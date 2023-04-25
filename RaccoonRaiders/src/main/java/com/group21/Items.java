package com.group21;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Abstract Items Class
 */
public abstract class Items {

    /**
     * {@code Items} Image
     */
    protected BufferedImage image;

    /**
     * {@code Items} name
     */
    protected String name;

    /**
     * {@code Items} Score
     */
    protected int score;

    /**
     * The option for collision, deafult is {@code false}
     */
    protected boolean collision = false;

    /**
     * The X coordinate on the Screen of {@code Items}
     */
    protected int xPosition;

    /**
     * The Y coordinate on the Screen of {@code Items}
     */
    protected int yPosition;

    /**
     * Solid Area of {@code Items} represented by {@code Ractangle}
     */
    protected Rectangle solidArea = new Rectangle(0,0,16,16);

    /**
     * Defalut Solid Area on X (horizontal)
     */
    protected int solidAreaDefaultX = 0;

    /**
     * Defalut Solid Area on Y (vertical)
     */
    protected int solidAreaDefaultY = 0;

    /**
     * Draw onto panel.
     * 
     * @param g2 the Graphics2D object to draw on
     * @param gp target GamePanel to draw on
     */
    public abstract void draw(Graphics2D g2, GamePanel gp);
}
