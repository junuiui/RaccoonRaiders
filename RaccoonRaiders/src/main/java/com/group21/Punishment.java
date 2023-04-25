package com.group21;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

/**
 * Punishment class
 */
public class Punishment extends Items{

    /**
     * Constructor.
     */
    public Punishment(){
        name = "Punishment";
        score = 50;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items_image/punishment.png"));
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
