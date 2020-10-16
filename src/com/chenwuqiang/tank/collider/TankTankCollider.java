package com.chenwuqiang.tank.collider;

import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class TankTankCollider implements Collider {
    Rectangle rectangle1 = new Rectangle();
    Rectangle rectangle2 = new Rectangle();

    @Override
    public boolean collider(GameObject gbj1, GameObject gbj2) {
        if (gbj1 instanceof Tank && gbj2 instanceof Tank) {
            Tank tank1 = (Tank) gbj1;
            Tank tank2 = (Tank) gbj2;
            tank1.setWH();
            tank2.setWH();
            changeRec(rectangle1, tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight());
            changeRec(rectangle2, tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());
            boolean intersects = rectangle1.intersects(rectangle2);
            if (intersects) {
                tank1.setX(tank1.getPreX());
                tank2.setY(tank2.getPreY());
            }
        }
        return true;
    }

    private void changeRec(Rectangle rectangle, int x, int y, int width, int height) {
        rectangle.setBounds(x, y, width, height);
    }
}
