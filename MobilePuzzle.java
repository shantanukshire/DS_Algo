import java.util.Scanner;
import java.util.HashMap;

class Stack{
    public int FRONT;
    public int capacity;
    public int size;
    public int[] s;

    public Stack(int cap){
        this.capacity = cap;
        FRONT = -1;
        s = new int[cap];
    }

    public int getSize(){        
        return FRONT+1;
    }

    public int peek(){
        if(FRONT > -1)
            return s[FRONT];
        else
            return -1;
    }
    
    public void push(int value){
        FRONT = (FRONT + 1)%capacity;
        s[FRONT] = value;
    }

    public int pop (int value){
        int popped = s[FRONT];
        FRONT = (FRONT - 1)%capacity;        
        return popped;
    }

}

public class MobilePuzzle{

    private static int INT_MAX = Integer.MAX_VALUE;
    private static int LOW = 0;
    private static int MAX = 999;
    private static HashMap<Integer,Integer> numMap; 

    private static int getLength(int value){
        return String.valueOf(value).toCharArray().length;
    }

    private static int add(int i, int j){
        if(i == INT_MAX || j == INT_MAX)
            return INT_MAX;
        else
            return i+j;
    }

    private static int getOperationOut(int op, int x, int y){
        if(op == 1){
            return x + y;
        }else if(op == 2){
            return x - y;
        }else if(op == 3){
            return x * y;
        }else if(op == 4 && x%y == 0){
            return x / y;
        }else{
            return -1;
        }
    }

    private static int getInvOperation(int op){
        if(op == 1){
            return 2;
        }else if(op == 2){
            return 1;
        }else if(op == 3){
            return 4;
        }else{
            return 3;
        }
    }

    private static boolean isInMap(int value){
        boolean ret = true;
        char[] charArr = String.valueOf(value).toCharArray();
        for(int i=0; i<charArr.length; ++i){
            if(!numMap.containsKey(Integer.parseInt(String.valueOf(charArr[i])))){
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static String getOpStr(int op){
        if(op == 1){
            return "+";
        }else if(op == 2){
            return "-";
        }else if(op == 3){
            return "*";
        }else{
            return "/";
        }
    }

    private static int getMinTouchesUtil(int W, int M, int[] min, 
    boolean[] num_occur, int[] op, int count){
        if(min[W] != INT_MAX){
                return min[W];
        }

        int min_touch = INT_MAX;

        //Every number in N
        for(int i=1; i<min.length; ++i){

            if(min[i] != INT_MAX && !num_occur[i]){
                num_occur[i] = true;

                //Every operation in op
                for(int operation : op){
                    int x = getOperationOut(getInvOperation(operation),W,i);
                    
                    if(x >= LOW && x <= MAX){
                        System.out.println(count + ": " + x + " " + getOpStr(operation) + " " + i + " = " + W);

                        int temp_touches = -1;
                        
                        temp_touches = add(1 + min[i],getMinTouchesUtil(x,M,min,num_occur,op, count+1));
                        
                        if(temp_touches != -1 && temp_touches != INT_MAX && 
                                temp_touches < min_touch){
                            System.out.println("Updating min-touch of " + W + " = " + temp_touches);
                            min_touch = temp_touches;
                        }
                    }
                }

                num_occur[i] = false;
            }
        }

        if(min_touch != INT_MAX && min_touch != -1)
            min[W] = min_touch;

        return min_touch;
    }

    private static int getMinTouches(int[] n, int[] op, int N, int O, int M, int W){
        int[] min = new int[MAX];
        boolean[] num_occur = new boolean[MAX];
        Stack stack = new Stack(MAX);

        for(int i=0; i<MAX; ++i){
            num_occur[i] = false;
            if(isInMap(i))
                min[i] = getLength(i);
            else
                min[i] = INT_MAX;
        }

        return getMinTouchesUtil(W,M,min,num_occur,op,0);

    }


    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int t = Integer.parseInt(sc.nextLine());

        for(int test_case=0; test_case<t; ++test_case){
            numMap = new HashMap<Integer,Integer>();

            String nom = sc.nextLine();
            String nos = sc.nextLine();
            String ops = sc.nextLine();
            int W = Integer.parseInt(sc.nextLine());

            String[] nomArr = nom.split("\\s+");
            String[] nosArr = nos.split("\\s+");
            String[] opsArr = ops.split("\\s+");

            int N = Integer.parseInt(nomArr[0]);
            int O = Integer.parseInt(nomArr[1]);
            int M = Integer.parseInt(nomArr[2]);
            
            int[] n = new int[N];
            for(int i=0; i<N; ++i){
                int number = Integer.parseInt(nosArr[i]);
                n[i] = number;
                numMap.put(number,1);
            }

            int[] op = new int[O];
            for(int i=0; i<O; ++i)
                op[i] = Integer.parseInt(opsArr[i]);
            
            int touches = getMinTouches(n,op,N,O,M,W);
            System.out.println("#" + test_case + " " + touches);
            
        }

    }

}