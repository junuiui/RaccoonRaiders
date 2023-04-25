package com.group21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;

/**
 * TileManager Class
 */
public class TileManager {

    /**
     * target {@code GamePanel} 
     */
    private GamePanel gp;
    
    /**
     * The Tile Array
     */
    protected ArrayList<Tile> tiles;

    /**
     * Map information
     */
    protected int mapArr[][]; // store map info

    /**
     * 
     */
    private boolean drawPath = false;
    private String directory = "/tiles_image/";

    /**
     * Deafult Constructor.
     * @param gp Main GamePanel
     */
    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new ArrayList<Tile>();
        mapArr = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    /**
     * Loads the map from a .txt file.
     */
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/mapFiles/mapInfo.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while (col < gp.maxScreenCol){
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);
                    mapArr[col][row] = num;
                    col++;
                }
                if (col >= gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Store tile images to <code>tiles</code> array.
     */
    public void getTileImage() {
        String[] imageNames = {"tile1.png", "tile2.png", "wall.png", "water.png", "bridge.png", "tree.png"};

        for (int i = 0; i < imageNames.length; i++) {
            try {
                Tile tile = new Tile();
                tile.image = ImageIO.read(getClass().getResourceAsStream(directory + imageNames[i]));
                if (i == 2 || i == 3 || i == 5) {
                    tile.collision = true;
                }
                tiles.add(tile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draw tiles on GamePanel.
     * 
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2){
        // put tiles on the panel
        int col = 0;
        int row = 0;   
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapArr[col][row];

            g2.drawImage(tiles.get(tileNum).image, x,y, gp.tileSize, gp.tileSize, null);
            col++;
            x+=gp.tileSize;
            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y+= gp.tileSize;
            }
        }

        if (drawPath == true) {
            g2.setColor(new Color(255, 0, 0, 70));

            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {

                int mapX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int mapY = gp.pFinder.pathList.get(i).row * gp.tileSize;

                g2.fillRect(mapX, mapY, 32, 32);

            }
        }
    }
}
