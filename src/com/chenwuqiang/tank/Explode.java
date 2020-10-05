package com.chenwuqiang.tank;

import com.chenwuqiang.tank.factory.BaseExplode;
import com.chenwuqiang.tank.mgr.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/9/8 0008 23:40
 **/
public class Explode extends BaseExplode {
    public Explode(int x, int y, TankFrame tankFrame) {
        super(x, y, tankFrame);
    }

    @Override
    public void paint(Graphics g) {
        if (!alive) {
            tankFrame.getExplodeList().remove(this);
        }
        List<BufferedImage> explodeImgs = ResourceMgr.explodeImgs;
        g.drawImage(explodeImgs.get(step++), x, y, null);
        if (step == explodeImgs.size()) {
            step = 0;
            alive = false;
        }
    }
}
