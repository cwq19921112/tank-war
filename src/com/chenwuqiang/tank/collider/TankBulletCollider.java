package com.chenwuqiang.tank.collider;

import com.chenwuqiang.tank.Bullet;
import com.chenwuqiang.tank.Bulletable;
import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class TankBulletCollider implements Collider {
    Rectangle rectangle1 = new Rectangle();
    Rectangle rectangle2 = new Rectangle();

    @Override
    public boolean collider(GameObject gbj1, GameObject gbj2) {
        if (gbj1 instanceof Tank && gbj2 instanceof Bulletable) {
            Tank tank = (Tank) gbj1;
            Bulletable bullet = (Bulletable) gbj2;
            if (bullet.getGroup().equals(tank.getGroup())) {
                return true;
            }
            changeRec(rectangle1, bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            changeRec(rectangle2, tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
            boolean intersects = rectangle1.intersects(rectangle2);
            if (intersects) {
                bullet.die();
                tank.die();
                tank.explode();
                return false;
            }
        } else if (gbj1 instanceof Bulletable && gbj2 instanceof Tank) {
            collider(gbj2, gbj1);
        }
        return true;
    }

    private void changeRec(Rectangle rectangle, int x, int y, int width, int height) {
        rectangle.setBounds(x, y, width, height);
    }
}
