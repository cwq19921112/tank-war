package com.chenwuqiang.tank.strategy;

import com.chenwuqiang.tank.Bullet;
import com.chenwuqiang.tank.Dir;
import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.decorator.BulletRectDecorator;

public class FourDirFirStrategy implements FireStrategy {

    private static FireStrategy fireStrategy = new FourDirFirStrategy();

    private FourDirFirStrategy() {

    }

    public static FireStrategy getInstance() {
        return fireStrategy;
    }

    @Override
    public void fire(Tank tank) {
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            Bullet bullet = new Bullet(tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2, dir,
                    tank.getGm(), tank.getGroup());
            BulletRectDecorator bulletRectDecorator = new BulletRectDecorator(bullet);
            tank.getGm().addGameObj(bulletRectDecorator);
        }
    }
}
