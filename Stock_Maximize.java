import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    private static int max(int i, int j, int k){
        int largest = i;
        if(j > largest)
            largest = j;
        if(k > largest)
            k = largest;
        return largest;
    }
    
    private static int max(int i, int j){
        return (i>j)?i:j;
    }
    
    private static int stockMaxUtil(int i, int current_price, int[] prices, int share_count, int size){
        if(i > size){
            return current_price;
        }else{
            int price = Integer.MIN_VALUE;
            if(share_count > 0){
                price = max(
                    stockMaxUtil(i+1,current_price - prices[i],prices,share_count+1,size),
                    stockMaxUtil(i+1,current_price + share_count*prices[i],prices,0,size),
                    stockMaxUtil(i+1,current_price,prices,share_count,size)
                );
            }else{
                price = max(
                    stockMaxUtil(i+1,current_price - prices[i],prices,share_count+1,size),
                    stockMaxUtil(i+1,current_price,prices,share_count,size)
                );
            }
            return price;
        }
    }

    // Complete the stockmax function below.
    static int stockmax(int[] prices) {
        System.out.println(prices.length);
        return stockMaxUtil(0,0,prices,0,prices.length-1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] prices = new int[n];

            String[] pricesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int pricesItem = Integer.parseInt(pricesItems[i]);
                prices[i] = pricesItem;
            }

            int result = stockmax(prices);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
