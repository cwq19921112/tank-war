package com.chenwuqiang.tank.collider;

import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.Wall;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class TandWallCollider implements Collider {
    Rectangle rectangle = new Rectangle();

    @Override
    public boolean collider(GameObject gbj1, GameObject gbj2) {
        if (gbj1 instanceof Tank && gbj2 instanceof Wall) {
            Tank tank = (Tank) gbj1;
            Wall wall = (Wall) gbj2;
            changeRec(rectangle, tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
            boolean intersects = rectangle.intersects(wall.getRect());
            if (intersects) {
                tank.setX(tank.getPreX());
                tank.setY(tank.getPreY());
            }
        } else if (gbj1 instanceof Wall && gbj2 instanceof Tank) {
            collider(gbj2, gbj1);
        }
        return true;
    }

    private void changeRec(Rectangle rectangle, int x, int y, int width, int height) {
        rectangle.setBounds(x, y, width, height);
    }
}
