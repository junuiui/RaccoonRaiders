package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RaccoonTest {
    private GamePanel gp;
    private Raccoon raccoon;

    @BeforeEach
    public void setUp() {
        gp = new GamePanel();
        raccoon = new Raccoon(gp);
        raccoon.direction = "down";
    }

    @Test // Integration test
    public void testSetAction() {
        // Set up a student at position (0, 0) and call setAction() on raccoon
        gp.student.xPosition = 5 * gp.tileSize;
        gp.student.yPosition = 5 * gp.tileSize;
        raccoon.xPosition = 5 * gp.tileSize;
        raccoon.yPosition = 10 * gp.tileSize;
        raccoon.setAction();

        // Check that raccoon's direction has been set to "down"
        assertEquals("up", raccoon.direction);
    }

    @Test // Integration test
    public void testSearchPath() {
        // Set up raccoon at position (5, 10) and call searchPath(5, 5) to see if raccoon moves up
        raccoon.xPosition = 5 * gp.tileSize;
        raccoon.yPosition = 10 * gp.tileSize;
        raccoon.searchPath(5, 5);

        // Check that raccoon's direction has been set to "up"
        assertEquals("up", raccoon.direction);
    }

    @Test // Integration test
    public void testCheckCollision() {
        // Set up raccoon and student at the same position and call checkCollision()
        raccoon.xPosition = 0;
        raccoon.yPosition = 0;
        gp.student.xPosition = 0;
        gp.student.yPosition = 0;
        raccoon.checkCollision();

        // Check that raccoon's collisionOn flag has been set to true
        assertTrue(raccoon.collisionOn);
    }

    @Test
    public void testUpdate() {
        // Set up raccoon at position (0, 0) and call update() to move it down
        raccoon.xPosition = 5 * gp.tileSize;
        raccoon.yPosition = 5 * gp.tileSize;
        raccoon.direction = "down";
        raccoon.update();

        // Check that raccoon's y position has been updated
        assertEquals(5*gp.tileSize - 2, raccoon.yPosition);
    }
}
