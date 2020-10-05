package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.*;

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

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Tank tank, Group group) {
        return new Bullet(x, y, dir, tankFrame, tank, group);
    }

    public static AbstractEntityFactory getFactory() {
        return factory;
    }
}
