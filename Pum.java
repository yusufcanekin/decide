public class Pum {
    /**
     * Computes the Preliminary Unlocking Matrix (PUM) based on the current CMV and LCM.
     * The matrix is computed using the following rules for LCM[i,j]:
     * ANDD:PUM[i,j] is true only if both CMV[i] and CMV[j] are true.
     * ORR: PUM[i,j] is true if at least one of CMV[i] or CMV[j] is true.
     * NOTUSED: PUM[i,j] is automatically set to true.
     * * @return A 15x15 boolean matrix representing the Preliminary Unlocking Matrix.
     */
    public static boolean[][] computePUM() {


        boolean[] cmv = Main.CMV;
        Main.Connector[][] lcm = Main.LCM;
        boolean[][] pum = new boolean[15][15]; 

        for(int i =0;i<lcm.length;i++){

            for(int j = 0;j<lcm[i].length;j++){

                if (i ==j){
                    continue ;
                }

                if(lcm[i][j]==Main.Connector.ANDD){
                  pum[i][j]=  cmv[i]&cmv[j];

                }

                 if(lcm[i][j]==Main.Connector.ORR){
                  pum[i][j] =  cmv[i]||cmv[j];

                }

                 if(lcm[i][j]==Main.Connector.NOTUSED){
                  pum[i][j]=  true;

                }

            }


        }

        return pum;
    }
}
