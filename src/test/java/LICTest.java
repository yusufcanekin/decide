import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class LICTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LICTest.class);
        System.out.println(result.getFailures());
    }
    
    /**
     * Asserts true for:
     * - Input contains at least two points
     * - There exists one pair of consecutive points
     * - The distance between that pair is strictly greater than LENGTH1 
     */
    @Test
    public void testLic0BasicTrueCases() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 5.0;
        
        Main.X = new double[] {0.0, 6.0, 10.0};
        Main.Y = new double[] {0.0, 0.0, 0.0};
        boolean[]cmv = Cmv.computeCMV();     

        assertTrue(cmv[0]);

    }
    /**
     * Asserts false for:
     * - Input contains multiple points
     * - All consecutive distances are less than or equal to LENGTH1
     */
    @Test
    public void testLic0BasicFalseCases() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 5.0;  

        Main.X = new double[] {0.0, 1.0, 3.0};
        Main.Y = new double[] {0.0, 1.0, 3.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);   
    }
    /**
     * Asserts false for:
     * - Input containing at least two points
     * - Distance between pair is exactly equal to LENGTH1
     */
    @Test
    public void testLic0BoundaryCase() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.X = new double[] {0.0, 3.0};
        Main.Y = new double[] {0.0, 4.0};
        boolean[] cmv = Cmv.computeCMV(); 
        
        assertFalse(cmv[0]);
    }

    /**
     * Asserts false for:
     * - Input containing less than two points 
     */
    @Test
    public void testLic0EdgeCase() {
        //Input contains less than two points
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.X = new double[] {0.0};
        Main.Y = new double[] {0.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);
    }
    /**
     * Asserts false for:
     * - LENGTH1 equal to zero
     */
    @Test
    public void testLic0ZeroLength() {
        //LENGTH1 is zero
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 0.0;  
              
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);
    }
    /**
     * Asserts false for:
     * - Only identical points
     */
    @Test
    public void testLic0IdenticalPoints() {
        //Only identical points
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.X = new double[] {1.0, 1.0, 1.0};
        Main.Y = new double[] {1.0, 1.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);
    }

    /**
     * Asserts false for:
     * - Negative RADIUS1
     */
    @Test
    public void testLic1NegativeRadius() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.RADIUS1 = -1.0;

        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[1]);
    }
    /**
     * Asserts false for:
     * - Less than three points
     */
    @Test
    public void testLic1LessThanThreePoints() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.RADIUS1 = 1.0;  
    
        Main.X = new double[] {0.0, 1.0};
        Main.Y = new double[] {0.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[1]);
    }
    /**
     * Asserts false for:
     * - All sets of three consecutive points lie within or on the circle 
     */
    @Test
    public void testLic1WithinRadius() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.RADIUS1 = Math.sqrt(2.0);

        Main.X = new double[] {1.0, 2.0, 3.0};
        Main.Y = new double[] {1.0, 2.0, 3.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[1]);
    }
    /**
     * Asserts true for:
     * - At least one set of three consecutive points that can be contained within or on the circle
     */
    @Test
    public void testLic1NotWithinRadius() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.RADIUS1 = 1.0;

        Main.X = new double[] {0.0, 1.0, 2.0};
        Main.Y = new double[] {0.0, 1.0, 2.0};
        boolean[] cmv = Cmv.computeCMV();

        assertTrue(cmv[1]);

    }
    /**
     * Asserts false for:
     * - Set of three consecutive points that share the same value
    */
    @Test
    public void testLic2SamePoints() {
        Main.PARAMETERS = new Main.Parameters();
        Main.X = new double[] {0.0, 0.0, 1.0};
        Main.Y = new double[] {0.0, 0.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[2]);
    }
    /**
     * Asserts false for:
     * - Set of less than three consecutive points
     */
    @Test
    public void test2Lic2LessThanThreePoints() {
        Main.PARAMETERS = new Main.Parameters();

        Main.X = new double[] {0.0, 1.0};
        Main.Y = new double[] {0.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[2]);
    }
    /**
     * Asserts true for:
     * - Set of three consecutive points that are valid
     */
    @Test
    public void testLic2ConditionMet(){
        Main.PARAMETERS = new Main.Parameters();

        Main.X = new double[] {0.0, 1.0, 1.0};
        Main.Y = new double[] {0.0, 0.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertTrue(cmv[2]);

    }

    /**
     * LIC 3 should return true if there exists at least one set of
     * three consecutive points forming a triangle with area > AREA1.
     */
    @Test
    public void testLic3_positive_triangleAreaGreaterThanArea1() {
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 4};
        Main.Y = new double[]{0, 3, 0};

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.AREA1 = 5.0;
        boolean[] cmv = Cmv.computeCMV();

        assertTrue("LIC 3 should be true when triangle area > AREA1", cmv[3]);
    }

    /**
     * LIC 3 should return false if all triangles formed by three
     * consecutive points have area <= AREA1.
     */
    @Test
    public void testLic3_negative_triangleAreaNotGreaterThanArea1() {
        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 1};
        Main.Y = new double[]{0, 1, 0};

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.AREA1 = 1.0;
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 3 should be false when triangle area <= AREA1", cmv[3]);
    }

    /**
     * Since the condition cannot be evaluated LIC 3 should return
     * false when the number of data points is less than 3.
     */
    @Test
    public void testLic3_invalidInput_lessThanThreePoints() {
        // Invalid Input
        Main.NUMPOINTS = 2;
        Main.X = new double[]{0, 1};
        Main.Y = new double[]{0, 1};

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.AREA1 = 0.0;
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 3 should be false when NUMPOINTS < 3", cmv[3]);
    }

    /**
     * LIC 4 should return true if there exists at least one set of Q_PTS consecutive points
     * that lie in more than QUADS quadrants.
     */
    @Test
    public void testLic4_positive_moreThanQuadsQuadrantsExists() {
        Main.NUMPOINTS = 4;
        Main.X = new double[]{ 1, -1, -1,  1};
        Main.Y = new double[]{ 1,  1, -1, -1}; // I, II, III, IV

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.Q_PTS = 4;
        Main.PARAMETERS.QUADS = 3;
        boolean[] cmv = Cmv.computeCMV();

        assertTrue("LIC 4 should be true when points span more than QUADS quadrants.", cmv[4]);
    }

    /**
     * LIC 4 should return false if for every window of Q_PTS consecutive points,
     * the number of distinct quadrants is <= QUADS.
     */
    @Test
    public void testLic4_negative_notMoreThanQuadsQuadrants() {
        Main.NUMPOINTS = 4;
        Main.X = new double[]{ 0,  1,  2,  3};
        Main.Y = new double[]{ 0,  0,  1,  2}; // (0,0)->I, (1,0)->I, (2,1)->I, (3,2)->I

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.Q_PTS = 4;
        Main.PARAMETERS.QUADS = 1;
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 4 should be false when # of distinct quadrants <= QUADS.",cmv[4]);
    }

    /**
     * LIC 4 should return false when NUMPOINTS < Q_PTS (invalid).
     */
    @Test
    public void testLic4_invalidInput_numPointsLessThanQpts() {
        Main.NUMPOINTS = 3;
        Main.X = new double[]{ 1, -1, 0};
        Main.Y = new double[]{ 1,  1, 0};

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.Q_PTS = 4;   // It needs 4 points, but we have 3
        Main.PARAMETERS.QUADS = 1;
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 4 should be false when NUMPOINTS < Q_PTS.", cmv[4]);
    }

    /**
     * LIC 5 should return true if there exists at least one pair of
     * consecutive points such that X[i+1] - X[i] < 0.
     */
    @Test
    public void testLic5_positive_decreasingXExists() {
        Main.NUMPOINTS = 3;
        Main.X = new double[]{3.0, 1.0, 2.0}; // 1.0 - 3.0 < 0 (true)
        Main.Y = new double[]{0.0, 0.0, 0.0};
        boolean[] cmv = Cmv.computeCMV();

        assertTrue("LIC 5 should be true when there exists at least one pair meeting the condition X[i+1] - X[i] < 0.", cmv[5]);
    }

    /**
     * LIC 5 should return false if for all consecutive pairs,
     * X[i+1] - X[i] >= 0.
     */
    @Test
    public void testLic5_negative_noDecreasingX() {
        Main.NUMPOINTS = 4;
        Main.X = new double[]{1.0, 2.0, 2.0, 5.0}; // for all i, X[i+1] - X[i] >= 0
        Main.Y = new double[]{0.0, 0.0, 0.0, 0.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 5 should be false when X never decreases.", cmv[5]);
    }

    /**
     * LIC 5 should return false when NUMPOINTS < 2 (invalid).
     */
    @Test
    public void testLic5_invalidInput_lessThanTwoPoints() {
        // Invalid Input
        Main.NUMPOINTS = 1;
        Main.X = new double[]{1.0};
        Main.Y = new double[]{0.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse("LIC 5 should be false when NUMPOINTS < 2.", cmv[5]);
    }


    /**
     * LIC 6 should return true if there exists at least one set of N_PTS consecutive points
     * such that at least one point is at a distance greater than DIST from the line joining
     * the first and last points of the set.
     */
    @Test
    public void lic6_true_whenPointFartherThanDIST_fromLine() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 2};
        Main.Y = new double[]{0, 2, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    /**
     * LIC 6 should return false if for every set of N_PTS consecutive points,
     * all points are within or on distance DIST from the line.
     */
    @Test
    public void lic6_false_whenAllPointsWithinDIST_ofLine() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 2};
        Main.Y = new double[]{0, 0.5, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[6]);
    }

    /**
     * LIC 6 special case: if the first and last points coincide, distance is measured
     * from that point instead of a line.
     */
    @Test
    public void lic6_true_whenEndpointsCoincide_andOtherPointFar() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 2, 0};
        Main.Y = new double[]{0, 0, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    /**
     * LIC 6 sliding window: should be true if any later window triggers.
     */
    @Test
    public void lic6_true_inLaterWindow_whenFirstWindowFalse() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.N_PTS = 3;
        Main.PARAMETERS.DIST = 1.0;

        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 1, 2, 3, 4};
        Main.Y = new double[]{0, 0, 0, 5, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[6]);
    }

    /**
     * LIC 7 should return true if there exists at least one pair of points separated by
     * exactly K_PTS points in between such that the distance between them is > LENGTH1.
     */
    @Test
    public void lic7_true_whenTwoPointsWithKPtsBetween_areFartherThanLENGTH1() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 10};
        Main.Y = new double[]{0, 0, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[7]);
    }

    /**
     * LIC 7 should return false if all such pairs have distance <= LENGTH1.
     */
    @Test
    public void lic7_false_whenDistanceNotGreaterThanLENGTH1() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.LENGTH1 = 15.0;

        Main.NUMPOINTS = 3;
        Main.X = new double[]{0, 0, 10};
        Main.Y = new double[]{0, 0, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[7]);
    }

    /**
     * LIC 7 with K_PTS > 1.
     */
    @Test
    public void lic7_true_withKPtsGreaterThan1() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.K_PTS = 3;
        Main.PARAMETERS.LENGTH1 = 5.0;

        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 0, 0, 10};
        Main.Y = new double[]{0, 0, 0, 0, 0};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[7]);
    }


    /**
     * LIC 8 should return true if there exists at least one triple of points separated by
     * A_PTS and B_PTS that cannot be contained in or on a circle of radius RADIUS1.
     */
    @Test
    public void lic8_true_whenTripleCannotFitInCircleRadius1() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 2.0;

        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 0};
        Main.Y = new double[]{0, 0, 0, 0, 3};

        boolean[] cmv = Cmv.computeCMV();
        assertTrue(cmv[8]);
    }

    /**
     * LIC 8 should return false if all such triples fit in or on a circle of radius RADIUS1.
     */
    @Test
    public void lic8_false_whenTripleFitsInCircleRadius1() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.RADIUS1 = 3.0;

        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 0};
        Main.Y = new double[]{0, 0, 0, 0, 3};

        boolean[] cmv = Cmv.computeCMV();
        assertFalse(cmv[8]);
    }

    /**
     * LIC 8 collinear boundary behavior: radius needed is maxDist/2.
     * Points (0,0), (4,0), (8,0) need radius 4 to fit.
     */
    @Test
    public void lic8_collinearPoints_boundaryBehavior() {
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;

        Main.NUMPOINTS = 5;
        Main.X = new double[]{0, 0, 4, 0, 8};
        Main.Y = new double[]{0, 0, 0, 0, 0};

        Main.PARAMETERS.RADIUS1 = 3.9;
        assertTrue(Cmv.computeCMV()[8]);

        Main.PARAMETERS.RADIUS1 = 4.0;
        assertFalse(Cmv.computeCMV()[8]);
    }

    /**
     * Tests LIC 9: Returns true when an angle is formed that is smaller than the lower bound.
     * Setup:
     * Points: (1,0), (99,99), (0,0), (99,99), (0,1)
     * Spacing: C_PTS=1, D_PTS=1 (Indices 0, 2, and 4 are used)
     * Epsilon: 0.5
     * The vectors formed are (-1, 0) and (0, 1) with (0,0) as the vertex. This forms a 90-degree 
     * angle (PI/2), which is significantly less than (PI - 0.5), satisfying the condition.
     */

    @Test
    public void lic9_true_whenAngleIsSmallerThanLowerBounds(){

    Main.PARAMETERS = new Main.Parameters();
    Main.NUMPOINTS = 5;
    Main.X = new double[] { 1.0, 99.0, 0.0, 99.0, 0.0 };
    Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 1.0 };


    Main.PARAMETERS.C_PTS = 1;
    Main.PARAMETERS.D_PTS=1;


    Main.PARAMETERS.EPSILON=0.5;


    boolean[] cmv = Cmv.computeCMV();


    assertTrue("LIC 9 should be true when angle is significantly smaller than PI", cmv[9]);
    }

    /**
     * Tests LIC 9: Returns false when the angle formed is within the excluded bounds.
     * Setup:
     * Points: (1,0), (99,99), (0,0), (99,99), (-1,0)
     * Spacing: C_PTS=1, D_PTS=1 (Indices 0, 2, and 4 are used)
     * Epsilon: 0.01
     * The points (1,0), (0,0), and (-1,0) are collinear, forming an angle of exactly PI radians. 
     * Since PI is within the range [PI - 0.01, PI + 0.01], the condition is not met.
     */
    @Test
    public void lic9_false_whenAngleIsWithinBounds() {
        Main.NUMPOINTS = 5;


        Main.X = new double[] { 1.0, 99.0, 0.0, 99.0, -1.0 };
        Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 0.0 };

        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.EPSILON = 0.01;


        boolean[] cmv = Cmv.computeCMV();
        
        assertFalse("LIC 9 should be false when the angle is exactly PI", cmv[9]);
    }


    /**
     * Tests that LIC 10 is true when a triangle with area > AREA1 exists.
     * Points: P0(0,0), P2(2,0), P4(0,2) form a triangle with area 2.0.
     * AREA1 is set to 1.0.
     */
    @Test
    public void testLic10AreaIsGreater(){
        
        Main.NUMPOINTS = 5;
        // P0=(0,0), P1=skip, P2=(2,0), P3=skip, P4=(0,2)
        Main.X = new double[] { 0.0, 99.0, 2.0, 99.0, 0.0 };
        Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 2.0 };
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.AREA1 = 1.0; 

        boolean[] cmv = Cmv.computeCMV();
        // Area is 0.5 * |0(0-2) + 2(2-0) + 0(0-0)| = 2.0
        assertTrue("LIC 10 should be true when area (2.0) > AREA1 (1.0)", cmv[10]);


    }
    /**
     * Tests that LIC 10 is false when NUMPOINTS < 5.
     */
    @Test
    public void lic10_false_whenNumPointsTooSmall() {
        Main.NUMPOINTS = 4;
        Main.X = new double[] { 0.0, 1.0, 2.0, 3.0 };
        Main.Y = new double[] { 0.0, 0.0, 0.0, 0.0 };
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.AREA1 = 1.0;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse("LIC 10 must be false if NUMPOINTS < 5", cmv[10]);
    }
    /**
     * Tests that LIC 10 is false when points are collinear (area = 0).
     */
    @Test
    public void lic10_false_whenPointsAreCollinear() {
        Main.NUMPOINTS = 5;
        // Three points on the X-axis: (0,0), (1,0), (2,0)
        Main.X = new double[] { 0.0, 99.0, 1.0, 99.0, 2.0 };
        Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 0.0 };
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.AREA1 = 0.1;

        boolean[] cmv = Cmv.computeCMV();
        assertFalse("LIC 10 should be false for collinear points (area 0)", cmv[10]);
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

    /**
     * Tests LIC 14 with a successful scenario.
     * Sets up two different triangles:
     * 1. A large triangle (Area > AREA1)
     * 2. A small triangle (Area < AREA2)
     * These triangles are formed by different sets of points.
     */
    @Test
    public void testLIC14_Success() {
        Main.NUMPOINTS = 6;
        
        Main.X = new double[]{0, 0, 10, 0, 0, 0.1};
        Main.Y = new double[]{0, 0, 0, 0, 10, 0.1};
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.AREA1 = 40.0; 
        Main.PARAMETERS.AREA2 = 1.0;  
        
        assertTrue( Cmv.computeCMV()[14]);
    }
    /**
     * Tests LIC 14 failure when NUMPOINTS is less than 5.
     * Even if area conditions are met, it should return false.
     */
    @Test
    public void testLIC14_FailureLowNumPoints() {
        Main.NUMPOINTS = 4;
        Main.X = new double[]{0, 10, 0, 1};
        Main.Y = new double[]{0, 0, 10, 1};
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        
        assertFalse( Cmv.computeCMV()[14]);
    }

    /**
     * Tests LIC 14 failure when only one condition is met.
     * In this case, AREA1 is exceeded, but no triangle is smaller than AREA2.
     */
    @Test
    public void testLIC14_FailureOnlyOneCondition() {
        Main.NUMPOINTS = 5;
        
        Main.X = new double[]{0, 0, 10, 0, 10};
        Main.Y = new double[]{0, 0, 0, 0, 10};
        Main.PARAMETERS = new Main.Parameters();
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.AREA1 = 1.0; 
        Main.PARAMETERS.AREA2 = 0.00000001; 
        
        assertFalse( Cmv.computeCMV()[14]);
    }
}

