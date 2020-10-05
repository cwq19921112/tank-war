package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.Dir;
import com.chenwuqiang.tank.Group;
import com.chenwuqiang.tank.Tank;
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

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Tank tank, Group group) {
        return new LowBullet(x, y, dir, tankFrame, tank, group);
    }

    public static AbstractEntityFactory getFactory() {
        return factory;
    }
}
