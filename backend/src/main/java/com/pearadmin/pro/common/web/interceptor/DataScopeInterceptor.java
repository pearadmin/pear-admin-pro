package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.web.interceptor.annotation.DataScope;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.common.context.BeanContext;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.stereotype.Component;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describe: 数据权限
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
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
        SysRoleService roleService = BeanContext.getBean(SysRoleService.class);

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        DataScope annotation = getAnnotation(mappedStatement);

        if(annotation!=null){

            String sql = getSql(invocation);

            // 根 据 注 解 模 式, 处 理 预 定 义 SQL 语 句
            Scope scope = annotation.scope();

            if(scope.equals(Scope.AUTO)){

                // 根 据 用 户 定 义 权 限 填 充 SQL
                List<SysRole> roles = userContext.getRole();

                Set<String> set = new HashSet<>();

                for (SysRole role : roles) {

                    Scope roleScope = role.getScope();
                    String roleId = role.getId();

                    // 根 据 用 户 定 义 权 限 填 充 SQL
                    if(roleScope.equals(Scope.ALL)){
                        break;
                    }

                    if(roleScope.equals(Scope.CUSTOM)){

                        // 查 询 自 定 义
                        List<SysDept> deptIds = roleService.dept(roleId);

                        //
                    }

                    if(roleScope.equals(Scope.DEPT)){

                        // 所 属 部 门
                        String deptId = userContext.getDeptId();

                        set.add(deptId);
                    }

                    if(roleScope.equals(Scope.DEPT_CHILD)){

                        // 所 属 下 级 部 门

                    }

                    if(roleScope.equals(Scope.SELF)){

                        // 用 户 编 号
                        String userId = userContext.getUserId();

                        //
                    }
                }
            }

            // 回填 SQL 语句
            setSql(invocation, sql);
        }

        return invocation.proceed();
    }

    /**
     * 获 取 SQL
     */
    private String getSql(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }

    /**
     * 包装 sql 后，回填到 invocation 中
     */
    private void setSql(Invocation invocation, String sql) throws SQLException {
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
     * 权 限 注 解 信 息 获 取
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
                if (me.getName().equals(methodName) && me.isAnnotationPresent(DataScope.class)) {
                    dataAuth = me.getAnnotation(DataScope.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataAuth;
    }

    /**
     * 内 部 类 作 用 于 SQL 包 装
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
