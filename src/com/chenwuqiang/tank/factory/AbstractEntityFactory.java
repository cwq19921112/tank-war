package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.TankFrame;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:15
 **/
public abstract class AbstractEntityFactory {
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
}
