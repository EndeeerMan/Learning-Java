import java.util.Scanner;
import java.util.InputMismatchException;

public class PrimeNumbersCheck {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            long n = sc.nextLong();
            // 特例
            if(n <= 1){
                System.err.print("ERROR!");
                return;
            }
            if(n == 2){
                System.out.print(n + " is a prime number");
                return;
            }
            // >=3则正常判断
            if(n % 2 == 0){ // 筛去偶数减少时间复杂度
                System.out.print(n + " is not a prime number");
                return;
            }
            for(long i=3;i*i<=n;i+=2){ // 只除以 \sqrt n 进一步减少时间复杂度
                if(n % i == 0){
                    System.out.print(n + " is not a prime number");
                    return;
                }
            }
            System.out.print(n + " is a prime number");

        }catch(InputMismatchException E){
            System.err.print("ERROR!");
        }
    }
}
