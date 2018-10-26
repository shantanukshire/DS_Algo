//Input in Java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class InputEg{

    private static void printArr(String[] arr, int size){
        System.out.println("");
        System.out.println("Array is---");
        for(int i=0; i<size; ++i){
            System.out.println(arr[i] + " ");
        }
    }

    public static void main (String args[]) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        String[] strList = new String[t];
        int i=0;
        while(i<t){
            strList[i] = bf.readLine();
            ++i;
        }

        printArr(strList,strList.length);

    }
}
