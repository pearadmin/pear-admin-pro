package com.pearadmin.pro.common.Interceptor;

import com.pearadmin.pro.common.aop.lang.annotation.DataScope;
import com.pearadmin.pro.common.aop.lang.enums.Scope;
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
import org.aspectj.lang.annotation.Aspect;
import java.lang.reflect.Method;
import java.sql.SQLException;

@Aspect
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class DataScopeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获 取 权 限 注 解
        DataScope annotation = getAnnotation(mappedStatement);

        // 处 理 当 前 用户 权 限
        String sql = getSql(invocation);

        System.out.println("打印 SQL :" + sql);

        if(annotation!=null){

            System.out.println("存在 DataScope 注解");

             // 根 据 注 解 模 式, 处 理 预 定 义 SQL 语 句
            Scope scope = annotation.scope();

            if(scope.equals(Scope.AUTO)){

                // 根 据 用 户 定 义 权 限 填 充 SQL


            }

            // 回填 SQL 语句
            setSql(invocation, sql);
        }

        return invocation.proceed();
    }

    /**
     * 获取sql语句
     * @param invocation
     * @return
     */
    private String getSql(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }

    /**
     * 包装sql后，重置到invocation中
     * @param invocation
     * @param sql
     * @throws SQLException
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
     * 获取数据权限注解信息
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
     * 内部类 作用于 SQL 包装
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
