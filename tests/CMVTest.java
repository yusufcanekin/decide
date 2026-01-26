import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;
import LICTest.LICTest;

public class CMVTest {
    //Positive Lic0 test
    @Test
    public void Lic0PositiveTest() {
          
        LICTest licTest = new LICTest();

        licTest.testLic0BasicTrueCases();
    }
    //Positive Lic1 test

  
}
