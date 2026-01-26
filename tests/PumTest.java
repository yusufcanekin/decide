import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

public class PumTest {
  public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PumTest.class);
        System.out.println(result.getFailures());
    }


    @Test
    public void testComputePUM() {
        
    }
}
