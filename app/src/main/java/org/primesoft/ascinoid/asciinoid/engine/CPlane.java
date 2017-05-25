package org.primesoft.ascinoid.asciinoid.engine;

/**
 * 2D plane
 * Based on CPlane from "Programowanie gier w OpenGL" by Dave Astle
 *
 * Ax + By + Cz - D = 0
 *
 * @author SBPrime
 */
public class CPlane {
    /**
     * Vector normal to plane
     */
    private final CVector m_normal;

    /**
     * Plane offset
     */
    private final double m_offset;

    public CVector getN() {
        return m_normal;
    }

    public double getD() {
        return m_offset;
    }

    public CPlane() {
        this(1, 0, 0, 0);
    }

    public CPlane(double a, double b, double c) {
        this(a, b, c, 0);
    }

    public CPlane(double a, double b, double c, double d) {
        this(new CVector(a, b, c), d);
    }

    public CPlane(CVector normal) {
        this(normal, 0);
    }

    public CPlane(CVector normal, double d) {
        m_normal = normal.clone();
        m_offset = d;
    }

    public CPlane(CPlane plane) {
        m_normal = plane.m_normal.clone();
        m_offset = plane.m_offset;
    }

    public CPlane(CVector vertexA, CVector vertexB, CVector vertexC) {
        CVector normalA = CVector.sub(vertexC, vertexA).normalize();
        CVector normalB = CVector.sub(vertexC, vertexB).normalize();

        m_normal = CVector.cross(normalA, normalB).normalize();
        m_offset = vertexA.inv().dot(m_normal);
    }

    /**
     * Checks if provided point is on plane
     *
     * @param point
     * @return
     */
    public boolean isPointOnPlane(CVector point) {
        return distanceToPlane(point) == 0;
    }

    /**
     * Calculate the distance from the plane
     *
     * @param point
     * @return
     */
    public double distanceToPlane(CVector point) {
        return CVector.dot(m_normal, point) + m_offset;
    }

    /**
     * Calculates the plane and ray intersection
     * @param rayPos
     * @param rayDir
     * @return
     */
    public CVector rayIntersection(CVector rayPos, CVector rayDir) {
        double a = CVector.dot(m_normal, rayDir);
        if (a == 0) {
            // The ray is parallel to plane
            return rayPos;
        }

        return CVector.sub(rayPos, CVector.mul(rayDir, distanceToPlane(rayPos) / a));
    }


    @Override
    public final int hashCode() {
        long longHash = Double.doubleToRawLongBits(m_offset);
        return (int) (longHash ^ (longHash >>> 32)) ^ m_normal.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CPlane)) {
            return false;
        }

        CPlane other = (CPlane) obj;

        return other.m_offset == m_offset && other.m_normal.equals(m_normal);
    }
}