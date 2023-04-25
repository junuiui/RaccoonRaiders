package com.group21;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathfinderTest {

    @Test
    public void testInstantiateNode() {
        GamePanel gp = new GamePanel();
        Pathfinder pf = new Pathfinder(gp);
        pf.instantiateNode();
        assertEquals(gp.maxScreenCol, pf.node.length);
        assertEquals(gp.maxScreenRow, pf.node[0].length);
    }

    @Test
    public void testResetNodes() {
        GamePanel gp = new GamePanel();
        Pathfinder pf = new Pathfinder(gp);
        pf.instantiateNode();
        pf.node[0][0].checked = true;
        pf.node[0][0].solid = true;
        pf.openList.add(pf.node[0][0]);
        pf.pathList.add(pf.node[0][0]);
        pf.goalReached = true;
        pf.step = 5;
        pf.resetNodes();
        for (int i = 0; i < gp.maxScreenCol; i++) {
            for (int j = 0; j < gp.maxScreenRow; j++) {
                assertFalse(pf.node[i][j].checked);
                assertFalse(pf.node[i][j].solid);
            }
        }
        assertTrue(pf.openList.isEmpty());
        assertTrue(pf.pathList.isEmpty());
        assertFalse(pf.goalReached);
        assertEquals(0, pf.step);
    }

    @Test
    public void testSetNodes() {
        GamePanel gp = new GamePanel();
        Pathfinder pf = new Pathfinder(gp);
        pf.instantiateNode();
        pf.setNodes(0, 0, gp.maxScreenCol-1, gp.maxScreenRow-1);
        assertNotNull(pf.startNode);
        assertNotNull(pf.goalNode);
        assertEquals(pf.node[0][0], pf.startNode);
        assertEquals(pf.node[gp.maxScreenCol-1][gp.maxScreenRow-1], pf.goalNode);
        assertTrue(pf.openList.contains(pf.startNode));
    }

    @Test
    public void testGetCost() {
        GamePanel gp = new GamePanel();
        Pathfinder pf = new Pathfinder(gp);
        pf.instantiateNode();
        Node n = pf.node[0][0];
        pf.startNode = n;
        pf.goalNode = pf.node[gp.maxScreenCol-1][gp.maxScreenRow-1];
        pf.getCost(n);
        assertEquals(0, n.gCost);
        assertEquals(gp.maxScreenCol + gp.maxScreenRow - 2, n.hCost);
        assertEquals(gp.maxScreenCol + gp.maxScreenRow - 2, n.fCost);
    }
}
