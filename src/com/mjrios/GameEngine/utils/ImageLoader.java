package com.mjrios.GameEngine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {

    /**
     * @param path
     * passes in the path of the image
     * @return
     * returns an image that ImageIO has read and reads it from the point of view of this class
     */
    public static BufferedImage loadImage(String path)
    {
        try
        {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

}
