package com.chenwuqiang.tank.v1;

/**
 * @author: Administrator
 * @date: 2020/8/30 0030 14:38
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true) {
            tankFrame.repaint();
            Thread.sleep(50);
        }
    }
}
