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
public void lic11_false_whenNumPointsLessThan3() {
    Main.NUMPOINTS = 2;
    Main.X = new double[] { 1.0, 0.0 };
    Main.Y = new double[] { 0.0, 0.0 };
    Main.PARAMETERS.G_PTS = 1;

    boolean[] cmv = Cmv.computeCMV();
    assertFalse(cmv[11]);
}

@Test
public void lic11_false_whenGPTsOutOfRange() {
    Main.NUMPOINTS = 4;
    Main.X = new double[] {3, 2, 1, 0 };
    Main.Y = new double[] {0, 0, 0, 0 };

    Main.PARAMETERS.G_PTS = 0; // Invalid, must be >= 1

    boolean[] cmv = Cmv.computeCMV();
    assertFalse(cmv[11]);
}




}
