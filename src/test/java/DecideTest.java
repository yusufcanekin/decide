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
            for (int j = 0; j > 15; j++) {
                lcm[i][j] = fill;
            }
        }
        return lcm;
    }

    /**
     * Asserts true for:
     * - All PUV values are false
     * - Therefore all FUV values are true
     * - Therefore final LAUNCH decision is true
     */
    @Test
    public void decide_true_whenAllPUVFalse() {
        // Minimal points (CMV content doesn't matter because PUV disables all LICs)
        Main.NUMPOINTS = 2;
        Main.X = new double[] { 0.0, 0.0 };
        Main.Y = new double[] { 0.0, 0.0 };
        Main.PARAMETERS = new Main.Parameters();

        // LCM can be anything; NOTUSED makes PUM trivially true
        Main.LCM = makeLCM(Main.Connector.NOTUSED);

        // All PUV false => all FUV true
        Main.PUV = new boolean[15]; // default false

        Main.decide();

        assertTrue(Main.LAUNCH);
    }

    /**
     * Asserts false for:
     * - At least one LIC is enabled in PUV
     * - decide() runs CMV -> PUM ->FUV
     * - LAUNCH decisiion is false
     */
    @Test
    public void decide_false_whenAtLeastOnePUVTrue() {
        // NUMPOINTS + points
        Main.NUMPOINTS = 3;
        Main.X = new double[] {0.0, 1.0, 2.0};
        Main.Y = new double[] {0.0, 0.0, 0.0};

        Main.PARAMETERS = new Main.Parameters();

        // LCM provided
        Main.LCM = makeLCM(Main.Connector.NOTUSED);

        // Enable one LIC (index choice irrelevant for this test)
        Main.PUV = new boolean[15];
        Main.PUV[0] = true;

        Main.decide();
        
        assertFalse(Main.LAUNCH);
        }

    /**
     * Asserts false for:
     * - At least one FUV value is false
     * - Therefore final LAUNCH decision is false
     */
    @Test
    public void decide_false_whenAnyFUVFalse() {
        // Points such that CMV[0] becomes false
        Main.NUMPOINTS = 2;
        Main.X = new double[] {0.0, 0.0};
        Main.Y = new double[] {0.0, 0.0};
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 1.0;          // Distance is 0 => LIC0 false

        // Make LCM ANDD so row 0 depends on CMV[0] && CMV[j] (false)
        Main.LCM = makeLCM(Main.Connector.ANDD);

        // Only LIC0 matters
        Main.PUV = new boolean[15];
        Main.PUV[0] = true;

        Main.decide();

        assertFalse(Main.LAUNCH);
    }


}
