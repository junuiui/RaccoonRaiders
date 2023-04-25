package com.group21;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Character class
 */
public abstract class Characters {

    /**
     * target {@code GamePanel} 
     */
    protected GamePanel gp;

    /**
     * The X coordinate on the Screen of {@code Characters}
     */
    protected int xPosition;

    /**
     * The Y coordinate on the Screen of {@code Characters}
     */
    protected int yPosition;
    
    /**
     * The speed of {@code Characters}
     */
    protected int speed;

    /**
     * Sprite Images for {@code Characters}'s movement
     */
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    /**
     * The Direction of {@code Characters}
     */
    protected String direction;

    /**
     * The option for collision, deafult is {@code false}
     */
    protected boolean collisionOn = false;

    /**
     * Solid Area of {@code Characters} represented by {@code Ractangle}
     */
    protected Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    /**
     * Game Score
     */
    protected int score = 0;

    /**
     * Action lock counter
     */
    protected int actionLockCounter = 0;

    /**
     * The option for invincible 
     */
    protected boolean invincible = false;

    /**
     * The counts of invincible
     */
    protected int invincibleCounter = 0;
    
    /**
     * The type of {@code Characters}, 0 for {@code Student} and 1 for {@code Raccoon}
     */
    protected int type; 

    /**
     * The directory of image resources
     */
    protected String directory;

    /**
     * The default solid area of {@code Characters}
     */
    protected int solidAreaDefaultX, solidAreaDefaultY;

    /**
     * The number representing for sprites
     */
    protected int spriteCounter = 0;

    /**
     * The number representing for sprites
     */
    protected int spriteNumber = 1;

    /**
     * Constructor.
     *
     * @param gp target GamePanel to be updated
     */
    public Characters(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Read the character's sprite images.
     */
    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream(directory + "_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(directory + "_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(directory + "_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(directory + "_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(directory + "_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(directory + "_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(directory + "_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(directory + "_right_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Sets the actions of characters.
     */
    public void setAction() {
        actionLockCounter++;
    
        if (actionLockCounter == 90) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // Picks num from 1-100
    
            if (i <= 25) direction = "up";
            if (i > 25 && i <= 50) direction = "down";
            if (i > 50 && i <= 75) direction = "left";
            if (i > 75 && i <= 100) direction = "right";

            actionLockCounter = 0;
        }
    }
    
    /**
     * Updates characters.
     */
    public abstract void update();
    
    /**
     * Draw onto panel.
     *
     * @param g2 the Graphics2D object to draw on
     */
    public abstract void draw(Graphics2D g2);
}
