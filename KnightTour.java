public class KnightTour{
    private static final int N = 8;

    private static void printArr(int[][] arr, int x, int y){
        for(int i=0; i<x; ++i){
            for(int j=0; j<y; ++j){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static boolean isSafe(int x, int y, int[][] solve){
        return (x>=0 && x<N && y>=0 && y<N && solve[x][y] == -1);   
    }

    public static void solveKT(){
        int[][] solve = new int[N][N];

        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                solve[i][j] = -1;
            }
        }

        int[] xMove = {2,1,-1,-2,-2,-1,1,2};
        int[] yMove = {1,2,2,1,-1,-2,-2,-1};


        solve[0][0] = 0;

        if(!solveKTUtil(0,0,1,xMove,yMove, solve)){
            System.out.println("No valid path found");
        }else{
            System.out.println("Path found !");
            printArr(solve,N,N);
        }
    }

    private static boolean solveKTUtil(int x, int y, int move, int[] xMoves, 
        int[] yMoves, int[][] solve){
            if(move == N*N)
                return true;
            
            for(int k=0; k<N; ++k){
                int next_x = x + xMoves[k];
                int next_y = y + yMoves[k];

                if(isSafe(next_x,next_y,solve)){
                    solve[next_x][next_y] = move;
                    if(solveKTUtil(next_x,next_y,move+1,xMoves,yMoves,solve)){
                        return true;
                    }else{
                        solve[next_x][next_y] = -1;    
                    }
                }
            }

            return false;
    }

    public static void main (String args[]){
        solveKT();
    }
}