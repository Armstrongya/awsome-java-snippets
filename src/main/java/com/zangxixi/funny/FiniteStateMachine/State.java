package com.zangxixi.funny.FiniteStateMachine;

/**
 * Created by zxx on 2017/1/18.
 */
public interface State {
    void power(AirController ac);
    void cool(AirController ac);
}
