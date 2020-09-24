package com.chenwuqiang.tank.mgr;

import java.io.IOException;
import java.util.Properties;

public class PropMgr {
    public static void main(String[] args) {
        System.out.println(PropMgr.getIntProp("frame.width"));
    }

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(PropMgr.class.getClassLoader().getResourceAsStream("prop/tank.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStrProp(String key) {
        return properties.getProperty(key);
    }

    public static Integer getIntProp(String key) {
        return Integer.parseInt(getStrProp(key));
    }
}
