public class Cmv {
    public static double EPSILON = 0.01;
    public static boolean[] computeCMV() {
        boolean[] cmv = new boolean[15];

        cmv[0]  = lic0();
        cmv[1]  = lic1();
        cmv[2]  = lic2();
        cmv[3]  = lic3();
        cmv[4]  = lic4();
        cmv[5]  = lic5();
        cmv[6]  = lic6();
        cmv[7]  = lic7();
        cmv[8]  = lic8();
        cmv[9]  = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();

        return cmv;
    }

    // LIC stubs

    private static boolean lic0()  { 
        /**
         * There exists at least one pair of consecutive points data points
         * whose distance is greater than LENTGH1, apart
         * 
         * Condition met when: 
         * - LENGTH ispositive
         * - There are at least one set of two points
         * 
         * Condition not when:
         * - LENGTH is less or equal to zero
         * - There are less than one set of two points
         * - All distances between consecutive points are less than 
         *   or equal to LENGTH1 
         */
        if (Main.PARAMETERS.LENGTH1 <= 0) {
            return false;
        }

        if (Main.X.length < 2) {
            return false;
        }
        
        for (int i = 0; i < Main.X.length - 1; i++) {
            double distance = Math.sqrt(Math.pow(Main.X[i+1] - Main.X[i], 2) + Math.pow(Main.Y[i+1] - Main.Y[i], 2));
            
            if (distance > Main.PARAMETERS.LENGTH1) {
                return true;
            }
        }

        return false;
    }   
    /***
     *  There exists at least one set of three consecutive data points 
     * that cannot all be contained
     * within or on a circle of radius RADIUS1.
     * (0 ≤ RADIUS1)
     * 
     * Condition met when:
     * - RADIUS1 is positive
     * - There is at least one set of three consecutive points
     * - Points cannot be contained within or on the circle of radius RADIUS1
     * 
     * Condition not met when:
     * - RADIUS1 is less or equal to zero
     * - There are less than three points
     * - All sets of three consecutive points lie within or on the circle of radius RADIUS1
     * @return
     */
    private static boolean lic1()  { 
        if (Main.PARAMETERS.RADIUS1 <= 0 ) {
            return false;
        }
        if (Main.X.length < 3) {
            return false;
        }

        for (int i = 0; i < Main.X.length - 2; i++) {
            double x0 = Main.X[i];
            double y0 = Main.Y[i];
            double x1 = Main.X[i+1];
            double y1 = Main.Y[i+1];
            double x2 = Main.X[i+2];
            double y2 = Main.Y[i+2];
            
            double radius = getCircleRadius(x0,y0,x1,y1,x2,y2);

            if (radius > Main.PARAMETERS.RADIUS1){
                return true;
            }
        }
        return false; 
    }
        /**
         * Helper function to calculate the radius of the circle
         * passing through three points (x0,y0), (x1,y1), (x2,y2)
         */
        private static double getCircleRadius(double x0, double y0, 
                                            double x1, double y1, 
                                            double x2, double y2) {
           
            /**
             * Calculate the lengths of the sides of the triangle formed by the three points
             */
            double d01 = Math.hypot(x1 - x0, y1 - y0);
            double d12 = Math.hypot(x2 - x1, y2 - y1);
            double d20 = Math.hypot(x2 - x0, y2 - y0);
            
            /**
             * Calculate the area of triangle 
             */
            double area = Math.abs(x0*(y1 - y2) + x1*(y2 -y0) + x2*(y1 - y0)) / 2.0;
            
            /**
             * If area is zero, points are collinear; return half the length of the longest side
             */
            if (Math.abs(area) < 1e-10) {
                return Math.max(Math.max(d01, d12), d20) / 2.0;
            }

            double a = d01;
            double b = d12;
            double c = d20;
        
            double s = (a + b + c) / 2.0;
            double triangleArea = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            
            /**
             * Prevent divison by zero
             */
            if (Math.abs(triangleArea) < 1e-10) {
                return Math.max(Math.max(d01, d12), d20) / 2.0;
            }
             /**
             * Using the formula for circumradius R of a triangle with sides a, b, c:
             * R = (a * b * c) / (4 * A)
             * where A is the area of the triangle.
             */
            return (a * b * c) / (4 * triangleArea);
        }
    
    private static boolean lic2()  { return false; }
    private static boolean lic3()  { return false; }
    private static boolean lic4()  { return false; }
    private static boolean lic5()  { return false; }
    private static boolean lic6()  { return false; }
    private static boolean lic7()  { return false; }
    private static boolean lic8()  { return false; }
    private static boolean lic9()  { return false; }
    /**
     * Evaluates Launch Interceptor Condition (LIC) 10.
     * * <p>The condition is satisfied if there exists at least one set of three data points 
     * (P_i, P_j, P_k) separated by exactly E_PTS and F_PTS consecutive intervening points, 
     * respectively, that form the vertices of a triangle with an area greater than AREA1.
     * * <p>The condition is not met when NUMPOINTS < 5.
     * * @return true if a triangle with area > AREA1 exists with the specified spacing, 
     * false otherwise.
     */
    private static boolean lic10() { 
        int num=Main.NUMPOINTS;
        int epts= Main.PARAMETERS.E_PTS;
        int fpts=Main.PARAMETERS.F_PTS;
        double area1= Main.PARAMETERS.AREA1;
        if(num<5){
            return false;

        }
        if(1>epts || 1>fpts || epts+fpts>num-3){

            return false;

        }
        
        for(int i = 0; i <= num - epts - fpts - 3; i++){
            
        int firstIdx = i;
        int middleIdx = i + epts + 1;
        int lastIdx = middleIdx + fpts + 1;

        double x1 = Main.X[firstIdx], y1 = Main.Y[firstIdx];
        double x2 = Main.X[middleIdx], y2 = Main.Y[middleIdx];
        double x3 = Main.X[lastIdx], y3 = Main.Y[lastIdx];

        double area = calculateArea(x1, y1, x2, y2, x3, y3);

        if (area > area1) {
            return true;
        }


        }
        
        return false; 
    
    }
    /**
     * Calculates the area of a triangle formed by three points (x1, y1), (x2, y2), and (x3, y3).
     * * <p>Uses the coordinate geometry formula (Shoelace formula) to compute the area:
     * 0.5 * |x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)|.
     * @param x1 x-coordinate of the first vertex.
     * @param y1 y-coordinate of the first vertex.
     * @param x2 x-coordinate of the second vertex.
     * @param y2 y-coordinate of the second vertex.
     * @param x3 x-coordinate of the third vertex.
     * @param y3 y-coordinate of the third vertex.
     * @return The absolute area of the triangle.
     */
    private static double calculateArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    return 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
}
    private static boolean lic11() { return false; }
    private static boolean lic12() { return false; }
    private static boolean lic13() { return false; }
    private static boolean lic14() { return false; }
}
