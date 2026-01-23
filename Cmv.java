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

    private static boolean lic9()  { 
        int cpts= Main.PARAMETERS.C_PTS;
        int dpts = Main.PARAMETERS.D_PTS;
        int np = Main.NUMPOINTS;

        if (np<5){
            return false ;
        }

        if(1>cpts || 1>dpts || (cpts+dpts > np -3) ){
            return false; 
        }

        for(int i =0 ; i < np-cpts-dpts-2;i++){

            int firstIdx = i;
            int middleIdx = i + cpts + 1; 
            int lastIdx = middleIdx + dpts + 1; 

            double x1 = Main.X[firstIdx], y1 = Main.Y[firstIdx];
            double x2 = Main.X[middleIdx], y2 = Main.Y[middleIdx];
            double x3 = Main.X[lastIdx], y3 = Main.Y[lastIdx];

             double angle=calculateAngle(x1,y1,x2,y2,x3,y3);
             if(angle < Math.PI-EPSILON || angle < Math.PI+EPSILON){
                    return true;
             }
        }
        
    return false; 
    }

    private static double calculateAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
    double v1x = x1 - x2;
    double v1y = y1 - y2;
    double v2x = x3 - x2;
    double v2y = y3 - y2;

    double dotProduct = (v1x * v2x) + (v1y * v2y);
    double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
    double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);

    double cosTheta = dotProduct / (mag1 * mag2);

    cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));

    return Math.acos(cosTheta);

    }


    private static boolean lic10() { return false; }
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
