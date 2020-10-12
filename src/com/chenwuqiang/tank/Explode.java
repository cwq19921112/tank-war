package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.ResourceMgr;
import com.chenwuqiang.tank.model.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/9/8 0008 23:40
 **/
public class Explode {
    public static final int WIDTH = ResourceMgr.explodeImgs.get(0).getWidth();
    public static final int HEIGHT = ResourceMgr.explodeImgs.get(0).getHeight();
    private boolean alive = true;
    private int x;
    private int y;
    private GameModel gm;
    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g) {
        if (!alive) {
            gm.getExplodeList().remove(this);
        }
        List<BufferedImage> explodeImgs = ResourceMgr.explodeImgs;
        g.drawImage(explodeImgs.get(step++), x, y, null);
        if (step == explodeImgs.size()) {
            step = 0;
            alive = false;
        }
    }

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
