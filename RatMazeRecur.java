public class RatMazeRecur{
    private static final int N = 7;

    private static void printArr(int[][] arr, int x, int y){
        for(int i=0; i<x; ++i){
            for(int j=0; j<y; ++j){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static boolean isInBound(int x, int y, int[][] maze){
        return (x>=0 && x<N && y>=0 && y<N);
    }

    private static boolean isSafe(int x, int y, int[][] maze){
        return (maze[x][y]==0);
    }

    private static int solveMazeUtil(int x, int y, int[][] maze, 
    int[][] soln){
        if(x == N-1 && y==N-1){
            soln[x][y] = 1;
            return soln[x][y];
        }
        
        if(isInBound(x,y,maze)){
            if(isSafe(x,y,maze)){
                soln[x][y] = solveMazeUtil(x+1,y,maze,soln) + solveMazeUtil(x,y+1,maze,soln);
            }else{
                soln[x][y] = 0;
            }
        }else{
            return 0;
        }

        return soln[x][y];
    }

    private static int solveMaze(int[][] maze){
        int[][] soln = new int[N][N];
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                soln[i][j] = 0;
            }
        }

        int output = solveMazeUtil(0,0,maze,soln);
        printArr(soln,N,N);

        return output;
    }

    public static void main (String args[]){
        
        int maze[][] = 
        {
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 1, 0},
            {1, 1, 1, 1, 0, 0, 0}
        };

        /*int maze[][] = 
        {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };*/

        System.out.println("Total Solutions: " + solveMaze(maze)); 
    }

}