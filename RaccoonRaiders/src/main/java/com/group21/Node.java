package com.group21;

/**
 * Node class used for pathfinding algorithm.
 */
public class Node {
    
    /**
     * Parent of the node.
     */
    Node parent;

    /**
     * The column position of the node.
     */
    public int col;

    /**
     * The row position of the node.
     */
    public int row;

    /**
     * The cost of moving from the start node to this node.
     */
    int gCost;

    /**
     * The estimated cost of moving from this node to the end node.
     */
    int hCost;

    /**
     * The sum of the gCost and hCost.
     */
    int fCost;

    /**
     * A boolean indicating whether this node is impassable.
     */
    boolean solid;

    /**
     * A boolean indicating whether this node is open.
     */
    boolean open;

    /**
     * A boolean indicating whether this node has been checked.
     */
    boolean checked;

    /**
     * Constructor.
     * 
     * @param col the tile column position
     * @param row the tile row position
     */
    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
