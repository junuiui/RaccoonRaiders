package com.group21;

/**
 * AssetSetter class
 * Setting rewards, punishments, and raccoons onto GamePanel
 */

public class AssetSetter {

  /**
   * target {@code GamePanel} 
   */
  private GamePanel gp;

  /**
   * Constructor.
   *
   * @param gp Game panel to be updated
   */
  public AssetSetter(GamePanel gp) { this.gp = gp; }

  /**
   * Sets the items locations.
   */
  public void setObject() {

    int XPositions[] = {1, 1, 23, 11, 8, 35, 36, 13, 24};
    int YPositions[] = {22, 7, 3, 1, 13, 16, 12, 14, 11};

    for (int i = 0; i < 9; i++) {
      gp.rewards[i] = new Coffee();
      gp.rewards[i].xPosition = XPositions[i] * gp.tileSize;
      gp.rewards[i].yPosition = YPositions[i] * gp.tileSize;
    }

    gp.rewards[9] = new BubbleTea();
    gp.rewards[9].xPosition = 15 * gp.tileSize;
    gp.rewards[9].yPosition = 22 * gp.tileSize;

    // set up punishments
    int XPositions2[] = {5, 12, 16, 21, 30, 36, 38, 8, 10, 13};
    int YPositions2[] = {6, 8, 20, 16, 19, 15, 6, 18, 20, 21};

    for (int i = 0; i < 10; i++) {
      gp.punishments[i] = new Punishment();
      gp.punishments[i].xPosition = XPositions2[i] * gp.tileSize;
      gp.punishments[i].yPosition = YPositions2[i] * gp.tileSize;
    }
  }

  /**
   * Set the Raccoons locations.
   */
  public void setRaccoon() {
    int Xarrs[] = {35, 14, 20};
    int Yarrs[] = {9, 18, 5};

    for (int i = 0; i < 3; i++) {
      gp.raccoons[i] = new Raccoon(gp);
      gp.raccoons[i].xPosition = Xarrs[i] * gp.tileSize;
      gp.raccoons[i].yPosition = Yarrs[i] * gp.tileSize;
    }
  }
}
