public class Main {
    // Input Variables
    public static int NUMPOINTS;
    public static double[] X;
    public static double[] Y;

    public static Parameters PARAMETERS;
    public static Connector[][] LCM;
    public static boolean[] PUV;

    // Intermediate / Output
    public static boolean[] CMV;
    public static boolean[][] PUM;
    public static boolean[] FUV;
    public static boolean LAUNCH;

    // Enums
    public enum Connector {
        NOTUSED,
        ORR,
        ANDD
    }

    // PARAMETERS struct
    public static class Parameters {
        public double LENGTH1;
        public double RADIUS1;
        public double EPSILON;
        public double AREA1;
        public final double PI =  3.1415926535;

        public int Q_PTS;
        public int QUADS;

        public double DIST;
        public int N_PTS;

        public int K_PTS;
        public int A_PTS;
        public int B_PTS;
        public int C_PTS;
        public int D_PTS;
        public int E_PTS;
        public int F_PTS;
        public int G_PTS;

        public double LENGTH2;
        public double RADIUS2;
        public double AREA2;
    }

    /**
     * Main method to run the DECIDE program.
     * Calls on decide and prints final LAUNCH decision.
     * @param args
     */
    public static void main(String[] args) {
        decide();
        System.out.println(LAUNCH ? "YES" : "NO");
    }

    // DECIDE pipeline
    public static void decide() {
        CMV = Cmv.computeCMV();
        PUM = Pum.computePUM();
        FUV = Fuv.computeFUV();

        LAUNCH = true;
        // Final decision is based on FUV.
        // If any FUV[i] is false, LAUNCH is false.
        for (int i = 0; i < 15; i++) {
            if (!FUV[i]) {
                LAUNCH = false;
                break;
            }
        }
    }
}
