package com.chenwuqiang.tank.strategy;

import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.factory.BaseBullet;

public class DefaultFireStrategy implements FireStrategy {
    private static FireStrategy fireStrategy = new DefaultFireStrategy();

    private DefaultFireStrategy() {

    }

    @Override
    public void fire(Tank tank) {
        BaseBullet bullet = tank.getEntityFactory().createBullet(tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2, tank.getDir(),
                tank.getTankFrame(), tank, tank.getGroup());
        tank.getTankFrame().getBulletList().add(bullet);
    }

    public static FireStrategy getInstance() {
        return fireStrategy;
    }
}
