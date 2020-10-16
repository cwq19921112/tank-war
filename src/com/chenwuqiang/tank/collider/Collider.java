package com.chenwuqiang.tank.collider;

import com.chenwuqiang.tank.model.GameObject;

public interface Collider {
    boolean collider(GameObject gbj1, GameObject gbj2);
}
