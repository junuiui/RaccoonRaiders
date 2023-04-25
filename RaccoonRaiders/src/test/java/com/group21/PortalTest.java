package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortalTest {
    
    private Portal portal;

    @BeforeEach
    public void setup(){
        portal = new Portal();
    }

    @Test
    public void collisionTest(){
        assertTrue(portal.collision);
    }

    @Test // integration testing
    public void imagesNotNullTest(){
        assertNotNull(portal.p1);
        assertNotNull(portal.p2);
        assertNotNull(portal.p3);
        assertNotNull(portal.p4);
        assertNotNull(portal.p5);
        assertNotNull(portal.p6);
    }
}
