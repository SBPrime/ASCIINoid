package org.primesoft.ascinoid.asciinoid.engine;

/**
 * Base world object
 * Based on CObject from "Programowanie gier w OpenGL" by Dave Astle
 *
 * @author SBPrime
 */
public abstract class CObject {
    private CVector m_position;

    private CVector m_velocity;

    private CVector m_acceleration;

    private double m_size;

    /**
     * Object position
     */
    public CVector getPosition() {
        return m_position;
    }

    public void setPosition(CVector position) {
        this.m_position = position;
    }

    /**
     * Object move speed
     */
    public CVector getVelocity() {
        return m_velocity;
    }

    public void setVelocity(CVector velocity) {
        this.m_velocity = velocity;
    }

    /**
     * Object acceleration
     */
    public CVector getAcceleration() {
        return m_acceleration;
    }

    public void setAcceleration(CVector acceleration) {
        this.m_acceleration = acceleration;
    }

    /**
     * Collision sphere radious
     */
    public double getSize() {
        return m_size;
    }

    public void setSize(double size) {
        this.m_size = size;
    }

    /**
     * Load object data into memory
     */
    public abstract void load();

    /**
     * Unload object parameters
     */
    public abstract void unload();

    /**
     * Draw the object
     */
    public abstract void draw();

    /**
     * Animate object
     * @param deltaTime the ellapsed time in millisecconds
     */
    public abstract void animate(double deltaTime);
}