package com.chenwuqiang.tank.v1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ResourceMgr {
    public static BufferedImage goodTankU;
    public static BufferedImage goodTankD;
    public static BufferedImage goodTankL;
    public static BufferedImage goodTankR;
    public static BufferedImage bulletU;
    public static BufferedImage bulletD;
    public static BufferedImage bulletL;
    public static BufferedImage bulletR;
    public static List<BufferedImage> explodeImgs = new ArrayList<>();

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();
            goodTankU = ImageIO.read(classLoader.getResourceAsStream("images/tankU.gif"));
            goodTankD = ImageIO.read(classLoader.getResourceAsStream("images/tankD.gif"));
            goodTankL = ImageIO.read(classLoader.getResourceAsStream("images/tankL.gif"));
            goodTankR = ImageIO.read(classLoader.getResourceAsStream("images/tankR.gif"));
            bulletU = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.gif"));
            bulletD = ImageIO.read(classLoader.getResourceAsStream("images/bulletD.gif"));
            bulletL = ImageIO.read(classLoader.getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(classLoader.getResourceAsStream("images/bulletR.gif"));
            for (int i = 0; i < 16; i++) {
                explodeImgs.add(ImageIO.read(classLoader.getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
