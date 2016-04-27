package com.zangxixi.designpattern;

import com.google.common.reflect.Reflection;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理模式, 是使用代理对象来完成用户请求, 可以屏蔽用户对真实对象的访问
 * 典型的应用场景: 延迟加载、虚拟代理、安全代理、远程代理等
 * 个人理解：静态代理跟装饰器模式有点像, 动态代理用的比较多, 延迟加载其实是在方法首次调用的时候才去创建一些耗资源的东东
 * @since 2016/4/13 20:36
 */
public class ProxyPattern {
    /** 延迟加载案例, 如果在系统刚启动时就创建连接会很耗时, 这时可以用代理类把连接放到首次查询时建立, 加快系统启动时间 **/
    public interface DbQuery {
        String request();
    }

    public class NormalDbQuery implements DbQuery {
        public NormalDbQuery() {
            //比如这里要创建数据库连接, 如果系统刚启动就加载, 会很耗时
            System.out.println("create db connect, cost time");
        }

        public String request() {
            System.out.println("request db query");
            return null;
        }
    }

    //代理类实现延迟加载, 可以在系统启动的时候替换正常的NormalDbQuery对象, 加快启动速度
    public class DbQueryProxy implements DbQuery {
        private DbQuery dbQuery = null;

        public String request() {
            if (dbQuery == null) {
                dbQuery = new NormalDbQuery();//首次查询时才创建连接
                System.out.println("lazy load, create connect");
            }

            return dbQuery.request();
        }
    }

    @Test
    public void testLazyLoadProxy() {
        DbQuery dbQuery = new DbQueryProxy();//这一步对象的创建可以非常快
        dbQuery.request();//出来混还是要还的
    }

    /***
     * 动态代理，这里使用的是Guava的动态代理, 其实是封装JDK的动态代理
     */
    public class DbQueryDynamicProxyHandler implements InvocationHandler {
        private DbQuery dbQuery = null;

        //动态代理的执行入口, 这里还可以根据method或其他参数来动态调整, 添加更多业务逻辑进来
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (dbQuery == null) {
                dbQuery = new NormalDbQuery();
                System.out.println("lazy load, create connect");
            }

            return dbQuery.request();
        }
    }

    @Test
    public void testDynamicProxy() {
        InvocationHandler myDbQueryHandler = new DbQueryDynamicProxyHandler();
        DbQuery dbQueryProxy = Reflection.newProxy(DbQuery.class, myDbQueryHandler);
        dbQueryProxy.request();
    }

}
