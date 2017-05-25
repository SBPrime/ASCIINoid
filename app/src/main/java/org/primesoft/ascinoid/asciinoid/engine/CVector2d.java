package org.primesoft.ascinoid.asciinoid.engine;

/**
 * 2D vector
 * Based on CVector2d from "Programowanie gier w OpenGL" by Dave Astle
 *
 * @author SBPrime
 */
public final class CVector2d {
    /**
     * Empty (0 vector)
     */
    public static final CVector2d EMPTY = new CVector2d();
    public static final CVector2d UNIT_X = new CVector2d(1, 0);
    public static final CVector2d UNIT_Y = new CVector2d(0, 1);

    private double m_x;
    private double m_y;


    public final double getX() {
        return m_x;
    }

    public final void setX(double x) {
        this.m_x = x;
    }

    public final double getY() {
        return m_y;
    }

    public final void setY(double y) {
        this.m_y = y;
    }

    public final double getLength() {
        return Math.sqrt(m_x * m_x + m_y * m_y);
    }

    private CVector2d() {
        this(0, 0);
    }

    public CVector2d(double x, double y) {
        set(x, y);
    }

    /**
     * Copy constructor
     *
     * @param vec
     */
    public CVector2d(CVector2d vec) {
        this(vec.m_x, vec.m_y);
    }

    /**
     * Create new unit vector based on this vector
     *
     * @return
     */
    public final CVector2d toUnitVector() {
        double length = getLength();

        if (length == 0) {
            return new CVector2d(0, 0);
        }

        return div(length);
    }

    /**
     * Set vector components to values from source.
     *
     * @param vec
     */
    public final void set(CVector2d vec) {
        set(vec.m_x, vec.m_y);
    }

    /**
     * Set vector components to provided values
     *
     * @param x
     * @param y
     */
    public final void set(double x, double y) {
        m_x = x;
        m_y = y;
    }

    /**
     * Add components and create new Vector
     *
     * @param vec
     * @return New vecotr instance
     */
    public final CVector2d add(CVector2d vec) {
        return add(vec.m_x, vec.m_y);
    }

    /**
     * Add values to components and create new Vector
     *
     * @param x
     * @param y
     * @return New vector instance
     */
    public final CVector2d add(double x, double y) {
        return new CVector2d(x + m_x, y + m_y);
    }

    /**
     * Add values to X component and create new Vector
     *
     * @param x
     * @return New vector instance
     */
    public final CVector2d addX(double x) {
        return new CVector2d(x + m_x, m_y);
    }

    /**
     * Add values to Y component and create new Vector
     *
     * @param y
     * @return New vector instance
     */
    public final CVector2d addY(double y) {
        return new CVector2d(m_x, y + m_y);
    }

    /**
     * Sub components and create new Vector
     *
     * @param vec
     * @return New vecotr instance
     */
    public final CVector2d sub(CVector2d vec) {
        return sub(vec.m_x, vec.m_y);
    }

    /**
     * Sub values of components and create new Vector
     *
     * @param x
     * @param y
     * @return New vector instance
     */
    public final CVector2d sub(double x, double y) {
        return new CVector2d(x - m_x, y - m_y);
    }

    /**
     * Sub values of X component and create new Vector
     *
     * @param x
     * @return New vector instance
     */
    public final CVector2d subX(double x) {
        return new CVector2d(x - m_x, m_y);
    }

    /**
     * Sub values of Y component and create new Vector
     *
     * @param y
     * @return New vector instance
     */
    public final CVector2d subY(double y) {
        return new CVector2d(m_x, y + m_y);
    }

    /**
     * Multiply vector by scallar
     *
     * @param s
     * @return
     */
    public final CVector2d mul(double s) {
        return new CVector2d(m_x * s, m_y * s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return
     */
    public final CVector2d div(double s) {
        return mul(1 / s);
    }

    /**
     * Calculate vector cross product
     * NOTE: Since cross product return a vector perpendicular to plane
     * and we are operating in a 2D space this operation returns the
     * the lengthe of the perpendicular vector. If you need a full vector
     * use the new CVector(0,0, l), where l is the result of this method.
     * @param vec
     * @return
     */
    public final double cross(CVector2d vec) {
        return m_x * vec.m_y - m_y * vec.m_x;
    }

    /**
     * Calculate vector dot product
     *
     * @param vec
     * @return
     */
    public final double dot(CVector2d vec) {
        return m_x * vec.m_x + m_y * vec.m_x;
    }


    public final void normalize() {
        double length = getLength();

        if (length == 0) {
            m_x = 0;
            m_y = 0;
        } else {
            m_x /= length;
            m_y /= length;
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof CVector2d)) {
            return false;
        }

        CVector2d other = (CVector2d) obj;

        return m_x == other.m_x &&
                m_y == other.m_y;
    }

    @Override
    public final int hashCode() {
        long longHash = Double.doubleToRawLongBits(m_x) ^
                Double.doubleToRawLongBits(m_y);
        return (int) (longHash ^ (longHash >>> 32));
    }
}