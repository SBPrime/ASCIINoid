package org.primesoft.ascinoid.asciinoid.engine;

/**
 * Hi res timer interface.
 * Based on HiResTimer from "Programowanie gier w OpenGL" by Dave Astle
 *
 * @author SBPrime
 */
public interface IHiResTimer {
    void init();

    double getElapsedSeconds();

    double getFPS();
    double getFPS(long elapsedFrames);

    double lockFPS(int targetFPS);

}
