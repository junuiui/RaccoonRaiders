package com.group21;

import java.awt.event.*;

/**
 * KeyHandler class -- interact with Students - Keyboard input from player
 */
public class KeyHandler implements KeyListener {

    /**
     * target {@code GamePanel} 
     */
    protected GamePanel gp;

    /**
     * {@code Student}'s direction (true - facing direction, false otherwise)
     */
    protected boolean up, down, right, left;

    /**
     * Constructor.
     * 
     * @param gp the target GamePanel
     */
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        inputTitleScreen(code);
        inputPause(code);
        inputMove(code);
        inputGameOver(code);
    }

    /**
     * interact with key code to move the options to choose in title screen
     * 
     * @param code key code for input key
     */
    public void inputTitleScreen(int code){
        // Input for buttons on Title screen
        if(gp.state == State.Title) {
            switch (code){
                case KeyEvent.VK_UP:
                    gp.ui.order--;
                    if(gp.ui.order < 0){
                        gp.ui.order = 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    gp.ui.order++;
                    if(gp.ui.order > 1){
                        gp.ui.order = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gp.ui.order == 0){
                        gp.state = State.Game;
                        gp.stopMusic();
                        gp.playMusic(0);
                    }
                    if(gp.ui.order == 1){
                        System.exit(0);
                    }
            }
        }
    }

    /**
     * interact with key code to do pause
     * 
     * @param code key code for input key
     */
    public void inputPause(int code){
        switch(code){
            case KeyEvent.VK_P:
                if (gp.state == State.Game) {
                    gp.state = State.Pause;
                }
                else if (gp.state == State.Pause) {
                    gp.state = State.Game;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (gp.state == State.Game) {
                    gp.state = State.Pause;
                }
                else if (gp.state == State.Pause) {
                    gp.state = State.Game;
                }
                break;
        }
    }

    /**
     * interact with key code to do move
     * 
     * @param code key code for input key
     */
    public void inputMove(int code){
        // Input for movement in game and triggering Pause screen
        switch (code) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            
        }
    }

    /**
     * interact with key code to do finish game
     * 
     * @param code key code for input key
     */
    public void inputGameOver(int code){
        // Input for buttons on GameOver screen
        if (gp.state == State.Over) {
            switch (code){
                case KeyEvent.VK_UP:
                    gp.ui.order--;
                    if(gp.ui.order < 0){
                        gp.ui.order = 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    gp.ui.order++;
                    if(gp.ui.order > 1){
                        gp.ui.order = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gp.ui.order == 0){
                        gp.state = State.Game;
                        gp.retry();
                    }
                    if(gp.ui.order == 1){
                        System.exit(0);
                    }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code){
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
        }
    }
    
}