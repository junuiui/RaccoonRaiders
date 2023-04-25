package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssetTest {

  private GamePanel gp;
  private AssetSetter assetSetter;

  @BeforeEach
  public void setUp() {
    gp = new GamePanel(); // Create an instance of GamePanel for testing
    assetSetter = new AssetSetter(gp); // Create an instance of AssetSetter
  }

  @Test
  public void testSetObject() {
    assetSetter.setObject(); // Call the setObject() method

    // Assert that the rewards array is properly updated
    assertEquals(gp.rewards[0].xPosition, 1 * gp.tileSize);
    assertEquals(gp.rewards[0].yPosition, 22 * gp.tileSize);
    assertEquals(gp.rewards[1].xPosition, 1 * gp.tileSize);
    assertEquals(gp.rewards[1].yPosition, 7 * gp.tileSize);
    assertEquals(gp.rewards[2].xPosition, 23 * gp.tileSize);
    assertEquals(gp.rewards[2].yPosition, 3 * gp.tileSize);
    assertEquals(gp.rewards[3].xPosition, 11 * gp.tileSize);
    assertEquals(gp.rewards[3].yPosition, 1 * gp.tileSize);
    assertEquals(gp.rewards[9].xPosition, 15 * gp.tileSize);
    assertEquals(gp.rewards[9].yPosition, 22 * gp.tileSize);

    // Assert that the punishments array is properly updated
    assertEquals(gp.punishments[0].xPosition, 5 * gp.tileSize);
    assertEquals(gp.punishments[0].yPosition, 6 * gp.tileSize);
    assertEquals(gp.punishments[1].xPosition, 12 * gp.tileSize);
    assertEquals(gp.punishments[1].yPosition, 8 * gp.tileSize);
    assertEquals(gp.punishments[2].xPosition, 16 * gp.tileSize);
    assertEquals(gp.punishments[2].yPosition, 20 * gp.tileSize);
    assertEquals(gp.punishments[3].xPosition, 21 * gp.tileSize);
    assertEquals(gp.punishments[3].yPosition, 16 * gp.tileSize);
    assertEquals(gp.punishments[9].xPosition, 13 * gp.tileSize);
    assertEquals(gp.punishments[9].yPosition, 21 * gp.tileSize);
  }
}
