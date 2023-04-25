package com.group21;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.Color;
/**
 * The GamePanel class represents the main panel for a simple game.
 * It contains all the game logic and rendering, and handles user input.
 */
public class GamePanel extends JPanel implements Runnable{
    
    /**
     * The size of each tile (Deafult: 32)
     */
    protected final int tileSize = 32;

    /**
     * The number of columns (horizontal tiles) on the map (Deafult: 40)
     */
    protected final int maxScreenCol = 40;

    /**
     * The number of rows (vertical tiles) on the map (Deafult: 24)
     */
    protected final int maxScreenRow = 24;

    /**
     * Screen Width (Default: 1280 pixels)
     */
    protected final int screenWidth = tileSize * maxScreenCol;    

    /**
     * Screen Height (Default: 768 pixels)
     */
    protected final int screenHeight = tileSize * maxScreenRow;   // 768 pixels
    
    /**
     * Frames per Seconds (Deafult: 60)
     */
    protected final int FPS = 60;

    /**
     * Game {@code Thread}
     */
    protected Thread gameThread;

    /**
     * {@code KeyHandler} to interact with keyboard inputs
     */
    protected KeyHandler key = new KeyHandler(this);

    /**
     * Main {@code Characters} which is the {@code Student}
     */
    protected Student student = new Student(this, key);

    /**
     * {@code Sound} to play music 
     */
    protected Sound sound = new Sound();

    /**
     * {@code UI} on screen
     */
    protected UI ui = new UI (this);

    /**
     * {@code TileManger}
     */
    protected TileManager tm = new TileManager(this);

    /**
     * Collision Checker
     */
    protected CollisionChecker cChecker = new CollisionChecker(this);

    /**
     * Path finder
     */
    protected Pathfinder pFinder = new Pathfinder(this);

    /**
     * Assset Setter
     */
    protected AssetSetter setter = new AssetSetter(this);

    /**
     * Rewards {@code Items}
     */
    protected Items rewards[] = new Items[10];

    /**
     * Punishments {@code Items}
     */
    protected Items punishments[] = new Items[10];

    /**
     * Enemy Raccoons {@code Characters}
     */
    protected Characters raccoons[] = new Characters[5];

    /**
     * {@code Portal} to exit after all rewards ({@code Coffee}) are collected
     */
    protected Portal portal = new Portal();

    /**
     * The Game {@code State}
     */
    protected State state;

    /**
     * Default Constructor. Creates GamePanel 
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // setup size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true); // let GamePanel focus to recieve key input
        this.state = State.Title;
    }

    /**
     * Setting up the items and enemies locations on panel.
     */
    public void setupGame(){
        setter.setObject();
        setter.setRaccoon();
        state = State.Title;
        playMusic(1);
    }

    /**
     * Let Thread start the game.
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Resets the character's positions and values.
     */
    public void retry() {
        student.setDefaultValues();
        student.restoreHealthAndScore();
        setter.setObject();
        setter.setRaccoon();
    }

    @Override
    public void run() {

        // game loop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
    
        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                this.update();
                this.repaint();
                delta--;
            }
        }
    }

    /**
     * Updates the characters.
     */
    public void update(){
        if(state == State.Game) {
            // PLAYER
            student.update();

            //ENEMIES
            for (int i = 0; i < raccoons.length; i++) {
                if (raccoons[i] != null) {
                    raccoons[i].update();
                }
            }
        }
    }

    /**
     * Draws the components on the panel.
     *
     * @param g Graphic 
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (state == State.Title)
        {
            ui.draw(g2);
        }
        else {
            //TITLE
            tm.draw(g2);

            // rewards and portal 
            for (int i = 0; i < rewards.length; i++){
                if (rewards[i] != null)
                    rewards[i].draw(g2, this);
                
                // create portal if coffees are all collected
                if (student.collectAllChecker()){
                    portal.xPosition = 38 * this.tileSize;
                    portal.yPosition = 1 * this.tileSize;
                    portal.draw(g2, this);
                }
            }

            //PUNISHMENTS
            for (int i = 0; i < rewards.length; i++){
                if (punishments[i] != null)
                punishments[i].draw(g2, this);
            }
            
            //ENEMIES
            for(int i = 0; i < raccoons.length; i++) {
                if (raccoons[i] != null)
                    raccoons[i].draw(g2);
            }

            //UI
            ui.draw(g2);
            
            //PLAYER
            student.draw(g2);
        }
        g2.dispose();   // dispose of this graphics contxt and release any system resources that it is using  
    }

    /**
     * Play the music
     *
     * @param i index number of target music stream
     */
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    /**
     * Stop the music
     */
    public void stopMusic()
    {
        sound.stop();
    }
}
