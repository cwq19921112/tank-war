package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.TankFrame;
import com.chenwuqiang.tank.mgr.ResourceMgr;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:16
 **/
public abstract class BaseExplode {
    public static final int WIDTH = ResourceMgr.explodeImgs.get(0).getWidth();
    public static final int HEIGHT = ResourceMgr.explodeImgs.get(0).getHeight();
    protected boolean alive = true;
    protected int x;
    protected int y;
    protected TankFrame tankFrame;
    protected int step = 0;

    public BaseExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public abstract void paint(Graphics g);

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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
}
