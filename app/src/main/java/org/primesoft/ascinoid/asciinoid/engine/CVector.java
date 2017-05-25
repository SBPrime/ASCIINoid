package org.primesoft.ascinoid.asciinoid.engine;

/**
 * 3D vector
 * Based on CVector from "Programowanie gier w OpenGL" by Dave Astle
 *
 * @author SBPrime
 */
public class CVector {
    /**
     * Empty (0 vector)
     */
    public static final CVector EMPTY = new CVector();
    public static final CVector UNIT_X = new CVector(1, 0, 0);
    public static final CVector UNIT_Y = new CVector(0, 1, 0);
    public static final CVector UNIT_Z = new CVector(0, 0, 1);

    private double m_x;
    private double m_y;
    private double m_z;


    public double getX() {
        return m_x;
    }

    public void setX(double x) {
        this.m_x = x;
    }

    public double getY() {
        return m_y;
    }

    public void setY(double y) {
        this.m_y = y;
    }

    public double getZ() {
        return m_z;
    }

    public void setZ(double z) {
        this.m_z = z;
    }

    public double getLength() {
        return Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
    }

    private CVector() {
        this(0, 0, 0);
    }

    public CVector(double x, double y, double z) {
        set(x, y, z);
    }

    /**
     * Copy constructor
     *
     * @param vec
     */
    public CVector(CVector vec) {
        this(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Create new unit vector based on this vector
     *
     * @return
     */
    public CVector toUnitVector() {
        double length = getLength();

        if (length == 0) {
            return new CVector(0, 0, 0);
        }

        return div(length);
    }

    /**
     * Set vector components to values from source.
     *
     * @param vec
     */
    public void set(CVector vec) {
        set(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Set vector components to provided values
     *
     * @param x
     * @param y
     * @param z
     */
    public void set(double x, double y, double z) {
        m_x = x;
        m_y = y;
        m_z = z;
    }

    /**
     * Add components and create new Vector
     *
     * @param vec
     * @return New vecotr instance
     */
    public CVector add(CVector vec) {
        return add(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Add values to components and create new Vector
     *
     * @param x
     * @param y
     * @param z
     * @return New vector instance
     */
    public CVector add(double x, double y, double z) {
        return new CVector(x + m_x, y + m_y, z + m_z);
    }

    /**
     * Add values to X component and create new Vector
     *
     * @param x
     * @return New vector instance
     */
    public CVector addX(double x) {
        return new CVector(x + m_x, m_y, m_z);
    }

    /**
     * Add values to Y component and create new Vector
     *
     * @param y
     * @return New vector instance
     */
    public CVector addY(double y) {
        return new CVector(m_x, y + m_y, m_z);
    }

    /**
     * Add values to Z component and create new Vector
     *
     * @param z
     * @return New vector instance
     */
    public CVector addZ(double z) {
        return new CVector(m_x, m_y, z + m_z);
    }

    /**
     * Sub components and create new Vector
     *
     * @param vec
     * @return New vecotr instance
     */
    public CVector sub(CVector vec) {
        return sub(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Sub values to components and create new Vector
     *
     * @param x
     * @param y
     * @param z
     * @return New vector instance
     */
    public CVector sub(double x, double y, double z) {
        return new CVector(x + m_x, y + m_y, z + m_z);
    }

    /**
     * Sub values to X component and create new Vector
     *
     * @param x
     * @return New vector instance
     */
    public CVector subX(double x) {
        return new CVector(x + m_x, m_y, m_z);
    }

    /**
     * Sub values to Y component and create new Vector
     *
     * @param y
     * @return New vector instance
     */
    public CVector subY(double y) {
        return new CVector(m_x, y + m_y, m_z);
    }

    /**
     * Sub values to Z component and create new Vector
     *
     * @param z
     * @return New vector instance
     */
    public CVector subZ(double z) {
        return new CVector(m_x, m_y, z + m_z);
    }

    /**
     * Multiply vector by scallar
     *
     * @param s
     * @return
     */
    public CVector mul(double s) {
        return new CVector(m_x * s, m_y * s, m_z * s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return
     */
    public CVector div(double s) {
        return mul(1 / s);
    }

    /**
     * Calculate vector cross product
     *
     * @param vec
     * @return
     */
    public CVector cross(CVector vec) {
        return new CVector(m_y * vec.m_z - m_z * vec.m_y,
                m_z * vec.m_x - m_x * vec.m_z,
                m_x * vec.m_y - m_y * vec.m_x);
    }

    /**
     * Calculate vector dot product
     *
     * @param vec
     * @return
     */
    public double dot(CVector vec) {
        return m_x * vec.m_x + m_y * vec.m_x + m_z * vec.m_z;
    }


    public void normalize() {
        double length = getLength();

        if (length == 0) {
            m_x = 0;
            m_y = 0;
            m_z = 0;
        }
        else {
            m_x /= length;
            m_y /= length;
            m_z /= length;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CVector)) {
            return false;
        }

        CVector other = (CVector) obj;

        return m_x == other.m_x &&
                m_y == other.m_y &&
                m_z == other.m_z;
    }

    @Override
    public int hashCode() {
        long longHash = Double.doubleToRawLongBits(m_x) ^
                Double.doubleToRawLongBits(m_y) ^
                Double.doubleToRawLongBits(m_z);
        return (int) (longHash ^ (longHash >>> 32));
    }
}