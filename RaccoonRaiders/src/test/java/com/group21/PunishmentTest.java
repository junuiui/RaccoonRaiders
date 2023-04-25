package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PunishmentTest {

    private Items punishment;

    @BeforeEach
    public void setup(){
        punishment = new Punishment();
    }

    @Test // integration testing
    public void imageTest(){
        assertNotNull(punishment.image);
    }

    @Test
    public void nameTest(){
        assertEquals(punishment.name, "Punishment");
    }

    @Test
    public void scoreTest(){
        assertEquals(50, punishment.score);
    }

    @Test
    public void collisionTest(){
        assertTrue(punishment.collision);
    }
}