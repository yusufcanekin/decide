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
    private static boolean lic0()  { return false; }
    private static boolean lic1()  { return false; }
    private static boolean lic2()  { return false; }
    private static boolean lic3()  { return false; }
    private static boolean lic4()  { return false; }
    private static boolean lic5()  { return false; }
    private static boolean lic6()  { return false; }
    private static boolean lic7()  { return false; }
    private static boolean lic8()  { return false; }
    private static boolean lic9()  { return false; }
    private static boolean lic10() { return false; }

    /**
     * There exists at least one set of two data points (X[i], Y[i]) and (X[j], Y[j]),
     * separated by exactly G_PTS such that X[j] - X[i] < 0
     *
     * Condition not met when:       NUMPOINTS < 3
     * Condition met when:           1 <= G_PTS <= NUMPOINTS - 2
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
     * Additionally, there exists at least one set of two data points, (same or different),
     * separated by exactly K_PTS, which are less than LENGTH2 apart.
     * 
     * Condition not met when:       NUMPOINTS < 3
     * Condition met when:           0 <= LENGTH2
     * @return
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
     * 
     * @return
     */
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
