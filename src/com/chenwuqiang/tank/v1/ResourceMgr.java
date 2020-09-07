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
    public static BufferedImage bulletU;
    public static BufferedImage bulletD;
    public static BufferedImage bulletL;
    public static BufferedImage bulletR;

    static {
        try {
            InputStream UStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif");
            goodTankU = ImageIO.read(UStream);
            InputStream DStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif");
            goodTankD = ImageIO.read(DStream);
            InputStream LStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif");
            goodTankL = ImageIO.read(LStream);
            InputStream RStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif");
            goodTankR = ImageIO.read(RStream);
            InputStream bulletUStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif");
            bulletU = ImageIO.read(bulletUStream);
            InputStream bulletDStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif");
            bulletD = ImageIO.read(bulletDStream);
            InputStream bulletLStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif");
            bulletL = ImageIO.read(bulletLStream);
            InputStream bulletRStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif");
            bulletR = ImageIO.read(bulletRStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
