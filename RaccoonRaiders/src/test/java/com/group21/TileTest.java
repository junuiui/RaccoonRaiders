package com.group21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void tileCollisionTest(){
        Tile tile = new Tile();
        assertFalse(tile.collision);
    }
}