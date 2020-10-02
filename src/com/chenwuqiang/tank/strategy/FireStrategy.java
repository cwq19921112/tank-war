package com.chenwuqiang.tank.strategy;

import com.chenwuqiang.tank.Tank;

/**
 * 开火策略
 */
public interface FireStrategy {
    void fire(Tank tank);
}
