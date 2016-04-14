package com.zangxixi.designpattern;

import org.junit.Test;

/**
 * 外观模式, 优点是解决了类与类之间的依赖关系, 跟Spring的理念很像,一个一个的pojo, 然后在Facade 类中对外提供封装后的接口调用
 * @since 2016/4/14 11:22
 */
public class FacadePattern {
    public class Cpu {
        public void startup() {
            System.out.println("cpu start up");
        }

        public void shutdown() {
            System.out.println("cpu shut down");
        }
    }

    public class Memory {
        public void read() {
            System.out.println("memory read");
        }

        public void write() {
            System.out.println("memory write");
        }
    }

    public class Disk {
        public void load() {
            System.out.println("load disk");
        }

        public void unload() {
            System.out.println("unload disk");
        }
    }

    /** 这个就是对外提供的 Facade 类 **/
    public class Computer {
        private Cpu cpu;
        private Memory memory;
        private Disk disk;

        public Computer() {
            cpu = new Cpu();
            memory = new Memory();
            disk = new Disk();
        }

        public void startup() {
            cpu.startup();
            memory.read();
            memory.write();
            disk.load();
            System.out.println("computer start up");
        }

        public void shutdown() {
            memory.write();
            disk.unload();
            cpu.shutdown();
            System.out.println("computer shut down");
        }
    }

    @Test
    public void testFacadePattern() {
       Computer computer = new Computer();
        computer.startup();
        System.out.println("===============================");
        computer.shutdown();
    }

}
