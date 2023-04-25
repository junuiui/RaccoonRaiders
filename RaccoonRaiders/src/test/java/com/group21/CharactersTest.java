package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;

import static org.junit.jupiter.api.Assertions.*;

class CharactersTest {

    private Characters characters;
    private GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
        characters = new Characters(gamePanel) {
            @Override
            public void update() {
                // Do nothing
            }

            @Override
            public void draw(Graphics2D g2) {
                // Do nothing
            }
        };
        characters.directory = "/student_image/student";
        characters.getImage();
    }

    @Test // integration testing
    void testGetImage() {
        assertNotNull(characters.up1);
        assertNotNull(characters.up2);
        assertNotNull(characters.down1);
        assertNotNull(characters.down2);
        assertNotNull(characters.left1);
        assertNotNull(characters.left2);
        assertNotNull(characters.right1);
        assertNotNull(characters.right2);
    }

    @Test
    void testSetAction() {
        int oldCounter = characters.actionLockCounter;
        characters.setAction();
        assertNotEquals(oldCounter, characters.actionLockCounter);
    }
}
