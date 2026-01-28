public class Fuv {
    /**
     * This function computes FUV based on the PUM and PUV.
     * If PUV[i] is false, then FUV[i] should be true (ignore that row).
     * If PUV[i] is true, then FUV[i] should be true only if all PUM[i][j] are true.
     */
    public static boolean[] computeFUV() {
        boolean[] fuv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            // FUV[i] should be set to true if PUV[i] is false (indicating that the associated
            // LIC should not hold back launch)
            if (!Main.PUV[i]) {
                fuv[i] = true;
                continue;
            }

            // If PUV[i] is true, then FUV[i] should be
            // set true if all elements in PUM row i are true
            boolean isValid = true;
            for (int j = 0; j < 15; j++) {
                if (i == j) {
                    continue; // Skip diagonal elements.
                }
                if (!Main.PUM[i][j]) {
                    isValid = false;
                    break;
                }
            }

            fuv[i] = isValid;
        }
        return fuv;
    }
}