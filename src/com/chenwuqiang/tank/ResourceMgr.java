package com.chenwuqiang.tank;

import com.chenwuqiang.tank.utils.ImageUtil;

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
    public static BufferedImage badTankU;
    public static BufferedImage badTankD;
    public static BufferedImage badTankL;
    public static BufferedImage badTankR;
    public static BufferedImage bulletU;
    public static BufferedImage bulletD;
    public static BufferedImage bulletL;
    public static BufferedImage bulletR;
    public static List<BufferedImage> explodeImgs = new ArrayList<>();

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();
            goodTankU = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            badTankU = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);

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
