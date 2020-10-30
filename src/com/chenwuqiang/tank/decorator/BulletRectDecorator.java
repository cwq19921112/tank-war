package com.chenwuqiang.tank.decorator;

import com.chenwuqiang.tank.Bulletable;
import com.chenwuqiang.tank.Group;
import com.chenwuqiang.tank.model.GameModel;
import com.chenwuqiang.tank.model.GameObject;

import java.awt.*;

public class BulletRectDecorator extends GameObject implements Bulletable {
    private boolean isAlive = true;
    private Bulletable bulletable;

    public BulletRectDecorator(Bulletable bulletable) {
        this.bulletable = bulletable;
    }

    @Override
    public void paint(Graphics g) {
        if (!isAlive) {
            getGm().removeGameObj(this);
        }
        bulletable.paint(g);
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(getX(), getY(), getWidth() + 5, getHeight() + 5);
        g.setColor(color);
    }

    @Override
    public void die() {
        isAlive = false;
    }

    @Override
    public int getX() {
        return bulletable.getX();
    }

    @Override
    public int getY() {
        return bulletable.getY();
    }

    @Override
    public Group getGroup() {
        return bulletable.getGroup();
    }

    @Override
    public GameModel getGm() {
        return bulletable.getGm();
    }

    @Override
    public int getWidth() {
        return bulletable.getWidth();
    }

    @Override
    public int getHeight() {
        return bulletable.getHeight();
    }
}
