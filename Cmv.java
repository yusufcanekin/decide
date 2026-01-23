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

    private static boolean lic0()  { 
        /**
         * There exists at least one pair of consecutive points data points
         * whose distance is greater than LENTGH1, apart
         * 
         * Condition met when: 
         * - LENGTH ispositive
         * - There are at least one set of two points
         * 
         * Condition not when:
         * - LENGTH is less or equal to zero
         * - There are less than one set of two points
         * - All distances between consecutive points are less than 
         *   or equal to LENGTH1 
         */
        if (Main.PARAMETERS.LENGTH1 <= 0) {
            return false;
        }

        if (Main.X.length < 2) {
            return false;
        }

        for (int i = 0; i < Main.X.length - 1; i++) {
            double distance = Math.sqrt(Math.pow(Main.X[i+1] - Main.X[i], 2) + Math.pow(Main.Y[i+1] - Main.Y[i], 2));
            
            if (distance > Main.PARAMETERS.LENGTH1) {
                return true;
            }
        }

        return false;
    }   
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
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
