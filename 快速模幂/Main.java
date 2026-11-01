import java.math.BigInteger;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        BigInteger A,B,M,result;
        try(Scanner sc = new Scanner(System.in)){
            A = sc.nextBigInteger();
            B = sc.nextBigInteger();
            M = sc.nextBigInteger();
            result = BigInteger.ONE;
            if(B.equals(BigInteger.ZERO)){
                result = BigInteger.ONE.mod(M);
            }
            while(!B.equals(BigInteger.ZERO)){
                if(B.testBit(0)){
                    result = result.multiply(A).mod(M);
                }
                A = A.multiply(A).mod(M);
                B = B.shiftRight(1);
            }
            System.out.println(result.toString());
        }catch(Exception e){
            System.err.println("程序错误！");
        }
    }
}
