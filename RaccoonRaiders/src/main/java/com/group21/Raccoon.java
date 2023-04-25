package com.group21;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;

/**
 * This class represents the main enemy character, a Raccoon.
 * It extends the Characters class and inherits its properties and methods.
 */
public class Raccoon extends Characters{

    /**
     * Constructor for the Raccoon class.
     * 
     * @param gp the GamePanel to be updated
     */
    public Raccoon(GamePanel gp) {
        super(gp);

        type = 1;
        direction = "down";
        speed = 2;
        directory = "/raccoon_image/raccoon";
        
        // Set up the hitbox area for the Raccoon object
        solidArea = new Rectangle(5, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    /**
     * Triggers the pathfinding algorithm for the Raccoon.
     */
    public void setAction() {
        // Calculate the goal column and row for the Raccoon to move towards
        int goalCol = (gp.student.xPosition + gp.student.solidArea.x) / gp.tileSize;
        int goalRow = (gp.student.yPosition + gp.student.solidArea.y) / gp.tileSize;

        // Use the pathfinding algorithm to determine the shortest path to the goal
        searchPath(goalCol, goalRow);
    }

    /**
     * Searches for the shortest path to player.
     * 
     * @param goalCol the goal column
     * @param goalRow the goal row
     */
    public void searchPath(int goalCol, int goalRow) {
    
        // Calculate the starting column and row for the Raccoon's position
        int startCol = (xPosition + solidArea.x)/gp.tileSize;
        int startRow = (yPosition + solidArea.y)/gp.tileSize;

        // Set the start and goal nodes for the pathfinding algorithm
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        // If a path to the goal was found, determine the Raccoon's direction and move it
        if(gp.pFinder.search() == true) {

            // Get the next column and row in the path
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Determine the Raccoon's solid area positions
            int enLeftX = xPosition + solidArea.x;
            int enRightX = xPosition + solidArea.x + solidArea.width;
            int enTopY = yPosition + solidArea.y;
            int enBottomY = yPosition + solidArea.y + solidArea.height;

            // Determine the direction the Raccoon should move in based on its position and the next path node
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY <= nextY + gp.tileSize) {
                // Left or Right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                // Up or Left
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                // Up or Right
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
                
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                // Down or Left
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                // Down or Right
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }
        }
    }

    /**
     * Damages player if raccoon contacts enemy.
     */
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this); // Check if the raccoon has collided with a tile
        gp.cChecker.checkObjects(this, false, gp.rewards); // Check if the raccoon has collided with any reward objects
        boolean touchPlayer = gp.cChecker.checkPlayer(this); // Check if the raccoon has collided with the player

        if (this.type == 1 && touchPlayer == true) {
            if (gp.student.invincible == false) {
                gp.student.heart -= 1;
                System.out.println("Enemy is hitting you!! Score: " + gp.student.score);
                gp.student.invincible = true;
            }
        }
    }

    /**
     * Updates the raccoon's position and animation.
     */
    @Override
    public void update() {
        
        setAction();
        checkCollision();

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
     * Draws the raccoon's sprite.
     */
    @Override
    public void draw(Graphics2D g2) {

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

        g2.drawImage(image, xPosition, yPosition, gp.tileSize, gp.tileSize, null);
    }

}
