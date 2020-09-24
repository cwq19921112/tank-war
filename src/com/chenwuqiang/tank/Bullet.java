package com.chenwuqiang.tank;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 19:50
 **/
public class Bullet {
    private static final int SPEED = 10;
    private int x;
    private int y;
    private boolean isAlive = true;
    private Dir dir;
    private Group group;
    private TankFrame tankFrame;
    private Tank tank;
    private int width;
    private int height;
    Rectangle rectangle1 = new Rectangle();
    Rectangle rectangle2 = new Rectangle();

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame,Tank tank, Group group) {
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.tank = tank;
        setWH(dir);
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            tankFrame.getBulletList().remove(this);
        }
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.FRAME_WIDTH || y > TankFrame.FRAME_HEIGHT) {
            isAlive = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void collideWith(Tank tank) {
        if (group.equals(tank.getGroup())) {
            return;
        }
        changeRec(rectangle1, x, y, width, height);
        changeRec(rectangle2, tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
        boolean intersects = rectangle1.intersects(rectangle2);
        if (intersects) {
            die();
            tank.die();
            tank.explode();
        }
    }

    private void changeRec(Rectangle rectangle, int x, int y, int width, int height) {
        rectangle.setBounds(x, y, width, height);
    }

    private void die() {
        isAlive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void setWH(Dir dir) {
        switch (dir) {
            case UP:
            case DOWN:
                width = ResourceMgr.bulletU.getWidth();
                height = ResourceMgr.bulletU.getHeight();
                break;
            case LEFT:
            case RIGHT:
                width = ResourceMgr.bulletL.getWidth();
                height = ResourceMgr.bulletL.getHeight();
                break;
        }
    }
}
