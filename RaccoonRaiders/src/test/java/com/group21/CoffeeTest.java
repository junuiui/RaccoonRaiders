package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoffeeTest{

    private Items coffee;

    @BeforeEach
    public void setup(){
        coffee = new Coffee();
    }

    @Test // integration testing
    public void imageTest(){
        assertNotNull(coffee.image);
    }

    @Test
    public void nameTest(){
        assertEquals(coffee.name, "Coffee");
    }

    @Test
    public void scoreTest(){
        assertEquals(50, coffee.score);
    }

    @Test
    public void collisionTest(){
        assertTrue(coffee.collision);
    }
}