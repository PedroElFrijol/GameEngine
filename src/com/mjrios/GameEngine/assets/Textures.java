package com.mjrios.GameEngine.assets;

import com.mjrios.GameEngine.utils.ImageLoader;

import java.awt.image.BufferedImage;

public class Textures {

    public static BufferedImage getItemTexture(String unlocalizedName) // want to get a texture from anywhere in this program
    {
        return ImageLoader.loadImage("/assets/textures/items/" + unlocalizedName + ".png");
    }

}
