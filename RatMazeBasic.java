public class RatMazeBasic{
    private static final int N = 4;

    private static void printArr(int[][] arr, int x, int y){
        for(int i=0; i<x; ++i){
            for(int j=0; j<y; ++j){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static boolean isSafe(int x, int y, int[][] maze){
        return (x>=0 && x<N && y>=0 && y<N && maze[x][y]==1);
    }

    private static boolean solveMazeUtil(int x, int y, int[][] maze, 
    int[][] soln){
        if(x == N-1 && y==N-1){
            soln[x][y] = 1;
            return true;
        }
        
        if(isSafe(x,y,maze)){
            soln[x][y] = 1;

            if(solveMazeUtil(x+1,y,maze,soln))
                return true;

            if(solveMazeUtil(x,y+1,maze,soln))
                return true;
            
            soln[x][y] = 0;
            return false;
        }
        return false;
    }

    private static void solveMaze(int[][] maze){
        int[][] soln = new int[N][N];
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                soln[i][j] = 0;
            }
        }

        if(!solveMazeUtil(0,0,maze,soln)){
            System.out.println("Solution not found..");
        }else{
            System.out.println("Solution found..");
            printArr(soln,N,N);
        }

    }

    public static void main (String args[]){
        
        int maze[][] = {{1, 0, 0, 0}, 
            {1, 1, 0, 1}, 
            {0, 1, 0, 0}, 
            {1, 1, 1, 1} 
        }; 
        solveMaze(maze); 
    }

}