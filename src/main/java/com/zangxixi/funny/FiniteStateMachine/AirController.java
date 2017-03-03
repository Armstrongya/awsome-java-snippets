package com.zangxixi.funny.FiniteStateMachine;

/**
 * Created by zxx on 2017/1/18.
 */
public class AirController {
    AirState airState = AirState.OFF;

    public void power() {
        airState.power(this);
    }

    public void cool() {
        airState.cool(this);
    }

    public static void main(String[] args) {
        AirController ac = new AirController();
        System.out.println("Current State= " + ac.airState.name());

        ac.cool();
        ac.power();
        ac.cool();
        ac.cool();
        ac.power();
        ac.power();
        ac.power();
    }
}
