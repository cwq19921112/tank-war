package com.chenwuqiang.tank.v1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceMgr {
    public static BufferedImage goodTankU;
    public static BufferedImage goodTankD;
    public static BufferedImage goodTankL;
    public static BufferedImage goodTankR;

    static {
        try {
            InputStream UStream = Test.class.getClassLoader().getResourceAsStream("images/tankU.gif");
            goodTankU = ImageIO.read(UStream);
            InputStream DStream = Test.class.getClassLoader().getResourceAsStream("images/tankD.gif");
            goodTankD = ImageIO.read(DStream);
            InputStream LStream = Test.class.getClassLoader().getResourceAsStream("images/tankL.gif");
            goodTankL = ImageIO.read(LStream);
            InputStream RStream = Test.class.getClassLoader().getResourceAsStream("images/tankR.gif");
            goodTankR = ImageIO.read(RStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
