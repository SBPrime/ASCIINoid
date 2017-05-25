package org.primesoft.ascinoid.asciinoid.engine;

/**
 * 3D vector
 * Based on CVector from "Programowanie gier w OpenGL" by Dave Astle
 *
 * @author SBPrime
 */
public final class CVector {
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

    public final double getZ() {
        return m_z;
    }

    public final void setZ(double z) {
        this.m_z = z;
    }

    public final double getLength() {
        return Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
    }

    /**
     * Set the length of the vector to l
     * @param l
     * @return this object to chain operations
     */
    public final CVector setLength(double l) {
        return mul(l / getLength());
    }

    /**
     * Set the length of the vector to l
     * @param v
     * @param l
     * @return new vector
     */
    public final static CVector setLength(CVector v, double l) {
        return mul(v, l / v.getLength());
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
     * @return new vector
     */
    public final CVector toUnitVector() {
        double length = getLength();

        if (length == 0) {
            return new CVector(0, 0, 0);
        }

        return div(this, length);
    }

    /**
     * Set vector components to values from source.
     *
     * @param vec
     */
    public final void set(CVector vec) {
        set(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Set vector components to provided values
     *
     * @param x
     * @param y
     * @param z
     */
    public final void set(double x, double y, double z) {
        m_x = x;
        m_y = y;
        m_z = z;
    }

    /**
     * Add components
     *
     * @param vec
     * @return this object to chain operations
     */
    public final CVector add(CVector vec) {
        return add(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Add components
     *
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static CVector add(CVector vec1, CVector vec2) {
        return add(vec1, vec2.m_x, vec2.m_y, vec2.m_z);
    }

    /**
     * Add values to components
     *
     * @param x
     * @param y
     * @param z
     * @return this object to chain operations
     */
    public final CVector add(double x, double y, double z) {
        m_x += x;
        m_y += y;
        m_z += z;
        return this;
    }


    /**
     * Add values to components
     *
     * @param x
     * @param y
     * @param z
     * @return new vector
     */
    public final static CVector add(CVector vec, double x, double y, double z) {
        return new CVector(vec).add(x, y, z);
    }

    /**
     * Add values to X component
     *
     * @param x
     * @return this object to chain operations
     */
    public final CVector addX(double x) {
        m_x += x;
        return this;
    }

    /**
     * Add values to Y component
     *
     * @param y
     * @return this object to chain operations
     */
    public final CVector addY(double y) {
        m_y += y;
        return this;
    }

    /**
     * Add values to Z component
     *
     * @param z
     * @return this object to chain operations
     */
    public final CVector addZ(double z) {
        m_z += z;
        return this;
    }

    /**
     * Add values to X component
     *
     * @param x
     * @param vec
     * @return new vector
     */
    public final static CVector addX(CVector vec, double x) {
        return new CVector(vec).addX(x);
    }

    /**
     * Add values to Y component
     *
     * @param y
     * @param vec
     * @return new vector
     */
    public final static CVector addY(CVector vec, double y) {
        return new CVector(vec).addY(y);
    }

    /**
     * Add values to Z component
     *
     * @param z
     * @param vec
     * @return new vector
     */
    public final static CVector addZ(CVector vec, double z) {
        return new CVector(vec).addZ(z);
    }

    /**
     * Sub components
     *
     * @param vec
     * @return this object to chain operations
     */
    public final CVector sub(CVector vec) {
        return sub(vec.m_x, vec.m_y, vec.m_z);
    }

    /**
     * Sub values of components
     *
     * @param x
     * @param y
     * @param z
     * @return this object to chain operations
     */
    public final CVector sub(double x, double y, double z) {
        m_x -= x;
        m_y -= y;
        m_z -= z;
        return this;
    }

    /**
     * Sub values of X component
     *
     * @param x
     * @return this object to chain operations
     */
    public final CVector subX(double x) {
        m_x -= x;
        return this;
    }

    /**
     * Sub values of Y component
     *
     * @param y
     * @return this object to chain operations
     */
    public final CVector subY(double y) {
        m_y -= y;
        return this;
    }

    /**
     * Sub values of Z component
     *
     * @param z
     * @return this object to chain operations
     */
    public final CVector subZ(double z) {
        m_z -= z;
        return this;
    }

    /**
     * Sub components
     *
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static CVector sub(CVector vec1, CVector vec2) {
        return sub(vec1, vec2.m_x, vec2.m_y, vec2.m_z);
    }

    /**
     * Sub values of components
     *
     * @param vec
     * @param x
     * @param y
     * @param z
     * @return new vector
     */
    public final static CVector sub(CVector vec, double x, double y, double z) {
        return new CVector(vec).sub(x, y, z);
    }

    /**
     * Sub values of X component
     *
     * @param vec
     * @param x
     * @return new vector
     */
    public final static CVector subX(CVector vec, double x) {
        return new CVector(vec).subX(x);
    }

    /**
     * Sub values of Y component
     *
     * @param vec
     * @param y
     * @return new vector
     */
    public final static CVector subY(CVector vec, double y) {
        return new CVector(vec).subY(y);
    }

    /**
     * Sub values of Z component
     *
     * @param vec
     * @param z
     * @return new vector
     */
    public final static CVector subZ(CVector vec, double z) {
        return new CVector(vec).subZ(z);
    }

    public final CVector inv() {
        m_x = -m_x;
        m_y = -m_y;
        m_z = -m_z;

        return this;
    }

    public final static CVector inv(CVector vec) {
        return new CVector(vec).inv();
    }

    /**
     * Multiply vector by scallar
     *
     * @param s
     * @return this object to chain operations
     */
    public final CVector mul(double s) {
        m_x *= s;
        m_y *= s;
        m_z *= s;
        return this;
    }

    /**
     * Multiply vector by scallar
     *
     * @param vec
     * @param s
     * @return new vector
     */
    public final static CVector mul(CVector vec, double s) {
        return new CVector(vec).mul(s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return this object to chain operations
     */
    public final CVector div(double s) {
        return mul(1 / s);
    }

    /**
     * Divide vector by scallar
     *
     * @param s
     * @return new vector
     */
    public final static CVector div(CVector vec, double s) {
        return mul(vec, 1 / s);
    }

    /**
     * Calculate vector cross product
     *
     * @param vec1
     * @param vec2
     * @return new vector
     */
    public final static CVector cross(CVector vec1, CVector vec2) {
        return vec1.cross(vec2);
    }

    /**
     * Calculate vector cross product
     *
     * @param vec
     * @return new vector
     */
    public final CVector cross(CVector vec) {
        return new CVector(m_y * vec.m_z - m_z * vec.m_y,
                m_z * vec.m_x - m_x * vec.m_z,
                m_x * vec.m_y - m_y * vec.m_x);
    }

    /**
     * Calculate vector dot product
     *
     * @param vec1
     * @param vec2
     * @return
     */
    public final static double dot(CVector vec1, CVector vec2) {
        return vec1.dot(vec2);
    }

    /**
     * Calculate vector dot product
     *
     * @param vec
     * @return
     */
    public final double dot(CVector vec) {
        return m_x * vec.m_x + m_y * vec.m_x + m_z * vec.m_z;
    }

    /**
     * Normalizes the vector (converts to unit vector)
     * @return this to chain operations
     */
    public final CVector normalize() {
        double length = getLength();

        if (length == 0) {
            m_x = 0;
            m_y = 0;
            m_z = 0;
        } else {
            m_x /= length;
            m_y /= length;
            m_z /= length;
        }

        return this;
    }

    public final static double angle(CVector vec, CVector normal) {
        return vec.angle(normal);
    }

    public final double angle(CVector normal) {
        return Math.acos(dot(normal));
    }


    public final CVector reflection(CVector normal) {
        CVector vec = toUnitVector();
        return vec.sub(normal.mul(2.0 * vec.dot(normal))).mul(getLength());
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof CVector)) {
            return false;
        }

        CVector other = (CVector) obj;

        return m_x == other.m_x &&
                m_y == other.m_y &&
                m_z == other.m_z;
    }

    @Override
    public final int hashCode() {
        // NOTE: You need to use readonly fields to calculate the hashCode.

        /*long longHash = Double.doubleToRawLongBits(m_x) ^
                Double.doubleToRawLongBits(m_y) ^
                Double.doubleToRawLongBits(m_z);
        return (int) (longHash ^ (longHash >>> 32));*/

        return super.hashCode();
    }

    public final CVector clone() {
        return new CVector(this);
    }
}