package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.mgr.ResourceMgr;
import com.chenwuqiang.tank.strategy.DefaultFireStrategy;
import com.chenwuqiang.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 17:44
 **/
public class Tank {
    public static final int DEFAULT_SPEED = PropMgr.getIntProp("tank.defaultSpeed");
    public static final int MAIN_SPEED = PropMgr.getIntProp("tank.mainSpeed");
    public static final int BAD_SPEED = PropMgr.getIntProp("tank.badSpeed");
    private static FireStrategy badFireStrategy = DefaultFireStrategy.getInstance();
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean moving;
    private boolean alive = true;
    private int speed;
    private Dir dir;
    private TankFrame tankFrame;
    private Group group;
    private Random random = new Random();

    public Tank(int x, int y, int speed, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.speed = speed;
        if (Group.GOOD == group) {
            setWH(dir);
        } else if (Group.BAD == group) {
            width = ResourceMgr.badTankU.getWidth();
            height = ResourceMgr.badTankU.getHeight();
        }
    }

    private void setWH(Dir dir) {
        switch (dir) {
            case UP:
            case DOWN:
                width = ResourceMgr.goodTankU.getWidth();
                height = ResourceMgr.goodTankU.getHeight();
                break;
            case LEFT:
            case RIGHT:
                width = ResourceMgr.goodTankL.getWidth();
                height = ResourceMgr.goodTankL.getHeight();
                break;
        }
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
        if (Group.BAD.equals(group) && random.nextInt(PropMgr.getIntProp("tank.changeDir.base")) > PropMgr.getIntProp("tank.changeDir.no")) {
            dir = Dir.values()[random.nextInt(Dir.values().length)];
        }
        move();
        if (Group.BAD.equals(group) && random.nextInt(PropMgr.getIntProp("tank.fire.base")) > PropMgr.getIntProp("tank.fire.no")) {
            fire(badFireStrategy);
        }
        // 边界检测
        checkBound();
    }

    /**
     * 边界检测
     */
    private void checkBound() {
        if (x < 2) {
            x = 2;
        }
        if (x > TankFrame.FRAME_WIDTH - width) {
            x = TankFrame.FRAME_WIDTH - width;
        }
        if (y < 2 + height / 2) {
            y = 2 + height / 2;
        }
        if (y > TankFrame.FRAME_HEIGHT - height) {
            y = TankFrame.FRAME_HEIGHT - height;
        }
    }

    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
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
        setWH(dir);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }
}
