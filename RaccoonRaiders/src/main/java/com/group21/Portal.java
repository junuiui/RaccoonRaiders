package com.group21;

import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

/**
 * Portal class
 */
public class Portal {

    /**
     * Check Collision
     */
    protected boolean collision;

    /**
     * Sprite images for {@code Portal}
     */
    protected BufferedImage p1, p2, p3, p4, p5, p6;

    /**
     * The number representing for sprites
     */
    protected int spriteCounter = 0;

    /**
     * The number representing for sprites
     */
    protected int spriteNumber = 1;

    /**
     * {@code Portal}'s X coordinate
     */
    protected int xPosition;

    /**
     * {@code Portal}'s X coordinate
     */
    protected int yPosition;

     /**
     * {@code Portal}'s solid area represented by {@code Rectangle}
     */
    protected Rectangle solidArea = new Rectangle(39 * 32, 32, 16,16);

    /**
     * Constructor.
     */
    public Portal(){

        collision = true;

        try{
            p1 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals1.png"));
            p2 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals2.png"));
            p3 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals3.png"));
            p4 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals4.png"));
            p5 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals5.png"));
            p6 = ImageIO.read(getClass().getResourceAsStream("/items_image/portals/portals0.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draw onto panel.
     * 
     * @param g2 the Graphics2D object to draw on
     * @param gp target GamePanel to draw on
     */
    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image = null;
        spriteCounter++;
            if (spriteCounter > 90){
                spriteCounter = 0;
            }
            else if (spriteCounter > 75){
                spriteNumber = 6;
                image = p6;
            }
            else if (spriteCounter > 60){
                spriteNumber = 5;
                image = p5;
            }
            else if (spriteCounter > 45){
                spriteNumber = 4;
                image = p4;
            }
            else if (spriteCounter > 30){
                spriteNumber = 3;
                image = p3;
            }
            else if (spriteCounter > 15){
                spriteNumber = 2;
                image = p2;
            }
            else{
                spriteNumber = 1;
                image = p1;
            }

        g2.drawImage(image, xPosition, yPosition, gp.tileSize, gp.tileSize, null);
    }
}

