package com.group21;

import java.awt.*;
import java.awt.image.*;

/**
 * Main character -- Student class
 */
public class Student extends Characters {
   
    /**
     * {@code KeyHandler} to interact with keyboard input
     */
    protected KeyHandler key;

    /**
     * Number of life 
     */
    protected int heart = 1;

    /**
     * number of collected rewards
     */
    protected int numCollected = 0; 

    /**
     * Construct new Student.
     * 
     * @param gp target GamePanel to draw on
     * @param key KeyHandler
     */
    public Student(GamePanel gp, KeyHandler key) {
        super(gp);
        this.key = key;

        directory = "/student_image/student";

        // hitting area
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
    }

    /**
     * Sets character's default values.
     */
    public void setDefaultValues(){
        xPosition = 7 * gp.tileSize;
        yPosition = 1 * gp.tileSize;
        speed = 2;
        direction = "down";
    }

    /**
     * Restores the characters values.
     */
    public void restoreHealthAndScore() {
        heart = 1;
        score = 0;
        invincible = false;
        numCollected = 0;
    }

    /**
     * interact with keys and based on the key input make character move
     */
    public void setUpMovement(){
        if (key.up == true || key.down == true || key.left == true || key.right == true){
            if (key.up == true && key.down == false && key.left == false && key.right == false){
                direction = "up";
            }
            if (key.up == false && key.down == true && key.left == false && key.right == false){
                direction = "down";
            }
            if (key.up == false && key.down == false && key.left == false && key.right == true){
                direction = "right";
            }
            if (key.up == false && key.down == false && key.left == true && key.right == false){
                direction = "left";
            }

            setUpCollisionObject();
            moveChar();
            makeSprite();
        }
    }

    /**
     * Update the student's location (status) by interacting with keyboard inputs.
     */
    public void update(){
        setUpMovement();

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Game over clause
        if (heart <= 0 || score < 0) {
            gp.state = State.Over;
        }
    }

    /**
     * Increases score when picking up items.
     * 
     * @param index index of the item on rewards array
     */
    public void pickUpRewards(int index){
        if (index != -1){
            score += gp.rewards[index].score;

            // Double the speed if bubble tea is collected
            if (gp.rewards[index].name == "BubbleTea")
                speed *= 2;
            else{
                numCollected++;
            }
            gp.rewards[index] = null;
        }
    }

    /**
     * make character sprite.
     */
    public void makeSprite(){
        spriteCounter++;
        if (spriteCounter > 15){
            if (spriteNumber == 1){
                spriteNumber = 2;
            }
            else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * based on direction, update the position of character
     */
    public void moveChar(){
        if (collisionOn == false){
            switch(direction){
                case "up": yPosition -= speed;
                    break;
                case "down": yPosition += speed;
                    break;
                case "right": xPosition += speed;
                    break;
                case "left": xPosition -= speed;
                    break;
            }
        }
    }

    /**
     * setting up collision for student to interact with objects
     */
    public void setUpCollisionObject(){

        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Rewards collision
        int rewardIndex = gp.cChecker.checkObjects(this, true, gp.rewards);
        pickUpRewards(rewardIndex);

        // Punishments collision
        int punishmentsIndex = gp.cChecker.checkObjects(this, true, gp.punishments);
        pickUpPunishments(punishmentsIndex);

        // Enemy collision
        int enemyIndex = gp.cChecker.checkEntity(this);
        interactEnemy(enemyIndex);

        if (collectAllChecker()){
            gp.cChecker.checkPortal(this, true, gp.portal);
        }
    }

    /**
     * Decreases score when picking up items.
     * 
     * @param index index of the item on punishments array
     */
    public void pickUpPunishments(int index){
        if (index != -1){
            score -= gp.punishments[index].score;
            gp.punishments[index] = null;
            if (speed > 1) speed -= 1;
        }
    }
    
    /**
     * Decreases player's health when it contacts and enemy.
     * 
     * @param index index of the item on items array
     */
    public void interactEnemy(int index){
        if (index != -1){
            if (invincible == false) {
                heart -= 1;
                invincible = true;
            }
        }
    }

    /**
     * Checks if all the rewards are collected.
     * 
     * @return true if all rewards are collected, false otherwise
     */
    public boolean collectAllChecker(){
        if (numCollected == 9){
            return true;
        }
        return false;
    }

    /**
     * Draw the student
     * 
     * @param g2 Graphics2D object to draw on
     */
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        switch(direction){
            case "up":
                if (spriteNumber == 1){
                    image = up1;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1){
                    image = down1;
                }
                if (spriteNumber == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1){
                    image = left1;
                }
                if (spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1){
                    image = right1;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
                break;
        }

        // Visualizes that damage was taken
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        
        g2.drawImage(image, xPosition, yPosition, gp.tileSize, gp.tileSize, null);
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }
}
