import java.math.BigInteger;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger num = sc.nextBigInteger();
        int index = sc.nextInt();
        BigInteger result = BigInteger.ONE;

        BigInteger num1 = num;

        while(index > 0){
            if((index & 1) == 1){
                result = result.multiply(num1);
            }
            index >>= 1;
            if(index > 0) num1.pow(2);
        }
        System.out.println(result);
        sc.close();
    }
}