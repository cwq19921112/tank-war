package com.chenwuqiang.tank.strategy;

import com.chenwuqiang.tank.Bullet;
import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.decorator.BulletRectDecorator;

public class DefaultFireStrategy implements FireStrategy {
    private static FireStrategy fireStrategy = new DefaultFireStrategy();

    private DefaultFireStrategy() {

    }

    @Override
    public void fire(Tank tank) {
        Bullet bullet = new Bullet(tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2, tank.getDir(),
                tank.getGm(), tank.getGroup());
        BulletRectDecorator bulletRectDecorator = new BulletRectDecorator(bullet);
        tank.getGm().addGameObj(bulletRectDecorator);
    }

    public static FireStrategy getInstance() {
        return fireStrategy;
    }
}
