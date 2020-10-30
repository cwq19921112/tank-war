package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.mgr.ResourceMgr;
import com.chenwuqiang.tank.model.GameModel;
import com.chenwuqiang.tank.model.GameObject;
import com.chenwuqiang.tank.strategy.DefaultFireStrategy;
import com.chenwuqiang.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 17:44
 **/
public class Tank extends GameObject {
    public static final int DEFAULT_SPEED = PropMgr.getIntProp("tank.defaultSpeed");
    public static final int MAIN_SPEED = PropMgr.getIntProp("tank.mainSpeed");
    public static final int BAD_SPEED = PropMgr.getIntProp("tank.badSpeed");
    private static FireStrategy badFireStrategy = DefaultFireStrategy.getInstance();
    private boolean moving;
    private boolean alive = true;
    private int speed;
    private Dir dir;
    private GameModel gm;
    private Group group;
    private Random random = new Random();
    private int preX;
    private int preY;

    public Tank(int x, int y, int speed, Dir dir, GameModel gm, Group group) {
        super(x, y);
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        this.speed = speed;
        if (Group.GOOD == group) {
            setWH();
        } else if (Group.BAD == group) {
            width = ResourceMgr.badTankU.getWidth();
            height = ResourceMgr.badTankU.getHeight();
        }
        preX = x;
        preY = y;
    }

    public void setWH() {
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
            gm.removeGameObj(this);
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
        Explode explode = new Explode(x, y, gm);
        gm.addGameObj(explode);
    }

    private void move() {
        preX = x;
        preY = y;
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

    public void stop() {
        moving = false;
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
        setWH();
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

    public GameModel getGm() {
        return gm;
    }

    public void setGm(GameModel gm) {
        this.gm = gm;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }
}
