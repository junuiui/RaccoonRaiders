package com.group21;

import java.awt.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyHandlerTest {
    
    private GamePanel gp;
    private KeyHandler key;

    @BeforeEach
    public void setup(){
        gp = new GamePanel();
        key = new KeyHandler(gp);
    }

    @Test // integration test
    public void titlePageInteractionTest(){

        // check initial condition
        assertEquals(0, gp.ui.order);
        
        // test for up key
        key.inputTitleScreen(KeyEvent.VK_UP);
        assertEquals(1, gp.ui.order);
        
        key.inputTitleScreen(KeyEvent.VK_UP);
        assertEquals(0, gp.ui.order);

        // test for down key
        key.inputTitleScreen(KeyEvent.VK_DOWN);
        assertEquals(1, gp.ui.order);

        key.inputTitleScreen(KeyEvent.VK_DOWN);
        assertEquals(0, gp.ui.order);

        // test for Enter key
        key.inputTitleScreen(KeyEvent.VK_ENTER);
        assertEquals(State.Game, gp.state);
    }

    /**
     * integration testing.
     * Testing the interaction between key input ('p' and esc) with pausing the game
     */
    @Test // integration testing, inte
    public void inputPauseTest(){

        // start game
        key.inputTitleScreen(KeyEvent.VK_ENTER);

        key.inputPause(KeyEvent.VK_P); // press 'p'
        assertEquals(State.Pause, gp.state);

        key.inputPause(KeyEvent.VK_P); // press 'p' again to resume
        assertEquals(State.Game, gp.state);

        key.inputPause(KeyEvent.VK_ESCAPE); // press 'esc' again to resume
        assertEquals(State.Pause, gp.state);

        key.inputPause(KeyEvent.VK_ESCAPE); // press 'esc'
        assertEquals(State.Game, gp.state);
    }

    @Test
    public void inputMoveTest(){

        // start the game
        key.inputTitleScreen(KeyEvent.VK_ENTER);

        key.inputMove(KeyEvent.VK_UP);
        assertTrue(key.up);

        key.inputMove(KeyEvent.VK_DOWN);
        assertTrue(key.down);

        key.inputMove(KeyEvent.VK_LEFT);
        assertTrue(key.left);

        key.inputMove(KeyEvent.VK_RIGHT);
        assertTrue(key.right);
    }

    @Test // integration testing
    public void inputGameOverTest(){

        // start game
        key.inputTitleScreen(KeyEvent.VK_ENTER);

        // make game over
        gp.state = State.Over;

        // check initial condition
        assertEquals(0, gp.ui.order);

        // for up
        key.inputGameOver(KeyEvent.VK_UP);
        assertEquals(1, gp.ui.order);

        key.inputGameOver(KeyEvent.VK_UP);
        assertEquals(0, gp.ui.order);

        // for down
        key.inputGameOver(KeyEvent.VK_DOWN);
        assertEquals(1, gp.ui.order);

        key.inputGameOver(KeyEvent.VK_DOWN);
        assertEquals(0, gp.ui.order);

        // for enter
        key.inputGameOver(KeyEvent.VK_ENTER);
        assertEquals(State.Game, gp.state);

    }
}
