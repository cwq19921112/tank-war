package com.chenwuqiang.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws Exception{
        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("images/tankU.gif");
        BufferedImage bufferedImage = ImageIO.read(resourceAsStream);
        System.out.println(bufferedImage == null);
    }
}
