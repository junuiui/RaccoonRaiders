package com.group21;

import java.util.ArrayList;

/**
 * Pathfinder class represents the A* pathfinding algorithm used to find a path from a start node to a goal node.
 * It creates nodes for every tile on the map, sets their status, and uses a cost function to determine the 
 * optimal path from start to goal.
 */
public class Pathfinder {
    
    /**
     * target {@code GamePanel} 
     */
    GamePanel gp;
    
    /**
     * A 2D array of nodes representing the grid used for pathfinding.
     */
    Node[][] node;
    
    /**
     * An ArrayList of nodes representing the open list used by the pathfinding algorithm.
     */
    ArrayList<Node> openList = new ArrayList<>();

    /**
     * An ArrayList of nodes representing the final path found by the pathfinding algorithm.
     */
    public ArrayList<Node> pathList = new ArrayList<>();

    /**
     * The starting node for the pathfinding algorithm.
     */
    Node startNode;

    /**
     * The goal node for the pathfinding algorithm.
     */
    Node goalNode; 
    
    /**
     * The current node being evaluated by the pathfinding algorithm.
     */
    Node currentNode;
    
    /**
     * A boolean representing whether or not the goal node has been reached by the pathfinding algorithm.
     */
    boolean goalReached = false;

    /**
     * An integer representing the current step of the pathfinding algorithm.
     */
    int step = 0;

    /**
     * Constructor that initializes the Pathfinder object with a target GamePanel.
     * 
     * @param gp the target GamePanel
     */
    public Pathfinder(GamePanel gp) {
        this.gp = gp;
        instantiateNode();
    }

    /**
     * Creates a node for every tile on the map.
     * The nodes are stored in a 2D array for easy access.
     */
    public void instantiateNode() {
        node = new Node[gp.maxScreenCol][gp.maxScreenRow];

        int col = 0;
        int row = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            // create a new node for the tile at the current column and row
            node[col][row] = new Node(col,row);
            col++;

            // if the column index is at its maximum value, reset it to zero and increment the row index
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Resets nodes' status and all other settings.
     * This method is used to reset the state of the pathfinding algorithm when a new path is being calculated.
     */
    public void resetNodes() {
        int col = 0;
        int row = 0;

        // Reset nodes' status
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            // set the open, checked, and solid status of the current node to false
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            // create a new node for the tile at the current column and row
            node[col][row] = new Node(col,row);

            col++;
            // if the column index is at its maximum value, reset it to zero and increment the row index
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }

        // Reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    /**
     * Sets the status of nodes.
     * 
     * @param startCol starting column
     * @param startRow starting row
     * @param goalCol goal column
     * @param goalRow goal row
     */
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {

        resetNodes();

        // Set the start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            
            // set solid node
            // check tiles
            int tileNum = gp.tm.mapArr[col][row];
            if (gp.tm.tiles.get(tileNum).collision == true) {
                node[col][row].solid = true;
            }

            // set cost
            getCost(node[col][row]);

            col++;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }

    }

    /**
     * Determines the cost for node in the pathfinding algorithm.
     * 
     * @param node the target Node
     */
    public void getCost(Node node) {
        
        // G cost
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row);
        node.gCost = xDist + yDist;
        
        // H cost
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;
        
        // F cost
        node.fCost = node.gCost + node.hCost;
        
    }

    /**
     * Searches for the best path using the pathfinding algorithm.
     * 
     * @return true if the goal has been reached, false otherwise
     */
    public boolean search() {
        while (goalReached == false && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            // check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // open the up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // open the left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // open the down node
            if (row + 1 < gp.maxScreenCol) {
                openNode(node[col][row + 1]);
            }
            // open the right node
            if (col + 1 < gp.maxScreenCol) {
                openNode(node[col + 1][row]);
            }

            // find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {

                //  check if this node's F cost is better
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }

                // if F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // if there is no node in openList, end the loop

            if (openList.size() == 0) {
                break;
            }

            // after the loop, openList[bestNodeIndex] is the next step 
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }

        return goalReached;
    }

    /**
     * Opens a node.
     * 
     * @param node the target Node
     */
    public void openNode(Node node) {
        if(node.open == false && node.checked == false && node.solid == false) {
            
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Tracks the path using the pathfinding algorithm.
     */
    public void trackThePath() {
        Node current = goalNode;

        while(current != startNode) {

            pathList.add(0, current);
            current = current.parent;
        }
    }
}
