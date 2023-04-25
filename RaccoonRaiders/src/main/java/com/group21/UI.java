package com.group21;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
/**
 * The UI class provides methods for creating and managing the user interface.
 */
public class UI {
  /**
   * target {@code GamePanel}
   */
  protected GamePanel gp;
  /**
   * The target 2D graphics for UI
   */
  protected Graphics2D g2;
  /**
   * a new front with named retro.
   */
  protected Font retroFont;
  /**
   * The bufferedImage variable to load image of heart(health).
   */
  protected BufferedImage heartImage;
  /**
   * The bufferedImage variable to load background image of title page.
   */
  protected BufferedImage titleImage;
  /**
   * The bufferedImage variable to load image of raccoon.
   */
  protected BufferedImage raccoonImage;
  /**
   * A operation variable
   */
  protected int order = 0;
  /**
   * the stopwatch to record the game time.
   */
  protected Stopwatch stopwatch;
  /**
   * The variable to input the stopwatch value
   */
  protected double timeElapsed;

  /**
   * Constructor.
   *
   * @param gp target GamePanel to draw on
   */
  public UI(GamePanel gp) {
    this.gp = gp;

    stopwatch = new Stopwatch();

    // Background Title Image
    try {
      titleImage = ImageIO.read(
          getClass().getResourceAsStream("/UI_image/titleImage.png"));
      raccoonImage = ImageIO.read(
          getClass().getResourceAsStream("/raccoon_image/raccoon_down_1.png"));
      heartImage =
          ImageIO.read(getClass().getResourceAsStream("/UI_image/heart.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Create font
    try {
      InputStream is = getClass().getResourceAsStream("/font/Retro_Gaming.ttf");
      retroFont = Font.createFont(Font.TRUETYPE_FONT, is);
    } catch (FontFormatException e) {

      e.printStackTrace();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }
  /**
   * Draw the state of the game character.
   *
   * @param g2 Graphics2D object to draw on
   */
  public void draw(Graphics2D g2) {
    this.g2 = g2;
    g2.setFont(retroFont);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25f));
    g2.setColor(Color.white);

    // HEART
    g2.drawImage(heartImage, 16, 3, 48, 48, null);
    g2.drawString("x " + gp.student.heart, 74, 40);

    // SCORE
    g2.drawString("Score: " + gp.student.score, 16, 80);

    // Draw Time Elapsed
    if (gp.state == State.Game) {
      if (!stopwatch.isRunning()) {
        stopwatch.start();
      }
      if (stopwatch.isPaused()) {
        stopwatch.resume();
      }

      timeElapsed = stopwatch.elapsed() / 100000000;
      timeElapsed /= 100;
      timeElapsed *= 10;
    }
    g2.drawString("Time: " + String.format("%.1f", timeElapsed), 16, 110);

    // TITLE STATE
    if (gp.state == State.Title) {
      drawTitle();
    }
    // PAUSE
    if (gp.state == State.Pause) {
      drawPause();
      stopwatch.pause();
    }
    // GAME OVER
    if (gp.state == State.Over) {
      drawGameOver();
      stopwatch.stop();
    }
  }

  /**
   * Creates a new window with the specified title and dimensions.
   */
  public void drawTitle() {
    // Background
    g2.drawImage(titleImage, 0, 0, 1280, 768, null);

    // TITLE
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 100f));
    String txt = "RACCOON RAIDERS";
    int x = getCenterX(txt);
    int y = gp.tileSize * 9;

    g2.setColor(Color.gray);
    g2.drawString(txt, x + 5, y + 5);
    g2.setColor(Color.white);
    g2.drawString(txt, x, y);

    // IMAGE OF CHARACTER
    x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2 - 600;
    y += gp.tileSize * 2 + 60;

    g2.drawImage(raccoonImage, 0, 550, gp.tileSize * 14, gp.tileSize * 14,
                 null);

    // OPTIONS
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
    txt = "NEW GAME";
    x = getCenterX(txt) + 150;
    y += gp.tileSize * 2;
    g2.drawString(txt, x, y);
    if (order == 0) {
      g2.drawString(">", x - gp.tileSize * 3, y);
    }

    txt = "QUIT";
    x = getCenterX(txt) + 150;
    y += gp.tileSize * 4;
    g2.drawString(txt, x, y);
    if (order == 1) {
      g2.drawString(">", x - gp.tileSize * 2 - 28, y);
    }
  }
  /**
   * Creates a new interface on the Pause state.
   */
  public void drawPause() {
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    String text;
    int x;
    int y;
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 150f));
    text = "PAUSED";
    x = getCenterX(text);
    y = gp.tileSize * 14;

    g2.setColor(Color.white);
    g2.drawString(text, x, y);
  }

  /**
   * Get the middle point of x-axis in the window.
   *
   * @param txt a String with different length
   * @return the index of the middle x in pixel
   */
  public int getCenterX(String txt) {
    int len = (int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();
    int x = gp.screenWidth / 2 - len / 2;
    return x;
  }
  /**
   * Creates a new interface on the GameOver state.
   */
  public void drawGameOver() {
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    int x;
    int y;
    String text;
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 150f));
    text = "GAME OVER";
    x = getCenterX(text);
    y = gp.tileSize * 15;

    // GAME OVER
    g2.setColor(Color.white);
    g2.drawString(text, x, y - 230);

    // SCORE
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50f));
    text = "Score: " + gp.student.score;
    x = getCenterX(text);
    y = gp.tileSize * 15;
    g2.drawString(text, x, y - 135);

    // TIME ELAPSED
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50f));
    text = "Time: " + String.format("%.1f", timeElapsed);
    x = getCenterX(text);
    y = gp.tileSize * 15;
    g2.drawString(text, x, y - 85);

    // RETRY
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50f));
    text = "RETRY";
    x = getCenterX(text);
    y = gp.tileSize * 15;
    g2.drawString(text, x, y);
    if (order == 0) {
      g2.drawString(">", x - gp.tileSize * 3, y);
    }

    // EXIT
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50f));
    text = "QUIT";
    x = getCenterX(text);
    y += 55;
    g2.drawString(text, x, y);
    if (order == 1) {
      g2.drawString(">", x - gp.tileSize * 3, y);
    }
  }
}
