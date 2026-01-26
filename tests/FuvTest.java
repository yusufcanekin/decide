import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FuvTest {
    @Before
    public void setUp() {
        // Initialize the arrays before each test
        Main.PUM = new boolean[15][15];
        Main.PUV = new boolean[15];
    }

    /**
     * This test checks that when PUV[i] is false, FUV[i] should be true
     * regardless of the PUM values for that row.
     */
    @Test
    public void fuv_trueWhenPuvFalse() {
        Main.PUV[3] = false; // Set PUV[3] to false

        boolean[] fuv = Fuv.computeFUV();

        assertTrue("FUV[3] should be true because PUV[3] is false", fuv[3]);
    }

    /**
     * This test checks that if any value in PUM[i] is false,
     * then FUV[i] should be false.
     */
    @Test
    public void fuv_falseWhenPumContainsFalse() {
        // Since Java initializes boolean arrays with 'false', set PUV[2] = true
        Main.PUV[2] = true;

        // Set one element of PUM[2] to false
        Main.PUM[2][5] = false;

        boolean[] fuv = Fuv.computeFUV();

        assertFalse("FUV[2] should be false because PUM[2][5] is false", fuv[2]);
    }

    /**
     * This test checks that when PUV[i] is true and all PUM[i][j] are true,
     * FUV[i] should be true.
     */
    @Test
    public void fuv_trueWhenPuvTrueAndPumAllTrue() {
        // Set all PUM[i][j] values to true
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Main.PUM[i][j] = true;
            }
        }

        Main.PUV[4] = true; // Set PUV[4] to true
        boolean[] fuv = Fuv.computeFUV();

        assertTrue("FUV[4] should be true when PUV[4] is true and all PUM[4][j] are true", fuv[4]);
    }

    /**
     * This test checks that when PUV[i] is true and any PUM[i][j] is false,
     * FUV[i] should be false.
     */
    @Test
    public void fuv_falseWhenPuvTrueAndPumContainsFalse() {
        // Set all PUM[i][j] values to true
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Main.PUM[i][j] = true;
            }
        }

        Main.PUV[4] = true; // Set PUV[4] to true
        Main.PUM[4][5] = false; // Set one element of PUM[4] to false
        boolean[] fuv = Fuv.computeFUV();

        assertFalse("FUV[4] should be false because PUM[4][5] is false", fuv[4]);
    }
}
