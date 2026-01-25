
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

    // ==================== Helper functions (for LIC6–LIC8) ====================

    private static double euclid(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    private static boolean nearlyEqual(double a, double b) {
        return Math.abs(a - b) <= EPSILON;
    }

    private static double pointToLineDistance(
            double x0, double y0,
            double x1, double y1,
            double x2, double y2,
            double denom
    ) {
        double num = Math.abs((y2 - y1) * x0 - (x2 - x1) * y0 + x2 * y1 - y2 * x1);
        return num / denom;
    }

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

        if (max > 2.0 * r + EPSILON) return false;

        double cross = Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1));
        if (cross <= EPSILON) {
            return (max / 2.0) <= r + EPSILON;
        }

        double a = d23, b = d13, c = d12;
        double a2 = a * a, b2 = b * b, c2 = c * c;

        double max2 = Math.max(a2, Math.max(b2, c2));
        double sumOthers = a2 + b2 + c2 - max2;

        boolean rightOrObtuse = max2 >= sumOthers - EPSILON;

        double minRadius;
        if (rightOrObtuse) {
            minRadius = max / 2.0;
        } else {
            double area = cross / 2.0;
            double circumRadius = (a * b * c) / (4.0 * area);
            minRadius = circumRadius;
        }

        return minRadius <= r + EPSILON;
    }
  
    /**
     * There exists at least one set of three consecutive data points which form an angle such that:
     *
     *      angle < (PI−EPSILON)
     *      or
     *      angle > (PI+EPSILON)
     *
     * The second of the three consecutive points is always the vertex of the angle. If either the first
     * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
     * is not satisfied by those three points.
     *
     * Condition met when:
     * - angle < (PI−EPSILON) or angle > (PI+EPSILON)
     * - the second of the three consecutive points is always the vertex of the angle
     *
     * Condition not met when:
     * - angle => (PI−EPSILON) or angle <= (PI+EPSILON)
     * - (PI - EPSILON) ≤ angle ≤ (PI + EPSILON)
     * - point or the last point (or both) coincides with the vertex, the angle is undefined
     * @return
     */
    private static boolean lic2()  {
        if (Main.X.length < 3) {
            return false;
        }

        for (int i = 0; i < Main.X.length - 2; i++) {
            double x0 = Main.X[i];
            double y0 = Main.Y[i];
            double x1 = Main.X[i+1];
            double y1 = Main.Y[i+1];
            double x2 = Main.X[i+2];
            double y2 = Main.Y[i+2];

            double angle = calculateAngle(x0, y0, x1, y1, x2, y2);

            if ((angle < Main.PARAMETERS.PI - Main.PARAMETERS.EPSILON || angle > Main.PARAMETERS.PI + Main.PARAMETERS.EPSILON) && angle != -1) {
                return true;
            }
        }

        return false;
    }

    private static double calculateAngle(double x0, double y0,
                                         double x1, double y1,
                                         double x2, double y2) {

        // Calculate distances from vertex to the other two points
        double d01 = Math.hypot(x1 - x0, y1 - y0);
        double d02 = Math.hypot(x2 - x0, y2 - y0);

        // Check if vertex coincides with either point (undefined angle)
        if (Math.abs(d01) < 1e-10 ||  Math.abs(d02) < 1e-10) {
            return -1;
        }
        else {
            double angle = Math.atan2(d01, d02);
            return angle;
        }
    }

    /**
     * LIC 3:
     * There exists at least one set of three consecutive data points
     * that are the vertices of a triangle with area greater than AREA1.
     */
    private static boolean lic3() {

        // This LCI needs at least 3 points to form a triangular
        if (Main.NUMPOINTS < 3) {
            return false;
        }

        // We need consecutive points, so sliding a window of 3 would catch each relevant subset
        for (int i = 0; i <= Main.NUMPOINTS - 3; i++) {

            double x1 = Main.X[i];
            double y1 = Main.Y[i];
            double x2 = Main.X[i + 1];
            double y2 = Main.Y[i + 1];
            double x3 = Main.X[i + 2];
            double y3 = Main.Y[i + 2];

            // Area of triangle calculated from 3x3 matrix determinant
            double area = Math.abs(
                    x1 * (y2 - y3) -
                            x2 * (y1 - y3) +
                            x3 * (y1 - y2)
            ) / 2.0;

            if (area > Main.PARAMETERS.AREA1) {
                return true;
            }
        }

        return false;
    }
    /**
     * LIC 4:
     * There exists at least one set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants (quadrant priority rules apply).
     */
    private static boolean lic4() {
        int qPts = Main.PARAMETERS.Q_PTS;
        int quads = Main.PARAMETERS.QUADS;

        // Condition is not met if NUMPOINTS < Q_PTS
        if (Main.NUMPOINTS < qPts) {
            return false;
        }

        // We need consecutive points, so we are sliding a window of size Q_PTS
        for (int start = 0; start <= Main.NUMPOINTS - qPts; start++) {

            // Booleans indicating whether ith quadrant is present in consecutive subset of points or not
            boolean[] seenQuadrants = new boolean[4];

            for (int j = start; j < start + qPts; j++) {
                int q = quadrant(Main.X[j], Main.Y[j]); // quadrant delegation is done in this line
                seenQuadrants[q - 1] = true;
            }

            int distinct = 0;
            for (boolean s : seenQuadrants) {
                if (s) distinct++;
            }

            if (distinct > quads) {
                return true;
            }
        }

        return false;
    }

    /**
     * Helper function for quadrant mapping with priority as per spec example.
     */
    private static int quadrant(double x, double y) {
        // Axis and origin cases with priority
        if (x == 0 && y == 0) return 1;      // (0,0) -> I
        if (x == 0) {
            if (y > 0) return 1;             // (0, +) -> I
            if (y < 0) return 3;             // (0, -) -> III
            return 1;                        // (0,0), handled already but for safety, returning 1
        }

        if (y == 0) {
            if (x > 0) return 1;             // (+,0) -> I
            if (x < 0) return 2;             // (-,0) -> II
            return 1;
        }

        // Non axis points
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        return 4; // x > 0 && y < 0
    }

    /**
     * LIC 5:
     * There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
     * that X[j] - X[i] < 0. (where i = j-1). i.e. X[i+1] - X[i] < 0
     */
    private static boolean lic5() {
        // Condition is not met when NUMPOINTS < 2
        if (Main.NUMPOINTS < 2) {
            return false;
        }

        for (int i = 0; i < Main.NUMPOINTS - 1; i++) {
            if (Main.X[i + 1] - Main.X[i] < 0) {
                return true;
            }
        }
        return false;
    }


    private static boolean lic9()  { return false; }
    private static boolean lic10() { return false; }
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
