import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CmvLic6to8Test {

    @BeforeEach
    void setup() {
        // Cmv reads everything from Main.* (globals), so each test has to set them up fresh.
        Main.PARAMETERS = new Main.Parameters();

        // Reasonable defaults so tests only override what they actually need.
        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 2.0;
    }

    // ---------------- LIC6 ----------------

    @Test
    void lic6_true_whenPointFartherThanDIST_fromLine() {
        // Simple triangle: endpoints form the x-axis, middle point is clearly above it.
        // If LIC6 is correct, it should detect the middle point is more than DIST away.
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 2};
        Main.Y = new double[]{0, 2, 0};

        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;  // middle point is distance 2 from the line, so this should be true

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    @Test
    void lic6_false_whenAllPointsWithinDIST_ofLine() {
        // Same idea as the previous test, but now the middle point is close to the line.
        // So LIC6 should NOT trigger.
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 2};
        Main.Y = new double[]{0, 0.5, 0};

        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;  // distance is 0.5 which is not > 1.0

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[6]);
    }

    @Test
    void lic6_true_whenEndpointsCoincide_andOtherPointFar() {
        // Special case in the spec: if first and last points are the same,
        // LIC6 uses distance from that point instead of line distance.
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 2, 0};
        Main.Y = new double[]{0, 0, 0};

        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;  // middle point is 2 units away from (0,0)

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    @Test
    void lic6_true_inLaterWindow_whenFirstWindowFalse() {
        // This checks the "sliding window" behavior for N_PTS > 3 scenarios.
        // The first window(s) shouldn't trigger, but a later one should.
        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 1, 2, 3, 4};
        Main.Y = new double[]{0, 0, 0, 5, 0};

        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    // ---------------- LIC7 ----------------

    @Test
    void lic7_true_whenTwoPointsWithKPtsBetween_areFartherThanLENGTH1() {
        // LIC7 compares points with exactly K_PTS points in between.
        // Here, points 0 and 2 are compared (1 point in between) and are far apart.
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 10};
        Main.Y = new double[]{0, 0, 0};

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 5.0;  // distance is 10, so it should trigger

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[7]);
    }

    @Test
    void lic7_false_whenDistanceNotGreaterThanLENGTH1() {
        // Same configuration, but LENGTH1 is bigger than the distance,
        // so LIC7 should be false.
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 10};
        Main.Y = new double[]{0, 0, 0};

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 15.0;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[7]);
    }

    @Test
    void lic7_true_withKPtsGreaterThan1() {
        // Check K_PTS > 1 so we don't only pass the "easy" case.
        // With K_PTS = 3, we compare point 0 and point 4.
        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 0, 0, 10};
        Main.Y = new double[]{0, 0, 0, 0, 0};

        Main.PARAMETERS.K_PTS = 3;
        Main.PARAMETERS.LENGTH1 = 5.0;

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[7]);
    }

    // ---------------- LIC8 ----------------

    @Test
    void lic8_true_whenTripleCannotFitInCircleRadius1() {
        // LIC8 uses a triple separated by A_PTS and B_PTS.
        // With A=1 and B=1 in a 5-point list, the triple is indices (0,2,4).
        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 0};
        Main.Y = new double[]{0, 0, 0, 0, 3};

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;

        // The farthest distance here is 5, so diameter would need to be at least 5.
        // Radius 2 gives diameter 4 -> not enough -> should be true.
        Main.PARAMETERS.RADIUS1 = 2.0;

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[8]);
    }

    @Test
    void lic8_false_whenTripleFitsInCircleRadius1() {
        // Same points as above, but now the radius is large enough.
        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 0};
        Main.Y = new double[]{0, 0, 0, 0, 3};

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 3.0;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[8]);
    }

    @Test
    void lic8_collinearPoints_behavesCorrectly() {
        // Collinear case: all three points on a straight line.
        // Minimal circle is based on the two farthest points (radius = maxDist/2).
        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 8};
        Main.Y = new double[]{0, 0, 0, 0, 0};

        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;

        // max distance is 8 => minimum radius needed is 4
        Main.PARAMETERS.RADIUS1 = 3.9;
        assertTrue(Cmv.computeCMV()[8]);  // should fail to fit

        Main.PARAMETERS.RADIUS1 = 4.0;
        assertFalse(Cmv.computeCMV()[8]); // should fit exactly on the boundary
    }
}
