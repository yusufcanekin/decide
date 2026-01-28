public class Main {
    // Input Variables
    public static int NUMPOINTS = 5;
    public static double[] X = new double[] {0.0, 1.0, 2.0, 4.0, -1.0};
    public static double[] Y = new double[] {0.0, 1.0, 2.0, 0.0, 0.0};

    public static Parameters PARAMETERS;
    public static Connector[][] LCM  = new Connector[15][15];
    public static boolean[] PUV = new boolean[15];

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
        public double LENGTH1 = 0.5;
        public double RADIUS1 = 0.5;
        public double EPSILON = 1.0;
        public double AREA1 = 0.5;
        public final double PI =  3.1415926535;

        public int Q_PTS = 4;
        public int QUADS = 1;

        public double DIST = 0.5;
        public int N_PTS = 3;

        public int K_PTS = 1;
        public int A_PTS = 1;
        public int B_PTS = 1;
        public int C_PTS = 1;
        public int D_PTS = 1;
        public int E_PTS = 1;
        public int F_PTS = 1;
        public int G_PTS = 1;

        public double LENGTH2 = 3.0;
        public double RADIUS2 = 5.0;
        public double AREA2 = 5.0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                Main.LCM[i][j] = Main.Connector.NOTUSED;
            }
        }

        for (int i = 0; i < 15; i++) {
             Main.PUV[i] = false;
        }
        decide();
        System.out.println(LAUNCH ? "YES" : "NO");
    }

    // DECIDE pipeline
    public static void decide() {
        CMV = Cmv.computeCMV();
        PUM = Pum.computePUM();
        FUV = Fuv.computeFUV();
        LAUNCH = false; // placeholder
    }
}
