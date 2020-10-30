package com.chenwuqiang.tank.decorator;

import com.chenwuqiang.tank.model.GameObject;

public abstract class GoDecorator extends GameObject {
    protected GameObject gameObject;

    public GoDecorator(GameObject gameObject){
        this.gameObject = gameObject;
    }
}
