package com.chenwuqiang.tank.factory;

import com.chenwuqiang.tank.Dir;
import com.chenwuqiang.tank.Group;
import com.chenwuqiang.tank.Tank;
import com.chenwuqiang.tank.TankFrame;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/10/5 0005 17:58
 **/
public class LowBullet extends BaseBullet {
    public LowBullet(int x, int y, Dir dir, TankFrame tankFrame, Tank tank, Group group) {
        super(x, y, dir, tankFrame, tank, group);
    }

    @Override
    public void paint(Graphics g) {
        if (!isAlive) {
            tankFrame.getBulletList().remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
        move();
        g.setColor(color);
    }
}
