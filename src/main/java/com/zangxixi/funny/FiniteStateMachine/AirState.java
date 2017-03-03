package com.zangxixi.funny.FiniteStateMachine;

/**
 * 遥控器的几个状态: 电源关闭, 送风, 制冷
 *
 * 有两个按键动作：
 * 1) 按电源Power键就开启送风, 进入送风状态，再按就关闭
 * 2) 按制冷Cool键……不写了,画图最合适
 *
 * 感觉有限状态机, 就是定义了有限的状态和状态之间的切换过程, 剩下的就是执行各种切换动作
 * Created by zxx on 2017/1/18.
 */
public enum AirState implements State{
    OFF {
        void exit(AirController ac) {
            super.exit(ac);
            startWind();
        }

        public void power(AirController ac) {
            this.exit(ac);
            ac.airState = WIND;
            ac.airState.entry(ac);
        }

        public void cool(AirController ac) {
            System.out.println("nothing");
        }
    },
    WIND {

        public void power(AirController ac) {
            this.exit(ac);
            stopWind();
            ac.airState = OFF;
            ac.airState.entry(ac);
        }

        public void cool(AirController ac) {
            this.exit(ac);
            ac.airState = COOL;
            ac.airState.entry(ac);
        }
    },
    COOL {
        void exit(AirController ac) {
            super.exit(ac);
            stopCool();
        }

        void entry(AirController ac) {
            startCool();
            super.entry(ac);
        }

        public void power(AirController ac) {
            this.exit(ac);
            stopWind();
            ac.airState = OFF;
            ac.airState.entry(ac);
        }

        public void cool(AirController ac) {
            this.exit(ac);
            ac.airState = WIND;
            ac.airState.entry(ac);
        }
    };


    //void power(AirController ac);
    //void cool(AirController ac);

    void entry(AirController ac) {
        System.out.println("->" + ac.airState.name());
    }

    void exit(AirController ac) {
        System.out.print(ac.airState.name() + "->");
    }

    void startCool() {
        System.out.print("start cool");
    }

    void stopCool() {
        System.out.print("stop cool");
    }

    void startWind() {
        System.out.print("start wind");
    }

    void stopWind() {
        System.out.print("stop wind");
    }
}
