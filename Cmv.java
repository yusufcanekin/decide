public class Cmv {
    public static double EPSILON = 0.01;

    public static boolean[] computeCMV() {
        boolean[] cmv = new boolean[15];

        cmv[0] = lic0();
        cmv[1] = lic1();
        cmv[2] = lic2();
        cmv[3] = lic3();
        cmv[4] = lic4();
        cmv[5] = lic5();
        cmv[6] = lic6();
        cmv[7] = lic7();
        cmv[8] = lic8();
        cmv[9] = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();

        return cmv;
    }

    // LIC stubs
    private static boolean lic0() {
        return false;
    }

    private static boolean lic1() {
        return false;
    }

    private static boolean lic2() {
        return false;
    }

    private static boolean lic3() {
        return false;
    }

    private static boolean lic4() {
        return false;
    }

    private static boolean lic5() {
        return false;
    }

    private static boolean lic6() {
        return false;
    }

    private static boolean lic7() {
        return false;
    }

    private static boolean lic8() {
        return false;
    }

    private static boolean lic9() {
        return false;
    }

    private static boolean lic10() {
        return false;
    }

    /**
     * There exists at least one set of two data points (X[i], Y[i]) and (X[j],
     * Y[j]),
     * separated by exactly G_PTS such that X[j] - X[i] < 0
     *
     * Condition not met when: NUMPOINTS < 3
     * Condition met when: 1 <= G_PTS <= NUMPOINTS - 2
     *
     * @return true if condition met, false otherwise.
     */
    private static boolean lic11() {
        if (Main.NUMPOINTS < 3) {
            return false;
        }

        int g = Main.PARAMETERS.G_PTS;

        if (g < 1 || g > Main.NUMPOINTS - 2) {
            return false;
        }

        int step = g + 1;
        for (int i = 0; i + step < Main.NUMPOINTS; i++) {
            int j = i + step;
            if (Main.X[j] < Main.X[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * There exists at least one set of two data points, separated by exactly K_PTS,
     * which are more than LENGTH1 apart.
     * Additionally, there exists at least one set of two data points, (same or
     * different),
     * separated by exactly K_PTS, which are less than LENGTH2 apart.
     * 
     * Condition not met when: NUMPOINTS < 3
     * Condition met when: 0 <= LENGTH2
     * 
     * @return true if condition met, false otherwise.
     */
    private static boolean lic12() {
        // Condition not met when NUMPOINTS < 3
        if (Main.NUMPOINTS < 3) {
            return false;
        }

        int k = Main.PARAMETERS.K_PTS;
        double length1 = Main.PARAMETERS.LENGTH1;
        double length2 = Main.PARAMETERS.LENGTH2;

        // Parameter constraints
        if (length2 < 0 || k < 1 || k > Main.NUMPOINTS - 2) {
            return false;
        }

        int step = k + 1;

        boolean foundGreaterThanL1 = false;
        boolean foundLessThanL2 = false;

        for (int i = 0; i + step < Main.NUMPOINTS; i++) {
            int j = i + step;

            double dx = Main.X[j] - Main.X[i];
            double dy = Main.Y[j] - Main.Y[i];
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > length1) {
                foundGreaterThanL1 = true;
            }
            if (distance < length2) {
                foundLessThanL2 = true;
            }

            // Exits when both conditions are met.
            if (foundGreaterThanL1 && foundLessThanL2) {
                return true;
            }
        }

        return false;
    }

    /**
     * There exists at least one set of three data points (X[i], Y[i]), (X[j],
     * Y[j]), and (X[k], Y[k]), separated by exactly A_PTS and B_PTS, that cannot be
     * contained within or on a circle of radius RADIUS1.
     * Additionally, there exists at least one set of three data points (same or
     * different) separated by exactly A_PTS and B_PTS that can be contained within or on a
     * circle of radius RADIUS2.
     *
     * Condition not met when: NUMPOINTS < 5 or RADIUS < 0
     * 
     * @return true if condition met, false otherwise.
     */
    private static boolean lic13() {
        if (Main.NUMPOINTS < 5) {
            return false;
        }

        int a = Main.PARAMETERS.A_PTS;
        int b = Main.PARAMETERS.B_PTS;

        double r1 = Main.PARAMETERS.RADIUS1;
        double r2 = Main.PARAMETERS.RADIUS2;

        // Parameter constraints
        if (a < 1 || b < 1) {
            return false;
        }
        if (a + b < Main.NUMPOINTS - 3) {
            return false;
        }
        if (r1 < 0 || r2 < 0) {
            return false;
        }

        boolean cannotFitR1 = false;
        boolean canFitR2 = false;

        int step1 = a + 1;
        int step2 = b + 1;

        for (int i = 0; i + step1 + step2 < Main.NUMPOINTS; i++) {
            int j = i + step1;
            int k = j + step2;

            double x1 = Main.X[i], y1 = Main.Y[i];
            double x2 = Main.X[j], y2 = Main.Y[j];
            double x3 = Main.X[k], y3 = Main.Y[k];

            double required = minEnclosingCircleRadius(x1, y1, x2, y2, x3, y3);
            if (required > r1) {
                cannotFitR1 = true;
            }
            if (required <= r2) {
                canFitR2 = true;
            }

            if (cannotFitR1 && canFitR2) {
                return true;
            }
        }

        return false;
    }

    private static double minEnclosingCircleRadius(double x1, double y1, double x2, double y2, double x3, double y3) {
        // TOL = tolerance for floating point comparisons
        final double TOL = 1e-12;

        double a = dist(x2, y2, x3, y3);
        double b = dist(x1, y1, x3, y3);
        double c = dist(x1, y1, x2, y2);

        // Check for collinearity using area of triangle
        double dMax = Math.max(a, Math.max(b, c));

        // Twice area using corss product
        double cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        double area2 = Math.abs(cross);

        // Collinear (or very close to it): Radius is half the longest segment.
        if (area2 < TOL) {
            return dMax / 2.0;
        }

        // Check if triangle is right/obtuse:
        // If c is longest side, then if c^2 >= a^2 + b^2,
        // triangle is right/obtuse at opposite vertex.
        // Sort sides so that L is largest, then compare L^2 >= S1^2 + S2^2
        double[] s = new double[] { a, b, c };
        java.util.Arrays.sort(s);
        double S1 = s[0], S2 = s[1], L = s[2];

        if (L * L >= S1 * S1 + S2 * S2 - TOL) {
            return L / 2.0;
        }

        // Acute triangle: use circumradius formula
        // R = abc / 4A , area2 = 2A => 4A = 2*area2
        return (a * b * c) / (2.0 * area2);
    }

    private static double dist(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static boolean lic14() {
        return false;
    }
}
