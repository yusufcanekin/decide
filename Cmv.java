public class Cmv {
    public static double EPSILON = 0.01;
    public static boolean[] computeCMV() {
        boolean[] cmv = new boolean[15];

        cmv[0]  = lic0();
        cmv[1]  = lic1();
        cmv[2]  = lic2();
        cmv[3]  = lic3();
        cmv[4]  = lic4();
        cmv[5]  = lic5();
        cmv[6]  = lic6();
        cmv[7]  = lic7();
        cmv[8]  = lic8();
        cmv[9]  = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();

        return cmv;
    }

    // LIC stubs


    /***
     * There exists at least one set of N_PTS consecutive data points such that at least one point
     * in the set lies a distance greater than DIST from the line joining the first and last points
     * of the set.
     *
     * Special case:
     * - If the first and last points in the set are identical, then the distance is measured from
     *   that coincident point to the other points in the set (instead of distance to a line).
     *
     * Condition met when:
     * - NUMPOINTS >= 3
     * - N_PTS is valid (3 <= N_PTS <= NUMPOINTS)
     * - There exists at least one window of N_PTS consecutive points where
     *   at least one point is at a distance > DIST from the line (or from the coincident endpoint)
     *
     * Condition not met when:
     * - NUMPOINTS < 3
     * - N_PTS < 3 or N_PTS > NUMPOINTS
     * - For every window of N_PTS consecutive points, all points are at distance <= DIST
     *
     * @return true if LIC6 is satisfied, otherwise false.
     */
    private static boolean lic6() {
        int n = Main.NUMPOINTS;
        if (n < 3) return false;

        int nPts = Main.PARAMETERS.N_PTS;
        double dist = Main.PARAMETERS.DIST;

        /**
         * Defensive checks for invalid N_PTS values.
         */
        if (nPts < 3 || nPts > n) return false;

        /**
         * Slide a window of size N_PTS over all consecutive points.
         */
        for (int i = 0; i <= n - nPts; i++) {
            int first = i;
            int last  = i + nPts - 1;

            double x1 = Main.X[first], y1 = Main.Y[first];
            double x2 = Main.X[last],  y2 = Main.Y[last];

            /**
             * If the endpoints are the same point, the spec says we should compare
             * distances from that coincident point to all points in the window.
             */
            boolean endpointsCoincide = nearlyEqual(x1, x2) && nearlyEqual(y1, y2);

            if (endpointsCoincide) {
                for (int j = i + 1; j <= last; j++) {
                    if (euclid(x1, y1, Main.X[j], Main.Y[j]) > dist) return true;
                }
            } else {
                /**
                 * Otherwise, compute perpendicular distance from each interior point
                 * to the infinite line through the endpoints.
                 */
                double denom = Math.hypot(y2 - y1, x2 - x1);
                for (int j = i + 1; j < last; j++) {
                    double d = pointToLineDistance(Main.X[j], Main.Y[j], x1, y1, x2, y2, denom);
                    if (d > dist) return true;
                }
            }
        }
        return false;
    }

    /***
     * There exists at least one pair of data points separated by exactly K_PTS consecutive
     * intervening points such that the distance between the pair is greater than LENGTH1.
     *
     * Condition met when:
     * - NUMPOINTS >= 3
     * - K_PTS is valid (K_PTS >= 1)
     * - There exists at least one index i such that distance(point[i], point[i + K_PTS + 1]) > LENGTH1
     *
     * Condition not met when:
     * - NUMPOINTS < 3
     * - K_PTS < 1
     * - All such pairs have distance <= LENGTH1
     *
     * @return true if LIC7 is satisfied, otherwise false.
     */
    private static boolean lic7() {
        int n = Main.NUMPOINTS;
        if (n < 3) return false;

        int kPts = Main.PARAMETERS.K_PTS;
        double length1 = Main.PARAMETERS.LENGTH1;

        /**
         * Defensive check for invalid K_PTS values.
         */
        if (kPts < 1) return false;

        /**
         * Points must be separated by exactly K_PTS intervening points,
         * meaning the index difference is (K_PTS + 1).
         */
        int step = kPts + 1;

        for (int i = 0; i + step < n; i++) {
            int j = i + step;
            if (euclid(Main.X[i], Main.Y[i], Main.X[j], Main.Y[j]) > length1) return true;
        }
        return false;
    }

    /***
     * There exists at least one set of three data points separated by exactly A_PTS and B_PTS
     * consecutive intervening points that cannot all be contained within or on a circle of
     * radius RADIUS1.
     *
     * The three points used are:
     * - point[i]
     * - point[i + A_PTS + 1]
     * - point[i + A_PTS + B_PTS + 2]
     *
     * Condition met when:
     * - NUMPOINTS >= 5
     * - A_PTS >= 1 and B_PTS >= 1
     * - There exists at least one such triple of points whose minimal enclosing circle radius is > RADIUS1
     *
     * Condition not met when:
     * - NUMPOINTS < 5
     * - A_PTS < 1 or B_PTS < 1
     * - All such triples can be contained within or on a circle of radius RADIUS1
     *
     * @return true if LIC8 is satisfied, otherwise false.
     */
    private static boolean lic8() {
        int n = Main.NUMPOINTS;
        if (n < 5) return false;

        int aPts = Main.PARAMETERS.A_PTS;
        int bPts = Main.PARAMETERS.B_PTS;
        double r  = Main.PARAMETERS.RADIUS1;

        /**
         * Defensive checks for invalid A_PTS / B_PTS values.
         */
        if (aPts < 1 || bPts < 1) return false;

        int offset2 = aPts + 1;
        int offset3 = aPts + bPts + 2;

        for (int i = 0; i + offset3 < n; i++) {
            int j = i + offset2;
            int k = i + offset3;

            double x1 = Main.X[i], y1 = Main.Y[i];
            double x2 = Main.X[j], y2 = Main.Y[j];
            double x3 = Main.X[k], y3 = Main.Y[k];

            /**
             * If the minimal enclosing circle radius is greater than RADIUS1,
             * then the three points cannot be contained -> LIC8 is satisfied.
             */
            if (!fitsInCircleRadius(x1, y1, x2, y2, x3, y3, r)) return true;
        }
        return false;
    }

    // ==================== Helper functions ====================

    /**
     * Helper function to calculate Euclidean distance between two points.
     *
     * @param x1 x-coordinate of first point
     * @param y1 y-coordinate of first point
     * @param x2 x-coordinate of second point
     * @param y2 y-coordinate of second point
     * @return distance between (x1,y1) and (x2,y2)
     */
    private static double euclid(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    /**
     * Helper function to compare floating-point values with tolerance.
     * Used mainly for checking whether two points are "the same" for the LIC6 special case.
     *
     * @param a first value
     * @param b second value
     * @return true if |a-b| <= EPSILON
     */
    private static boolean nearlyEqual(double a, double b) {
        return Math.abs(a - b) <= EPSILON;
    }

    /**
     * Helper function to calculate the perpendicular distance from a point (x0,y0)
     * to the infinite line passing through (x1,y1) and (x2,y2).
     *
     * Formula:
     * distance = |(y2-y1)x0 - (x2-x1)y0 + x2*y1 - y2*x1| / sqrt((y2-y1)^2 + (x2-x1)^2)
     *
     * @param x0 x-coordinate of the point
     * @param y0 y-coordinate of the point
     * @param x1 x-coordinate of first line endpoint
     * @param y1 y-coordinate of first line endpoint
     * @param x2 x-coordinate of second line endpoint
     * @param y2 y-coordinate of second line endpoint
     * @param denom precomputed denominator sqrt((y2-y1)^2 + (x2-x1)^2)
     * @return perpendicular distance from (x0,y0) to the line
     */
    private static double pointToLineDistance(
            double x0, double y0,
            double x1, double y1,
            double x2, double y2,
            double denom
    ) {
        double num = Math.abs((y2 - y1) * x0 - (x2 - x1) * y0 + x2 * y1 - y2 * x1);
        return num / denom;
    }

    /***
     * Helper function to determine whether three points can be contained within or on a circle
     * of radius r (i.e., whether the minimal enclosing circle radius is <= r).
     *
     * Approach:
     * 1) If the farthest pair distance is greater than 2r, they cannot fit (early exit).
     * 2) If the points are collinear, the minimal radius is maxDistance/2.
     * 3) Otherwise:
     *    - If the triangle is right/obtuse, minimal radius is maxDistance/2.
     *    - If the triangle is acute, minimal radius is the circumradius.
     *
     * @param x1 first point x
     * @param y1 first point y
     * @param x2 second point x
     * @param y2 second point y
     * @param x3 third point x
     * @param y3 third point y
     * @param r circle radius to test against
     * @return true if the three points fit within/on a circle of radius r, otherwise false
     */
    private static boolean fitsInCircleRadius(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3,
            double r
    ) {
        double d12 = euclid(x1, y1, x2, y2);
        double d23 = euclid(x2, y2, x3, y3);
        double d13 = euclid(x1, y1, x3, y3);

        double max = Math.max(d12, Math.max(d23, d13));

        /**
         * Early exit: if the farthest two points are more than the diameter apart,
         * there is no way all three points fit in a circle of radius r.
         */
        if (max > 2.0 * r + EPSILON) return false;

        /**
         * Compute twice the triangle area using the cross product.
         * If the area is near zero, the points are collinear.
         */
        double cross = Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1));
        if (cross <= EPSILON) {
            return (max / 2.0) <= r + EPSILON;
        }

        /**
         * Check if triangle is right or obtuse:
         * If maxSide^2 >= sum(otherSides^2), then minimal enclosing circle
         * is determined by the longest side.
         */
        double a = d23, b = d13, c = d12;
        double a2 = a * a, b2 = b * b, c2 = c * c;

        double max2 = Math.max(a2, Math.max(b2, c2));
        double sumOthers = a2 + b2 + c2 - max2;

        boolean rightOrObtuse = max2 >= sumOthers - EPSILON;

        double minRadius;
        if (rightOrObtuse) {
            minRadius = max / 2.0;
        } else {
            /**
             * Acute triangle: minimal enclosing circle is the circumcircle.
             * Circumradius = (a*b*c) / (4*Area)
             * where Area = cross / 2.
             */
            double area = cross / 2.0;
            double circumRadius = (a * b * c) / (4.0 * area);
            minRadius = circumRadius;
        }

        return minRadius <= r + EPSILON;
    }


    private static boolean lic9()  { return false; }
    private static boolean lic10() { return false; }
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
