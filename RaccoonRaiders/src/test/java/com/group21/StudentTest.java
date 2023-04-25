package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private GamePanel gp;
    private Student student;
    private KeyHandler keyHandler;

    @BeforeEach
    public void setUp() {
        gp = new GamePanel();
        keyHandler = new KeyHandler(gp);
        student = new Student(gp, keyHandler);
        student.setDefaultValues();
    }

    // Tests for setting values

    @Test
    public void testSetDefaultValues() {
        assertEquals(7 * student.gp.tileSize, student.xPosition);
        assertEquals(1 * student.gp.tileSize, student.yPosition);
        assertEquals(2, student.speed);
        assertEquals("down", student.direction);
    }

    @Test
    public void testRestoreHealthAndScore() {
        student.heart = 0;
        student.score = -1;
        student.invincible = true;
        student.numCollected = 1;

        student.restoreHealthAndScore();

        assertEquals(1, student.heart);
        assertEquals(0, student.score);
        assertFalse(student.invincible);
        assertEquals(0, student.numCollected);
    }

    // Tests movement

    @Test // Integration test
    public void testSetUpMovement() {
        keyHandler.up = true;
        student.setUpMovement();
        assertEquals("up", student.direction);
        
        keyHandler.up = false;
        keyHandler.down = true;
        student.setUpMovement();
        assertEquals("down", student.direction);

        keyHandler.down = false;
        keyHandler.right = true;
        student.setUpMovement();
        assertEquals("right", student.direction);

        keyHandler.right = false;
        keyHandler.left = true;
        student.setUpMovement();
        assertEquals("left", student.direction);
    }

    @Test
    public void testMoveChar() {
        student.direction = "up";
        student.moveChar();
        assertEquals(student.yPosition, 1 * student.gp.tileSize - student.speed);
        
        student.direction = "down";
        student.moveChar();
        assertEquals(student.yPosition, 1 * student.gp.tileSize);
        
        student.direction = "left";
        student.moveChar();
        assertEquals(student.xPosition, 7 * student.gp.tileSize - student.speed);
        
        student.direction = "right";
        student.moveChar();
        assertEquals(student.xPosition, 7 * student.gp.tileSize);
    }
    
    // Tests for game state

    @Test
    public void testUpdate() {
        // Testing scenario where enemy hits player
        student.heart = 0;
        student.update();
        assertEquals(State.Over, student.gp.state);

        // Testing scenario where score becomes negative
        student.heart = 1;
        student.score = -1;
        student.update();
        assertEquals(State.Over, student.gp.state);
    }

    // Tests for sprites

    @Test
    public void testMakeSprite() {
        student.spriteNumber = 1;
        student.spriteCounter = 16;
        student.makeSprite();
        assertEquals(2, student.spriteNumber);

        student.spriteCounter = 31;
        student.makeSprite();
        assertEquals(1, student.spriteNumber);
    }
    
    // Tests for interactions with items

    @Test // Integration test
    public void testPickUpRewards() {
        // Test for Coffee reward
        student.gp.rewards[0] = new Coffee();
        student.pickUpRewards(0);
        assertEquals(50, student.score);
        assertEquals(1, student.numCollected);
        assertNull(student.gp.rewards[0]);
        
        // Test for BubbleTea reward
        student.gp.rewards[0] = new BubbleTea();
        int originalSpeed = student.speed;
        student.pickUpRewards(0);
        assertNotEquals(originalSpeed, student.speed);
        assertEquals(150, student.score);
        assertNull(student.gp.rewards[0]);
    }
    
    @Test // Integration test
    public void testPickUpPunishments() {
        student.gp.punishments[0] = new Punishment();  
        student.pickUpPunishments(0);
        assertEquals(-50, student.score);
        assertEquals(1, student.speed);
    }

    @Test // Integration test
    public void testInteractEnemy() {
        // Test health
        student.heart = 2;
        student.interactEnemy(0);
        assertEquals(1, student.heart);
        
        // Test invincibility
        student.invincible = false;
        student.interactEnemy(1);
        assertEquals(0, student.heart);
        assertTrue(student.invincible);
    }

    // @Test
    // public void testSetUpCollisionObject() {
    // }    
}    