package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.TankFrame;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:29
 **/
public class LowEntityFactory extends AbstractEntityFactory {
    private static AbstractEntityFactory factory = new LowEntityFactory();

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new LowExplode(x, y, tankFrame);
    }

    public static AbstractEntityFactory getFactory() {
        return factory;
    }
}
