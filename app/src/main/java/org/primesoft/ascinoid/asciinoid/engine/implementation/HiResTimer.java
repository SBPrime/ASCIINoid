package org.primesoft.ascinoid.asciinoid.engine.implementation;

import org.primesoft.ascinoid.asciinoid.engine.IHiResTimer;

/**
 * Hi res timer
 * Based on HiResTimer from "Programowanie gier w OpenGL" by Dave Astle
 * Warning: This class is not thread safe.
 * @author SBPrime
 */
public class HiResTimer implements IHiResTimer {
    private static final double NANO_TO_SEC = 1000000000.0;

    private long m_lastTimeElapsedSeconds;
    private long m_lastTimeFps;
    private long m_lastTimeLockFps;

    @Override
    public void init() {
        long startTime = System.nanoTime();

        m_lastTimeElapsedSeconds = startTime;
        m_lastTimeFps = startTime;
        m_lastTimeLockFps = startTime;
    }

    @Override
    public double getElapsedSeconds() {
        long currentTime = System.nanoTime();

        double seconds = (double)(currentTime - m_lastTimeElapsedSeconds) / NANO_TO_SEC;

        m_lastTimeElapsedSeconds = currentTime;

        return seconds;
    }

    @Override
    public double getFPS() {
        return getFPS(1);
    }

    @Override
    public double getFPS(long elapsedFrames) {
        long currentTime = System.nanoTime();

        double fps = NANO_TO_SEC / (double)(currentTime - m_lastTimeFps) * elapsedFrames;

        m_lastTimeFps = currentTime;

        return fps;
    }

    @Override
    public double lockFPS(int targetFPS) {
        if (targetFPS <= 0) {
            targetFPS = 1;
        }

        long currentTime;
        double fps;

        /**
         * NOTE:
         *  Since we need to manipulate on a nano second level
         *  the best way to do the sleep is using active wait.
         *  We can't use the Thread.sleep(milis).
         */
        do {
            currentTime = System.nanoTime();
            fps = NANO_TO_SEC / (double) (currentTime - m_lastTimeLockFps);
        } while (fps > targetFPS);

        m_lastTimeLockFps = currentTime;

        return fps;
    }
}
