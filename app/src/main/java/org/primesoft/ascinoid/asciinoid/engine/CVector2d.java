package org.primesoft.ascinoid.asciinoid.engine;

/**
 * 2D vector
 * Based on CVector from "Programowanie gier w OpenGL" by Dave Astle
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

    /**
     * Set the length of the vector to l
     * @param l
     * @return this object to chain operations
     */
    public final CVector2d setLength(double l) {
        return mul(l / getLength());
    }

    /**
     * Set the length of the vector to l
     * @param v
     * @param l
     * @return new vector
     */
    public final static CVector2d setLength(CVector2d v, double l) {
        return mul(v, l / v.getLength());
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
     * @return new vector
     */
    public final CVector2d toUnitVector() {
        double length = getLength();

        if (length == 0) {
            return new CVector2d(0, 0);
        }

        return div(this, length);
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
     * Add components
     *
     * @param vec
     * @return this object to chain operations
     */
    public final CVector2d add(CVector2d vec) {
        return add(vec.m_x, vec.m_y);
    }

    /**
     * Add components
     *
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static CVector2d add(CVector2d vec1, CVector2d vec2) {
        return add(vec1, vec2.m_x, vec2.m_y);
    }

    /**
     * Add values to components
     *
     * @param x
     * @param y
     * @return this object to chain operations
     */
    public final CVector2d add(double x, double y) {
        m_x += x;
        m_y += y;
        return this;
    }


    /**
     * Add values to components
     *
     * @param x
     * @param y
     * @return new vector
     */
    public final static CVector2d add(CVector2d vec, double x, double y) {
        return new CVector2d(vec).add(x, y);
    }

    /**
     * Add values to X component
     *
     * @param x
     * @return this object to chain operations
     */
    public final CVector2d addX(double x) {
        m_x += x;
        return this;
    }

    /**
     * Add values to Y component
     *
     * @param y
     * @return this object to chain operations
     */
    public final CVector2d addY(double y) {
        m_y += y;
        return this;
    }

    /**
     * Add values to X component
     *
     * @param x
     * @param vec
     * @return new vector
     */
    public final static CVector2d addX(CVector2d vec, double x) {
        return new CVector2d(vec).addX(x);
    }

    /**
     * Add values to Y component
     *
     * @param y
     * @param vec
     * @return new vector
     */
    public final static CVector2d addY(CVector2d vec, double y) {
        return new CVector2d(vec).addY(y);
    }

    /**
     * Sub components
     *
     * @param vec
     * @return this object to chain operations
     */
    public final CVector2d sub(CVector2d vec) {
        return sub(vec.m_x, vec.m_y);
    }

    /**
     * Sub values of components
     *
     * @param x
     * @param y
     * @return this object to chain operations
     */
    public final CVector2d sub(double x, double y) {
        m_x -= x;
        m_y -= y;
        return this;
    }

    /**
     * Sub values of X component
     *
     * @param x
     * @return this object to chain operations
     */
    public final CVector2d subX(double x) {
        m_x -= x;
        return this;
    }

    /**
     * Sub values of Y component
     *
     * @param y
     * @return this object to chain operations
     */
    public final CVector2d subY(double y) {
        m_y -= y;
        return this;
    }

    /**
     * Sub components
     *
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static CVector2d sub(CVector2d vec1, CVector2d vec2) {
        return sub(vec1, vec2.m_x, vec2.m_y);
    }

    /**
     * Sub values of components
     *
     * @param vec
     * @param x
     * @param y
     * @return new vector
     */
    public final static CVector2d sub(CVector2d vec, double x, double y) {
        return new CVector2d(vec).sub(x, y);
    }

    /**
     * Sub values of X component
     *
     * @param vec
     * @param x
     * @return new vector
     */
    public final static CVector2d subX(CVector2d vec, double x) {
        return new CVector2d(vec).subX(x);
    }

    /**
     * Sub values of Y component
     *
     * @param vec
     * @param y
     * @return new vector
     */
    public final static CVector2d subY(CVector2d vec, double y) {
        return new CVector2d(vec).subY(y);
    }

    public final CVector2d inv() {
        m_x = -m_x;
        m_y = -m_y;

        return this;
    }

    public final static CVector2d inv(CVector2d vec) {
        return new CVector2d(vec).inv();
    }

    /**
     * Multiply vector by scallar
     *
     * @param s
     * @return this object to chain operations
     */
    public final CVector2d mul(double s) {
        m_x *= s;
        m_y *= s;
        return this;
    }

    /**
     * Multiply vector by scallar
     *
     * @param vec
     * @param s
     * @return new vector
     */
    public final static CVector2d mul(CVector2d vec, double s) {
        return new CVector2d(vec).mul(s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return this object to chain operations
     */
    public final CVector2d div(double s) {
        return mul(1 / s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return new vector
     */
    public final static CVector2d div(CVector2d vec, double s) {
        return mul(vec, 1 / s);
    }

    /**
     * Calculate vector cross product
     * NOTE: Since cross product return a vector perpendicular to plane
     * and we are operating in a 2D space this operation returns the
     * the lengthe of the perpendicular vector. If you need a full vector
     * use the new CVector(0,0, l), where l is the result of this method.
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static double cross(CVector2d vec1, CVector2d vec2) {
        return vec1.cross(vec2);
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
     * @param vec1
     * @param vec2
     * @return
     */
    public final static double dot(CVector2d vec1, CVector2d vec2) {
        return vec1.dot(vec2);
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

    /**
     * Normalizes the vector (converts to unit vector)
     * @return this to chain operations
     */
    public final CVector2d normalize() {
        double length = getLength();

        if (length == 0) {
            m_x = 0;
            m_y = 0;
        } else {
            m_x /= length;
            m_y /= length;
        }

        return this;
    }

    public final static double angle(CVector2d vec, CVector2d normal) {
        return vec.angle(normal);
    }

    public final double angle(CVector2d normal) {
        return Math.acos(dot(normal));
    }


    public final CVector2d reflection(CVector2d normal) {
        CVector2d vec = toUnitVector();
        return vec.sub(normal.mul(2.0 * vec.dot(normal))).mul(getLength());
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
        // NOTE: You need to use readonly fields to calculate the hashCode.
        
        /*long longHash = Double.doubleToRawLongBits(m_x) ^
                Double.doubleToRawLongBits(m_y);
        return (int) (longHash ^ (longHash >>> 32));*/

        return super.hashCode();
    }

    public final CVector2d clone() {
        return new CVector2d(this);
    }
}