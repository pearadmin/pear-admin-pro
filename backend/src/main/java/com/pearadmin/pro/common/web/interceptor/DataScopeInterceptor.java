package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.constant.SystemConstant;
import com.pearadmin.pro.common.web.interceptor.annotation.DataScope;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.common.context.BeanContext;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.util.stream.Collectors;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 数 据 权 限 拦 截 器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class DataScopeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        SysUserService userService = BeanContext.getBean(SysUserService.class);
        try
        {
            String userId = userContext.getUserId();
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            DataScope annotation = getAnnotation(mappedStatement);
            if(annotation != null){
                String sql = getSql(invocation);
                Scope scope = annotation.scope();
                sql = "select * from ("+ sql +") data a left join sys_user b on b.id = a.create_by";
                String where = SystemConstant.EMPTY;
                if(Scope.AUTO.equals(scope)){
                    List<SysRole> roles = userService.role(userId);
                    for (SysRole role: roles) {
                        where += sqlHandler(role.getScope());
                    }
                } else {
                    where += sqlHandler(scope);
                }

                if(Strings.isNotBlank(where)) {
                   where = where.replaceFirst("or","");
                   sql += " where " + where;
                }
                setSql(invocation, sql);
            }
        }
        catch (NullPointerException e) {
            // TODO 当 userId 表示非 request 执行 SQL
        }
        return invocation.proceed();
    }

    /**
     * 处理 Sql 权限
     * */
    private String sqlHandler(Scope scope) {
        SysDeptService deptService = BeanContext.getBean(SysDeptService.class);
        SysUserService userService = BeanContext.getBean(SysUserService.class);
        UserContext userContext = BeanContext.getBean(UserContext.class);
        String userId = userContext.getUserId();
        String deptId = userContext.getDeptId();

        if(Scope.SELF.equals(scope))
        {
            return "or a.create_by = " + userId;
        }
        else if(Scope.DEPT.equals(scope))
        {
            return "or b.dept_id = " + deptId;
        }
        else if(Scope.DEPT_CHILD.equals(scope))
        {
            return "or b.dept_id in (" + convertDept(deptService.treeAndChildren(deptId)) + ")";
        }
        else if(Scope.CUSTOM.equals(scope))
        {
            return "or b.dept_id in (" + convertDept(userService.dept(userId)) + ")";
        }
        else
        {
            return SystemConstant.EMPTY;
        }
    }

    private String convertDept(List<SysDept> deptList) {
        List<String> deptIds = deptList.stream().map(d -> d.getId()).collect(Collectors.toList());
        return StringUtils.join(deptIds,",");
    }

    /**
     * 获取 Sql 语句
     */
    private String getSql(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }

    /**
     * 回填 Sql 语句
     * */
    private void setSql(Invocation invocation, String sql) {
        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = statement.getBoundSql(parameterObject);
        MappedStatement newStatement = newMappedStatement(statement, new BoundSqlSqlSource(boundSql));
        MetaObject msObject =  MetaObject.forObject(newStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());
        msObject.setValue("sqlSource.boundSql.sql", sql);
        args[0] = newStatement;
    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    /**
     * 获取权限注解
     */
    private DataScope getAnnotation(MappedStatement mappedStatement) {
        DataScope dataAuth = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
            final Class<?> cls = Class.forName(className);
            final Method[] method = cls.getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName)) {
                    dataAuth = me.getAnnotation(DataScope.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataAuth;
    }

    /**
     * SQL 包装内部类
     * */
    class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
