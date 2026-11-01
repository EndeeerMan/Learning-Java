import java.math.BigInteger;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            StringBuilder strbdr;
            StringBuilder strbdr1;
            BigInteger N2;
            BigInteger N;
            int B,K;
            N = sc.nextBigInteger();
            B = sc.nextInt();
            K = sc.nextInt();
            
            strbdr = new StringBuilder(N.toString(B));
            strbdr1 = new StringBuilder(strbdr.toString());
            strbdr1.reverse();
            if(strbdr.compareTo(strbdr1) == 0){
                System.out.println("0");
                System.out.println(strbdr);
                return;
            }

            for(int i=1;i<=K;++i){
                N2 = new BigInteger(strbdr1.toString(),B);
                N = N.add(N2);
                strbdr = new StringBuilder(N.toString(B));
                strbdr1 = new StringBuilder(strbdr.toString());
                strbdr1.reverse();
                if(strbdr.compareTo(strbdr1) == 0){
                    System.out.println(i);
                    System.out.println(strbdr);
                    return;
                }
            }
            System.out.println("-1");
            System.out.println(strbdr);

        }
    }
}