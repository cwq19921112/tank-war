package com.chenwuqiang.tank;

import com.chenwuqiang.tank.mgr.PropMgr;
import com.chenwuqiang.tank.model.GameModel;
import com.chenwuqiang.tank.strategy.FireStrategy;
import com.chenwuqiang.tank.strategy.FourDirFirStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 14:39
 **/
public class TankFrame extends Frame {
    public static final int FRAME_WIDTH = PropMgr.getIntProp("frame.width");
    public static final int FRAME_HEIGHT = PropMgr.getIntProp("frame.height");
    private static FireStrategy goodFireStrategy = FourDirFirStrategy.getInstance();
    private boolean bL = false;
    private boolean bR = false;
    private boolean bU = false;
    private boolean bD = false;

    private GameModel gm = new GameModel();

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
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
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
                    gm.getMainTank().fire(goodFireStrategy);
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank mainTank = gm.getMainTank();
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
}
