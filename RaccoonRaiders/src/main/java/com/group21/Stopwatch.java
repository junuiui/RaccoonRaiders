package com.group21;

/**
 * StopWatch class
 */
class Stopwatch {

    /**
     * true if {@code Stopwach} is running, false otherwise
     */
    private boolean running;

    /**
     * true if {@code Stopwach} is paused, false otherwise
     */
    private boolean paused;

    /**
     * The start time
     */
    private long start;

    /**
     * The paused time
     */
    private long pausedStart;

    /**
     * The end time
     */
    private long end;

    /**
     * Constructor
     */
    public Stopwatch() {
        this.pausedStart = 0;
        this.start = 0;
        this.end = 0;
    }

    /**
     * Determines if the Stopwatch is running (could be paused)
     *
     * @return Whether the stopwatch is currently running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Whether this stopwatch is paused
     *
     * @return true if it is currently paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Starts the Stopwatch
     */
    public void start() {
        start = System.nanoTime();
        running = true;
        paused = false;
        pausedStart = -1;
    }

    /**
     * Stops the stopwatch and returns the time elapsed
     *
     * @return Stops the StopWatch
     */
    public long stop() {
        if (!isRunning()) {
            return -1;
        } else if (isPaused()) {
            running = false;
            paused = false;

            return pausedStart - start;
        } else {
            end = System.nanoTime();
            running = false;
            return end - start;
        }
    }

    /**
     * Pauses the Stopwatch
     *
     * @return The time elapsed so far
     */
    public long pause() {
        if (!isRunning()) {
            return -1;
        } else if (isPaused()) {
            return (pausedStart - start);
        } else {
            pausedStart = System.nanoTime();
            paused = true;
            return (pausedStart - start);
        }
    }

    /**
     * Resumes the StopWatch from it's paused state
     */
    public void resume() {
        if (isPaused() && isRunning()) {
            start = System.nanoTime() - (pausedStart - start);
            paused = false;
        }
    }

    /**
     * Returns the total time elapsed
     *
     * @return The total time elapsed
     */
    public long elapsed() {
        if (isRunning()) {
            if (isPaused())
                return (pausedStart - start);
            return (System.nanoTime() - start);
        } else
            return (end - start);
    }
}