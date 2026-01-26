import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class PumTest {
  public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PumTest.class);
        System.out.println(result.getFailures());
    }

    /**
     * Verifies the specific logic examples provided in the requirements 
     * specification for PUM entry generation.
     * Tested Scenarios:
     * ANDD (False): PUM[0,1] is false if one operand is false.
     * ORR (True): PUM[0,2] and PUM[1,2] are true if at least one operand is true.
     * ANDD (True): PUM[2,3] is true if both operands are true.
     * NOTUSED: PUM[0,4] is true regardless of CMV values.
     */
    @Test
    public void testPumRequirementExamples() {
        // Initialize global variables in Main
        Main.CMV = new boolean[15];
        Main.LCM = new Main.Connector[15][15];

        // Setup CMV values based on the requirements provided
        // Case 1 & 2: CMV[0] is false, CMV[1] is false, CMV[2] is true
        Main.CMV[0] = false; 
        Main.CMV[1] = true;  // Assume true to test ANDD failure with CMV[0]
        Main.CMV[2] = true;
        // Case 4: CMV[2] and CMV[3] must be true
        Main.CMV[3] = true;
        // Case 5: CMV[4] can be anything (using false to prove NOTUSED independence)
        Main.CMV[4] = false;

        // Initialize LCM with NOTUSED
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                Main.LCM[i][j] = Main.Connector.NOTUSED;
            }
        }

        // 1. LCM[0,1] is ANDD
        Main.LCM[0][1] = Main.Connector.ANDD;
        
        // 2. LCM[0,2] is ORR
        Main.LCM[0][2] = Main.Connector.ORR;

        // 3. LCM[1,2] is ORR
        Main.LCM[1][2] = Main.Connector.ORR;

        // 4. LCM[2,3] is ANDD
        Main.LCM[2][3] = Main.Connector.ANDD;

        // 5. LCM[0,4] is NOTUSED
        Main.LCM[0][4] = Main.Connector.NOTUSED;

        // Execute PUM calculation
        boolean[][] pum = Pum.computePUM();

        // Verifications
        assertFalse("PUM[0,1] should be false (false AND true)", pum[0][1]);
        assertTrue("PUM[0,2] should be true (false OR true)", pum[0][2]);
        assertTrue("PUM[1,2] should be true (true OR true)", pum[1][2]);
        assertTrue("PUM[2,3] should be true (true AND true)", pum[2][3]);
        assertTrue("PUM[0,4] should be true (NOTUSED is always true)", pum[0][4]);
    }

}
