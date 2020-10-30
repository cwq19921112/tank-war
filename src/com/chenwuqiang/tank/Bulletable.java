package com.chenwuqiang.tank;

import com.chenwuqiang.tank.model.GameModel;

import java.awt.*;

public interface Bulletable {
    void paint(Graphics g);

    void die();

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    Group getGroup();

    GameModel getGm();
}
