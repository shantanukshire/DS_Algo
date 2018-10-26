public class Permutation{

    private static String swap(String str, int i, int j){
        char temp;
        char[] charArr = str.toCharArray();
        temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
        return String.valueOf(charArr);
    }

    private static void permutation(String str, int l, int r){
        if(l==r){
            System.out.println(str);
        }else{
            for(int i=l; i<r+1; ++i){
                str = swap(str,l,i);
                permutation(str,l+1,r);
                str = swap(str,l,i);
            }
        }
    }

    public static void main (String args[]){
        String input = "ABCDE";
        permutation(input,0,input.length()-1);
    }

}