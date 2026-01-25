import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class test {

    @Before
    public void setup() {
        // Initialize PARAMETERS before each test.
        Main.PARAMETERS = new Main.Parameters();
    }

    /**
     * Asserts false for:
     * - NUMPOINTS < 3
     */
    @Test
    public void lic11_false_whenNumPointsLessThan3() {
        Main.NUMPOINTS = 2;
        Main.X = new double[] { 1.0, 0.0 };
        Main.Y = new double[] { 0.0, 0.0 };
        Main.PARAMETERS.G_PTS = 1;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[11]);
    }

    /**
     * Asserts false for:
     * - G_PTS out of range
     */
    @Test
    public void lic11_false_whenGPTsOutOfRange() {
        Main.NUMPOINTS = 4;
        Main.X = new double[] { 3, 2, 1, 0 };
        Main.Y = new double[] { 0, 0, 0, 0 };

        Main.PARAMETERS.G_PTS = 0; // Invalid, must be >= 1

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[11]);
    }

    /**
     * Asserts true for:
     * - Condition met X[2] < X[0]
     * - NUMPOINTS >= 3
     * - G_PTS = 1 (pair (0,2))
     */
    @Test
    public void lic11_true_whenConditionMet() {
        // Condition is met when X decreases with exactly GPTs between.
        // NUMPOINTS = 3, G_PTS = 1 --> step = 2, pair (0,2)
        // X[2] < X[0] --> condition met.
        Main.NUMPOINTS = 3;
        Main.X = new double[] { 5.0, 100.0, 4.0 };
        Main.Y = new double[] { 0.0, 0.0, 0.0 };

        Main.PARAMETERS.G_PTS = 1;

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[11]);
    }

    /**
     * Asserts false for:
     * - NUMPOINTS >= 3
     * - G_PTS = 2 (pairs (0,3) and (1,4))
     * - No pair satisfies X[j] < X[i]
     */
    @Test
    public void lic11_false_whenConditionNotMet() {
        // COndition is not met when there is no decreasing pair with G_PTS between.
        // G_PTS = 2 --> step = 3, pairs = (0,3) & (1,4)
        // X[3] >= X[0] and X[4] >= X[1] --> no decreasing pair.
        Main.NUMPOINTS = 5;
        Main.X = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0 };
        Main.Y = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        Main.PARAMETERS.G_PTS = 2;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[11]);
    }

    /**
     * Tests for lic12
     */

    /**
     * Asserts false for:
     * - NUMPOINTS < 3
     */
    @Test
    public void lic12_false_whenNumPointsLessThan3() {
        Main.NUMPOINTS = 2;
        Main.X = new double[] { 0, 10 };
        Main.Y = new double[] { 0, 0 };

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 1;
        Main.PARAMETERS.LENGTH2 = 5;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[12]);
    }

    /**
     * Asserts true for:
     * - A pair separated by K_PTS has distance > LENGTH1
     * - A (same or different) pair separated by K_PTS has distance < LENGTH2
     */
    @Test
    public void lic12_true_whenConditionsMet() {
        // NUMPOINTS = 5, K_PTS = 1, step = 2, pairs: (0,2), (1,3), (2,4)
        Main.NUMPOINTS = 5;
        Main.X = new double[] { 0, 0, 10, 0, 0 };
        Main.Y = new double[] { 0, 0, 0, 0, 0 };

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 5; // (0,2) distance = 10 > 5 : condition 1
        Main.PARAMETERS.LENGTH2 = 1; // (1,3) distance = 0 < 1 : condition 2

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[12]);
    }

    /**
     * Asserts false for:
     * - There exists a pair with distance > LENGTH1
     * - There exists no pair with distance < LENGTH2
     */
    @Test
    public void lic12_false_whenOnlyCondition1Met() {
        Main.NUMPOINTS = 5;
        Main.X = new double[] { 0, 0, 10, 0, 10 };
        Main.Y = new double[] { 0, 0, 0, 0, 0 };

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 5; // many pairs > 5
        Main.PARAMETERS.LENGTH2 = 1; // no pair has distance < 1
        Main.X = new double[] { 0, 1, 10, 2, 20 }; // (1,3) = 1, not < 1

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[12]);
    }

    /**
     * Asserts false for:
     * - There exists a pair with distance < LENGTH2
     * - There exists no pair with distance > LENGTH1
     */
    @Test
    public void lic12_false_whenOnlyCondition2Met() {
        Main.NUMPOINTS = 5;
        Main.X = new double[] { 0, 1, 2, 3, 4 };
        Main.Y = new double[] { 0, 0, 0, 0, 0 };

        Main.PARAMETERS.K_PTS = 1; // step=2 => distances are 2
        Main.PARAMETERS.LENGTH1 = 5; // none > 5
        Main.PARAMETERS.LENGTH2 = 3; // yes, 2 < 3 exists

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[12]);
    }

    /**
     * Test for lic13
     */

    /**
     * Asserts false for:
     * - NUMPOINTS < 5
     */
    @Test
    public void lic13_false_whenNumPointsLessThan5() {
        Main.NUMPOINTS = 4;
        Main.X = new double[] { 0, 1, 2, 3 };
        Main.Y = new double[] { 0, 0, 0, 0 };

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 1;
        Main.PARAMETERS.RADIUS2 = 1;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[13]);
    }

    /**
     * Asserts true for:
     * - One triple cannot fit in RADIUS1
     * - The same triple can fit in RADIUS2
     */
    @Test
    public void lic13_true_whenBothConditionsMetByOneTriple() {
        // NUMPOINTS = 5, A_PTS = 1, B_PTS = 1 => triples: (0,2,4)
        // Points that require radius ~5 --> cannot fit RADIUS1 = 1 but can RADIUS2 =
        // 20.
        Main.NUMPOINTS = 5;
        Main.X = new double[] { 0, 0, 10, 0, 0 };
        Main.Y = new double[] { 0, 0, 0, 0, 10 };

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 1; // Too small to fit triple (0,2,4)
        Main.PARAMETERS.RADIUS2 = 20; // Large enough to fit triple (0,2,4)

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[13]);
    }

    /**
     * Asserts false for:
     * - All valid triples fit in RADIUS1
     * - Therefore no triple requires radius > RADIUS1
     */
    @Test
    public void lic13_false_whenAllTriplesFitR1() {
        // If all triples fit in RADIUS1 --> Condition 1 never met.
        Main.NUMPOINTS = 6;
        Main.X = new double[] { 0, 0.1, 0.2, 0.3, 0.4, 0.5 };
        Main.Y = new double[] { 0, 0.0, 0.0, 0.0, 0.0, 0.0 };

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 1; // Large enough to fit all triples
        Main.PARAMETERS.RADIUS2 = 1;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[13]);
    }

    /**
     * Asserts false for:
     * - At least one triple requires radius > RADIUS1
     * - No triple fits in RADIUS2
     */
    @Test
    public void lic13_false_whenCannotFitR1ButCannotFitR2() {
        // If no triple can fit in RADIUS2 --> Condition 2 never met.
        Main.NUMPOINTS = 6;
        Main.X = new double[] { 0, 0, 10, 0, 0, 10 };
        Main.Y = new double[] { 0, 0, 0, 0, 10, 10 };

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 1; // Too small to fit
        Main.PARAMETERS.RADIUS2 = 2; // Too small to fit

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[13]);
    }
}