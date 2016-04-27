package com.zangxixi.designpattern;

import org.junit.Test;

/**
 * 抽象工厂模式
 * 以工厂为单位来封装内部操作，有新的需求变化时直接添加新的工厂即可，不用修改之前的代码
 * 抽象工厂模式跟工厂模式最大的区别就是封装粒度大小，后者是参数级的封装，可以人为来跳动；前者直接封装到工厂里，直接调用即可
 * @TODO: 这里举的抽象工厂例子并不是太合适，后面找到合适的例子再替换
 * @since 2016/4/8 19:46
 */
public class AbstractFactoryPattern {

    public interface Sender {
        void send();
    }

    public class TextMailSender implements Sender {
        public void send() {
            System.out.println("we send text mail");
        }
    }

    public class HtmlMailSender implements Sender {
        public void send() {
            System.out.println("we send html mail");
        }
    }

    public class SmsSender implements Sender {
        public void send() {
            System.out.println("we send sms");
        }
    }

    public class SenderFactory {
        public void send(Sender sender) {
            sender.send();
        }
    }

    public interface Alarm {
        Sender notifyUser();
    }

    public class DebugAlarmFactory implements Alarm {
        public Sender notifyUser() {
            return new TextMailSender();
        }
    }

    public class InfoAlarmFactory implements Alarm {
        public Sender notifyUser() {
            return new HtmlMailSender();
        }
    }

    @Test
    public void testAbstractFactoryPattern() {
        Alarm alarmFactory = new InfoAlarmFactory();
        Sender sender = alarmFactory.notifyUser();
        sender.send();
    }
}
