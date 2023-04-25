package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileManagerTest {
    private GamePanel gp;
    private TileManager tm;

    @BeforeEach
    public void setup(){
        gp = new GamePanel();
        tm = new TileManager(gp);
    }

    @Test // integration testing
    public void mapArrayTest(){
        tm.loadMap();

        assertNotNull(tm.mapArr);
    }

    @Test // integration testing
    public void imagesTest(){
        tm.getTileImage();
        for (Tile t : tm.tiles){
            assertNotNull(t);
        }
    }
}