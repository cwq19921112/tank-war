package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.TankFrame;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:30
 **/
public class LowExplode extends BaseExplode {
    public LowExplode(int x, int y, TankFrame tankFrame) {
        super(x, y, tankFrame);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        if (!alive) {
            tankFrame.getExplodeList().remove(this);
        }
        g.fillRect(x, y, 20 * step, 20 * step);
        step++;
        if (step == 10) {
            step = 0;
            alive = false;
        }
        g.setColor(color);
    }
}
