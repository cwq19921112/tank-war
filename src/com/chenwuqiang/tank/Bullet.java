package com.chenwuqiang.tank;

import com.chenwuqiang.tank.factory.BaseBullet;
import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.mgr.ResourceMgr;

import java.awt.*;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 19:50
 **/
public class Bullet extends BaseBullet {
    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Tank tank, Group group) {
        super(x, y, dir, tankFrame, tank, group);
    }

    @Override
    public void paint(Graphics g) {
        if (!isAlive) {
            tankFrame.getBulletList().remove(this);
        }
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }
}
