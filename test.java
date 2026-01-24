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

@Test
public void lic9_true_whenAngleIsSmallerThanLowerBounds(){

    Main.NUMPOINTS = 5;
    Main.X = new double[] { 1.0, 99.0, 0.0, 99.0, 0.0 };
    Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 1.0 };

    Main.PARAMETERS.C_PTS = 1;
    Main.PARAMETERS.D_PTS=1;

    Main.PARAMETERS.EPSILON=0.5;

    boolean[] cmv = Cmv.computeCMV();

    assertTrue("LIC 9 should be true when angle is significantly smaller than PI", cmv[9]);
}
@Test
    public void lic9_false_whenAngleIsWithinBounds() {
        Main.NUMPOINTS = 5;

        Main.X = new double[] { 1.0, 99.0, 0.0, 99.0, -1.0 };
        Main.Y = new double[] { 0.0, 99.0, 0.0, 99.0, 0.0 };

        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.EPSILON = 0.01;

        boolean[] cmv = Cmv.computeCMV();
        
        assertFalse("LIC 9 should be false when the angle is exactly PI", cmv[9]);
    }





}
