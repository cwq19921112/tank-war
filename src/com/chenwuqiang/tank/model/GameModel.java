package com.chenwuqiang.tank.model;

import com.chenwuqiang.tank.*;
import com.chenwuqiang.tank.mgr.PropMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chenwuqiang.tank.TankFrame.FRAME_HEIGHT;
import static com.chenwuqiang.tank.TankFrame.FRAME_WIDTH;

public class GameModel {
    private static final int INIT_MY_TANK_X = PropMgr.getIntProp("frame.goodTank.initX");
    private static final int INIT_MY_TANK_Y = PropMgr.getIntProp("frame.goodTank.initY");

    private Tank mainTank = new Tank(INIT_MY_TANK_X, INIT_MY_TANK_Y, Tank.MAIN_SPEED, Dir.UP, this, Group.GOOD);
//    private List<Bullet> bulletList = new ArrayList<>();
//    private List<Tank> badTankList = new ArrayList<>();
//    private List<Explode> explodeList = new ArrayList<>();
    private List<GameObject> gameObjects = new ArrayList<>();

    public GameModel() {
        // 初始化敌军坦克
        for (int i = 0; i < PropMgr.getIntProp("tank.badCount"); i++) {
            Tank tank = new Tank(PropMgr.getIntProp("tank.badX") + PropMgr.getIntProp("tank.badGap") * i,
                    PropMgr.getIntProp("tank.badY"), Tank.BAD_SPEED, Dir.DOWN, this, Group.BAD);
            tank.setMoving(true);
            addGameObj(tank);
        }
    }

    public void addGameObj(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObj(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.fillRect(0,0, FRAME_WIDTH, FRAME_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + objCount(Bullet.class), 10, 80);
        g.drawString("敌方坦克的数量" + objCount(Tank.class), 10, 100);
        g.drawString("爆炸的数量" + objCount(Explode.class), 10, 120);
        g.setColor(color);
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        // 再画坦克
        mainTank.paint(g);

        // 碰撞检测
        /*for (Bullet bullet : bulletList) {
            for (Tank tank : badTankList) {
                bullet.collideWith(tank);
            }
        }*/
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public void setMainTank(Tank mainTank) {
        this.mainTank = mainTank;
    }

    public <T> int objCount(Class<T> tClass) {
        int count = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getClass().equals(tClass)) {
                count++;
            }
        }
        return count;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
