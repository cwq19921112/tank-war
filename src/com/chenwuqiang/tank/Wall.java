package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class Wall extends GameObject {
    private int width;
    private int height;
    private Rectangle rect;

    public Wall(int x, int y) {
        this(x, y, PropMgr.getIntProp("wall.width"), PropMgr.getIntProp("wall.height"));
    }

    public Wall(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(color);
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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
