package com.chenwuqiang.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 17:44
 **/
public class Tank {
    public static final int DEFAULT_SPEED = 5;
    public static final int MAIN_SPEED = 8;
    public static final int BAD_SPEED = 5;
    private int x;
    private int y;
    private boolean moving;
    private boolean alive = true;
    private int speed;
    private Dir dir;
    private TankFrame tankFrame;
    private Group group;
    private Random random = new Random();
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Tank(int x, int y, int speed, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.speed = speed;
    }

    public void paint(Graphics g) {
        if (!alive) {
            tankFrame.getBadTankList().remove(this);
            return;
        }
        switch (dir) {
            case UP:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            default:
                break;
        }
        // 敌方坦克随机改变方向
        if (Group.BAD.equals(group) && random.nextInt(100) > 97) {
            dir = Dir.values()[random.nextInt(Dir.values().length)];
        }
        move();
        if (Group.BAD.equals(group) && random.nextInt(30) > 28) {
            fire();
        }
    }

    public void fire() {
        Bullet bullet = new Bullet(x, y, dir, tankFrame, group);
        tankFrame.getBulletList().add(bullet);
    }

    public void explode() {
        Explode explode = new Explode(x, y, tankFrame);
        tankFrame.getExplodeList().add(explode);
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            default:
                break;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void die() {
        alive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
