package com.chenwuqiang.tank.v1;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 19:50
 **/
public class Bullet {
    private static final int SPEED = 5;
    private static final int WIDTH=20;
    private static final int HEIGHT=20;
    private int x;
    private int y;
    private boolean isAlive = true;
    private Dir dir;
    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            tankFrame.getBulletList().remove(this);
        }
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
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
}
