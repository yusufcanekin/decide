public class Pum {

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
