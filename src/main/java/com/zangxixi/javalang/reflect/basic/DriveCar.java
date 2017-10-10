package com.zangxixi.javalang.reflect.basic;

/**
 * Created by zxx on 2017/10/10 上午7:51.
 */
public class DriveCar {
    private boolean openStatus = false;
    private String driverName = "zxx";

    public boolean open() {
        if (!openStatus) {
            openStatus = true;
        }

        return openStatus;
    }

    public String where(String location) {
        return location;
    }
}
