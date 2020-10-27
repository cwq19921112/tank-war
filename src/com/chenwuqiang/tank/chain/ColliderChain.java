package com.chenwuqiang.tank.chain;

import com.chenwuqiang.tank.collider.*;
import com.chenwuqiang.tank.model.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new TankBulletCollider());
        add(new TankTankCollider());
        add(new TandWallCollider());
        add(new WallBulletCollider());
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    @Override
    public boolean collider(GameObject gbj1, GameObject gbj2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collider(gbj1, gbj2)) {
                return false;
            }
        }
        return true;
    }
}
