package com.group21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StopWatchTest {

    private Stopwatch time;

    @BeforeEach
    public void setup(){
        time = new Stopwatch();
    }

    /**
     * Test the start time expected: 0
     */
    @Test
    public void startTimeTest(){
        assertEquals(0, time.elapsed());
    }

    @Test
    public void startTest(){
        time.start();

        assertTrue(time.isRunning());
        assertNotEquals(0, time.elapsed());
    }

    @Test
    public void pauseTest(){
        time.start();
        time.pause();

        assertTrue(time.isPaused());
        assertNotEquals(0, time.elapsed());
    }

    @Test
    public void resumeTest(){
        time.start();
        time.resume();

        assertTrue(time.isRunning());
        assertNotEquals(0, time.elapsed());
    }

    @Test
    public void stopTest(){
        time.start();
        long timeElapsed = time.stop();

        assertFalse(time.isRunning());
        assertNotEquals(0, timeElapsed);
    }

}
