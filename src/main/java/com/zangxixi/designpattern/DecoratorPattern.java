package com.zangxixi.designpattern;

import org.junit.Test;

/**
 * 装饰器模式, 优点是不改变原有类代码, 不用继承, 就能动态扩展
 * 装饰器模式和适配器模式很像，二者的差异在于前者是在保持原有接口的基础上进行功能的扩展，而后者是改变原有的接口
 * 比如JDK IO中的InputStream(基类接口)，FileInputStream(基类实现类)，BufferedInputStream(装饰器类A)，DataInputStream(装饰器类B)就
 * @since 2016/4/13 11:20
 */
public class DecoratorPattern {
    //原始基类
    public interface DbOps {
        void add();
        void update();
    }

    public class MysqlDbOps implements DbOps {
        public void add() {
            System.out.println("mysql add operate");
        }

        public void update() {
            System.out.println("mysql update operate");
        }
    }

    public class OracleDbOps implements DbOps {
        public void add() {
            System.out.println("oracle add operate");
        }

        public void update() {
            System.out.println("oracle add operate");
        }
    }

    //装饰器抽象类, 这个类不是必须的
    public abstract class OrmOps implements DbOps {
        private DbOps dbOps;
        public OrmOps(DbOps dbOps) {
            this.dbOps = dbOps;
        }

        public void add() {
            dbOps.add();
        }

        public void update() {
            dbOps.update();
        }
    }

    //装饰器类, 其实这里可以直接去实现被装饰的原始接口DbOps
    public class JdbcOrmOps extends OrmOps {
        public JdbcOrmOps(DbOps dbOps) {
            super(dbOps);
        }

        private void prepareJdbc(String method) {
            System.out.println("装饰器扩展的JDBC方法:\t" + method + "\t参数检查或者处理");
        }

        //这里就体现出了装饰器模式的作用, 在原有类的基础上进行了扩展, 但是没有破坏原有类的语义
        public void add() {
            prepareJdbc("add");
            super.add();
        }

        public void update() {
            prepareJdbc("update");
            super.update();
        }
    }

    public class MyBatisOrmOps extends OrmOps {
        public MyBatisOrmOps(DbOps dbOps) {
            super(dbOps);
        }

        private void prepareMyBatis(String method) {
            System.out.println("装饰器扩展的MyBatis方法:\t" + method + "\t参数检查或者处理");
        }

        public void add() {
            prepareMyBatis("add");
            super.add();
        }

        public void update() {
            prepareMyBatis("update");
            super.update();
        }
    }

    @Test
    public void testDecoratorPattern() {
        DbOps dbOps = new OracleDbOps();//new MysqlDbOps();
        OrmOps jdbcOrmOps = new JdbcOrmOps(dbOps);
        jdbcOrmOps.add();

        OrmOps myBatisOrmOps = new MyBatisOrmOps(dbOps);
        myBatisOrmOps.update();
    }

}
