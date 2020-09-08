package com.chenwuqiang.tank.v1;

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
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x + (Tank.WIDTH - WIDTH) / 2;
        this.y = y + (Tank.HEIGHT - HEIGHT) / 2;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
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
        Rectangle rectangle1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        boolean intersects = rectangle1.intersects(rectangle2);
        if (intersects) {
            die();
            tank.die();
            tank.explode();
        }
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
}
