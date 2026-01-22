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
     * Exists at least one set of two data points (X[i], Y[i]) and (X[j], Y[j]),
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
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
