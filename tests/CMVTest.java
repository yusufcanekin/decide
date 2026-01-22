import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class CMVTest {

    @Test
    public void testLic0BasicTrueCases() {
        //Input contains at least two points
        //There exists one pair of consecutive points
        //The distance between that pair is strictly greater than LENGTH1 
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 5.0;
        
        Main.X = new double[] {0.0, 6.0, 10.0};
        Main.Y = new double[] {0.0, 0.0, 0.0};
        boolean[]cmv = Cmv.computeCMV();     

        assertTrue(cmv[0]);

    }
    public void testLic0BasicFalseCases() {
        //Input contains multiple points
        //All consecutive distances are less than or equal to LENGTH1
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 5.0;  

        Main.X = new double[] {0.0, 1.0, 3.0};
        Main.Y = new double[] {0.0, 1.0, 3.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);   
    }
    public void testLic0BoundaryCase() {
        //At least one pair of consecutive points
        //The distance between that pair is exactly equal to LENGTH1
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 5.0;

        Main.X = new double[] {0.0, 3.0};
        Main.Y = new double[] {0.0, 4.0};
        boolean[] cmv = Cmv.computeCMV(); 
        
        assertFalse(cmv[0]);
    }
    public void testLic0EdgeCase() {
        //Input contains less than two points
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 5.0;

        Main.X = new double[] {0.0};
        Main.Y = new double[] {0.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);
    }
    public void testLic0ZeroLength() {
        //LENGTH1 is zero
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 0.0;

        Main.X = new double[] {0.0, 0.0};
        Main.Y = new double[] {0.0, 0.0};   
        boolean[] cmv = Cmv.computeCMV();

        assertTrue(cmv[0]);
    }
    public void testLic0IdenticalPoints() {
        //Only identical points
        Main.Parameters lic = new Main.Parameters();
        lic.LENGTH1 = 5.0;

        Main.X = new double[] {1.0, 1.0, 1.0};
        Main.Y = new double[] {1.0, 1.0, 1.0};
        boolean[] cmv = Cmv.computeCMV();

        assertFalse(cmv[0]);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CMVTest.class);
    }
}

