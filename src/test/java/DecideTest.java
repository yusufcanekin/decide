import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class DecideTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DecideTest.class);
        System.out.println(result.getFailures());
    }

    private Main.Connector[][] makeLCM(Main.Connector fill) {
        Main.Connector[][] lcm = new Main.Connector[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = fill;
            }
        }
        return lcm;
    }

    /**
     * Asserts true for:
     * - All PUV values are false --> all FUV values are true.
     * - Therefore final LAUNCH decision is true.
     */
    @Test
    public void decide_true_whenExpectedToBeTrueAndPUVAllFalse() {
        Main.NUMPOINTS = 5;
        Main.PARAMETERS = new Main.Parameters();

        Main.PARAMETERS.LENGTH1 = 0.5;
        Main.PARAMETERS.RADIUS1 = 0.5;
        Main.PARAMETERS.EPSILON = 1.0;
        Main.PARAMETERS.AREA1 = 0.5;

        Main.PARAMETERS.Q_PTS = 4;
        Main.PARAMETERS.QUADS = 1;

        Main.PARAMETERS.DIST = 0.5;
        Main.PARAMETERS.N_PTS = 3;

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.G_PTS = 1;

        Main.PARAMETERS.LENGTH2 = 3.0;
        Main.PARAMETERS.RADIUS2 = 5.0;
        Main.PARAMETERS.AREA2 = 5.0;

        Main.X = new double[] { 0.0, 1.0, 2.0, 4.0, -1.0 };
        Main.Y = new double[] { 0.0, 1.0, 2.0, 0.0, 0.0 };

        Main.LCM = makeLCM(Main.Connector.ORR);
        Main.PUV = new boolean[15]; // default false

        Main.decide();
        assertTrue(Main.LAUNCH);
    }

    @Test
    public void decide_true_whenExpectedToBeTrueAndPUVAllTrue() {
        Main.NUMPOINTS = 5;
        Main.PARAMETERS = new Main.Parameters();

        Main.PARAMETERS.LENGTH1 = 0.5;
        Main.PARAMETERS.RADIUS1 = 0.5;
        Main.PARAMETERS.EPSILON = 1.0;
        Main.PARAMETERS.AREA1 = 0.5;

        Main.PARAMETERS.Q_PTS = 4;
        Main.PARAMETERS.QUADS = 1;

        Main.PARAMETERS.DIST = 0.5;
        Main.PARAMETERS.N_PTS = 3;

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.G_PTS = 1;

        Main.PARAMETERS.LENGTH2 = 3.0;
        Main.PARAMETERS.RADIUS2 = 5.0;
        Main.PARAMETERS.AREA2 = 5.0;

        Main.X = new double[] { 0.0, 1.0, 2.0, 4.0, -1.0 };
        Main.Y = new double[] { 0.0, 1.0, 2.0, 0.0, 0.0 };
        // CMV[i] true for all i
        // When LCM is ORR --> PUM[i][j] true for all i,j
        // When PUV[i] true for alla i -->
        // must check that all PUM[i][j] true for FUV[i] to be true

        Main.LCM = makeLCM(Main.Connector.ORR);
        Main.PUV = new boolean[15]; // default false
        for (int i = 0; i < 15; i++) {
            Main.PUV[i] = true;
        }

        System.out.println("LCM[0][1] = " + Main.LCM[0][1]);
        System.out.println("LCM[0][0] = " + Main.LCM[0][0]);
        System.out.println("LCM[2][3] = " + Main.LCM[2][3]);
        System.out.println("PUV[0] = " + Main.PUV[0]);

        Main.decide();
        // Check how many CMVs are actually true
        int trueCount = 0;
        for (int i = 0; i < 15; i++) {
            if (Main.CMV[i])
                trueCount++;
        }
        System.out.println("CMV true count = " + trueCount);

        printPUM(Main.PUM);

        // Show which FUV entries fail
        for (int i = 0; i < 15; i++) {
            if (!Main.FUV[i]) {
                System.out.println("FUV[" + i + "] is false, CMV[" + i + "]=" + Main.CMV[i]);
            }
        }

        assertTrue(Main.LAUNCH);
    }

    @Test
    public void decide_falseWhenExpectedToBeFalse() {
        Main.NUMPOINTS = 2;
        Main.PARAMETERS = new Main.Parameters();

        Main.PARAMETERS.LENGTH1 = 1000.0;
        Main.PARAMETERS.RADIUS1 = 100.0;
        Main.PARAMETERS.EPSILON = Main.PARAMETERS.PI;
        Main.PARAMETERS.AREA1 = 1000.0;

        Main.PARAMETERS.Q_PTS = 2;
        Main.PARAMETERS.QUADS = 4;

        Main.PARAMETERS.DIST = 0.00001;
        Main.PARAMETERS.N_PTS = 2;

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.G_PTS = 1;

        Main.PARAMETERS.LENGTH2 = 0.00001;
        Main.PARAMETERS.RADIUS2 = 0.001;
        Main.PARAMETERS.AREA2 = 0.00001;

        Main.X = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0 };
        Main.Y = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        Main.LCM = makeLCM(Main.Connector.ANDD);
        Main.PUV = new boolean[15];
        for (int i = 0; i < 15; i++) {
            Main.PUV[i] = true;
        }

        Main.decide();
        assertFalse(Main.LAUNCH);
    }

    private static void printPUM(boolean[][] pum) {
        System.out.println("PUM matrix:");
        System.out.print("      ");
        for (int j = 0; j < 15; j++) {
            System.out.printf("%4d", j);
        }
        System.out.println();

        for (int i = 0; i < 15; i++) {
            System.out.printf("%4d: ", i);
            for (int j = 0; j < 15; j++) {
                System.out.printf("%4s", pum[i][j] ? "T" : "F");
            }
            System.out.println();
        }
    }
}