package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.strategy.FireStrategy;
import com.chenwuqiang.tank.strategy.FourDirFirStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 14:39
 **/
public class TankFrame extends Frame {
    public static final int FRAME_WIDTH = PropMgr.getIntProp("frame.width");
    public static final int FRAME_HEIGHT = PropMgr.getIntProp("frame.height");
    private static final int INIT_MY_TANK_X = PropMgr.getIntProp("frame.goodTank.initX");
    private static final int INIT_MY_TANK_Y = PropMgr.getIntProp("frame.goodTank.initY");
    private static FireStrategy goodFireStrategy = FourDirFirStrategy.getInstance();
    private boolean bL = false;
    private boolean bR = false;
    private boolean bU = false;
    private boolean bD = false;
    private Tank mainTank = new Tank(INIT_MY_TANK_X, INIT_MY_TANK_Y, Tank.MAIN_SPEED, Dir.UP, this, Group.GOOD);
    private List<Bullet> bulletList = new ArrayList<>();
    private List<Tank> badTankList = new ArrayList<>();
    private List<Explode> explodeList = new ArrayList<>();

    public TankFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setTitle("tank war");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        // 初始化敌军坦克
        for (int i = 0; i < PropMgr.getIntProp("tank.badCount"); i++) {
            Tank tank = new Tank(PropMgr.getIntProp("tank.badX") + PropMgr.getIntProp("tank.badGap") * i,
                    PropMgr.getIntProp("tank.badY"), Tank.BAD_SPEED, Dir.DOWN, this, Group.BAD);
            tank.setMoving(true);
            badTankList.add(tank);
        }
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.fillRect(0,0, FRAME_WIDTH, FRAME_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bulletList.size(), 10, 80);
        g.drawString("敌方坦克的数量" + badTankList.size(), 10, 100);
        g.drawString("爆炸的数量" + explodeList.size(), 10, 120);
        g.setColor(color);
        // 先画子弹
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.paint(g);
        }
        // 再画坦克
        mainTank.paint(g);
        for (int i = 0; i < badTankList.size(); i++) {
            Tank badTank = badTankList.get(i);
            badTank.paint(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            Explode explode = explodeList.get(i);
            explode.paint(g);
        }
        // 碰撞检测
        for (Bullet bullet : bulletList) {
            for (Tank tank : badTankList) {
                bullet.collideWith(tank);
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case 0x11:
                    mainTank.fire(goodFireStrategy);
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            mainTank.setMoving(true);
            if (bL) {
                mainTank.setDir(Dir.LEFT);
            }
            if (bR) {
                mainTank.setDir(Dir.RIGHT);
            }
            if (bU) {
                mainTank.setDir(Dir.UP);
            }
            if (bD) {
                mainTank.setDir(Dir.DOWN);
            }
            if (!bL && !bR && !bU && !bD) {
                mainTank.setMoving(false);
            }
        }
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    private Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public List<Tank> getBadTankList() {
        return badTankList;
    }

    public void setBadTankList(List<Tank> badTankList) {
        this.badTankList = badTankList;
    }

    public List<Explode> getExplodeList() {
        return explodeList;
    }

    public void setExplodeList(List<Explode> explodeList) {
        this.explodeList = explodeList;
    }
}
