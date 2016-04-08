package com.zangxixi.designpattern;

import org.junit.Test;

/**
 * 普通工厂方法模式
 * 这种模式的缺陷是新增一种方法都得改动工厂
 *
 * @author
 * @since 2016/4/8 16:34
 */
public class FactoryMethodPattern {

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

    public class SendFactory {
        public Sender send(String cmd) {
            if ("textMail".equalsIgnoreCase(cmd)) {
                return new TextMailSender();
            } else if ("htmlMail".equalsIgnoreCase(cmd)) {
                return new HtmlMailSender();
            } else if ("sms".equalsIgnoreCase(cmd)) {
                return new SmsSender();
            } else {
                return null;
            }
        }
    }

    @Test
    public void testFactoryMethod() {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.send("sms");
        sender.send();
    }
}
