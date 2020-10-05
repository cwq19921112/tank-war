package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.Explode;
import com.chenwuqiang.tank.TankFrame;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:21
 **/
public class DefaultEntityFactory extends AbstractEntityFactory {
    private static AbstractEntityFactory factory = new DefaultEntityFactory();

    private DefaultEntityFactory() {

    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }

    public static AbstractEntityFactory getFactory() {
        return factory;
    }
}
