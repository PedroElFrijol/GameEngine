package com.mjrios.GameEngine;

import com.mjrios.GameEngine.assets.Textures;
import com.mjrios.GameEngine.display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy; // A buffer strategy tells how to draw things to the screen

public class Game implements Runnable{

    public Display display;

    public final String title;

    public final int width;

    public final int height;

    private Thread thread;

    private boolean running = false;

    public Game(String title, int width, int height){

        this.title = title;

        this.width = width;

        this.height = height;

    }

    private void preInit(){



    }

    private void init(){

        display = new Display(title, width, height);

        System.out.println("[System]: initialization/INFO - Successfully initialize game (Game)");

    }

    private void postInit(){



    }

    private void tick(){



    }

    private void render()
    {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();

        if (bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);
        // DRAW HERE

        g.drawImage(Textures.getItemTexture("guava"), 0, 0, 300, 825, null);


        // End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run()
    {
        preInit();
        init();
        postInit();

        int fps = 60; // CHANGED IN SETTINGS TO EITHER CAP AT 30 OR 60
        double timePerTick = 1000000000f / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000)
            {
                display.getFrame().setTitle(title + " | FPS: " + ticks); // adds the fps to the title
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start()
    {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running) return;
        running = false;

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}