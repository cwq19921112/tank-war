package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.Dir;
import com.chenwuqiang.tank.Group;
import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.TankFrame;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:15
 **/
public abstract class AbstractEntityFactory {
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Tank tank, Group group);
}
