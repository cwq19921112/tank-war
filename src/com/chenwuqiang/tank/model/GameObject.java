package com.chenwuqiang.tank.model;

import java.awt.*;

public abstract class GameObject {
    public GameObject() {
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected int x;
    protected int y;

    public abstract void paint(Graphics g);

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
