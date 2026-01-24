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

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CMVTest.class);
    }
}

