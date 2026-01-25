import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class CMVTest {
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



    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CMVTest.class);
    }

}

