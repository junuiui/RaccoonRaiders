package com.group21;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

/**
 * class BubbleTea -- rewarding scores and double up the speed
 */
public class BubbleTea extends Items{
    /**
     * Constructor.
     */
    public BubbleTea(){
        name = "BubbleTea";
        score = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items_image/bubbleTea.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    /**
     * Draw onto panel.
     * 
     * @param g2 the Graphics2D object to draw on
     * @param gp target GamePanel to draw on
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, xPosition, yPosition, gp.tileSize, gp.tileSize, null);
    }
}
