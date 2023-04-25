package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleTeaTest {

    private Items bubbletea;

    @BeforeEach
    public void setup(){
        bubbletea = new BubbleTea();
    }

    @Test // integration testing
    public void imageTest(){
        assertNotNull(bubbletea.image);
    }

    @Test
    public void nameTest(){
        assertEquals(bubbletea.name, "BubbleTea");
    }

    @Test
    public void scoreTest(){
        assertEquals(100, bubbletea.score);
    }

    @Test
    public void collisionTest(){assertTrue(bubbletea.collision);}
}