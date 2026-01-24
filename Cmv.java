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
    private static boolean lic4()  { return false; }
    private static boolean lic5()  { return false; }
    private static boolean lic6()  { return false; }
    private static boolean lic7()  { return false; }
    private static boolean lic8()  { return false; }
    private static boolean lic9()  { return false; }
    private static boolean lic10() { return false; }
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
