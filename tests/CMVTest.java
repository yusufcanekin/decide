import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class CMVTest {
    /**
     * Positive test: All LIC conditions return true
     * Parameters are tuned to satisfy all 15 LIC conditions
     */
    @Test
    public void testComputeCMV_AllTrue() {
        Main.NUMPOINTS = 5;
        Main.PARAMETERS = new Main.Parameters();
        
        Main.PARAMETERS.LENGTH1 = 0.5;
        Main.PARAMETERS.RADIUS1 = 0.5;
        Main.PARAMETERS.EPSILON = 1.0;
        Main.PARAMETERS.AREA1 = 0.5;
        
        Main.PARAMETERS.Q_PTS = 4;
        Main.PARAMETERS.QUADS = 1;
        
        Main.PARAMETERS.DIST = 0.5;
        Main.PARAMETERS.N_PTS = 3;
        
        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.G_PTS = 1;
        
        Main.PARAMETERS.LENGTH2 = 3.0;
        Main.PARAMETERS.RADIUS2 = 5.0;
        Main.PARAMETERS.AREA2 = 5.0;

        Main.X = new double[] {0.0, 1.0, 2.0, 4.0, -1.0};
        Main.Y = new double[] {0.0, 1.0, 2.0, 0.0, 0.0};

        boolean[] cmv = Cmv.computeCMV();

        for (int i = 0; i < 15; i++) {
            assertTrue("CMV[" + i + "] should be true", cmv[i]);
        }
    }

    /**
     * Negative test: All LIC conditions return false
     * Parameters are set to violate all 15 LIC conditions
     */
    @Test
    public void testComputeCMV_AllFalse() {
        Main.NUMPOINTS = 2;
        Main.PARAMETERS = new Main.Parameters();
        
        Main.PARAMETERS.LENGTH1 = 1000.0;  
        Main.PARAMETERS.RADIUS1 = 100.0;   
        Main.PARAMETERS.EPSILON = Main.PARAMETERS.PI; 
        Main.PARAMETERS.AREA1 = 1000.0;    

        Main.PARAMETERS.Q_PTS = 2;
        Main.PARAMETERS.QUADS = 4;       

        Main.PARAMETERS.DIST = 0.00001;    
        Main.PARAMETERS.N_PTS = 2;

        Main.PARAMETERS.K_PTS = 1;
        Main.PARAMETERS.A_PTS = 1;
        Main.PARAMETERS.B_PTS = 1;
        Main.PARAMETERS.C_PTS = 1;
        Main.PARAMETERS.D_PTS = 1;
        Main.PARAMETERS.E_PTS = 1;
        Main.PARAMETERS.F_PTS = 1;
        Main.PARAMETERS.G_PTS = 1;

        Main.PARAMETERS.LENGTH2 = 0.00001;
        Main.PARAMETERS.RADIUS2 = 0.001;
        Main.PARAMETERS.AREA2 = 0.00001;

        Main.X = new double[] {0.0, 1.0, 2.0, 3.0, 4.0};
        Main.Y = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};

        boolean[] cmv = Cmv.computeCMV();

        for (int i = 0; i < 15; i++) {
            assertFalse("CMV[" + i + "] should be false", cmv[i]);
        }
    }
}
