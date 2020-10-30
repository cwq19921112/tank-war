package com.chenwuqiang.tank.collider;

import com.chenwuqiang.tank.Bullet;
import com.chenwuqiang.tank.Bulletable;
import com.chenwuqiang.tank.Wall;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class WallBulletCollider implements Collider {
    Rectangle rectangle = new Rectangle();

    @Override
    public boolean collider(GameObject gbj1, GameObject gbj2) {
        if (gbj1 instanceof Wall && gbj2 instanceof Bulletable) {
            Wall wall = (Wall) gbj1;
            Bulletable bullet = (Bulletable) gbj2;

            changeRec(rectangle, bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            boolean intersects = rectangle.intersects(wall.getRect());
            if (intersects) {
                bullet.die();
                return false;
            }
        } else if (gbj1 instanceof Bulletable && gbj2 instanceof Wall) {
            collider(gbj2, gbj1);
        }
        return true;
    }

    private void changeRec(Rectangle rectangle, int x, int y, int width, int height) {
        rectangle.setBounds(x, y, width, height);
    }
}
