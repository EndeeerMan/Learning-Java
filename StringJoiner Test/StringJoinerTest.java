import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.StringJoiner;

public class StringJoinerTest {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            StringJoiner sj = new StringJoiner(" ");
            long n = sc.nextLong();
            for(int i=1;i<=n;i++){
                if(i%3 == 0 && i%5 !=0){
                    sj.add(String.valueOf(i));
                }
            }
            System.out.print(sj.toString());
        }catch(InputMismatchException E){
            System.err.print("ERROR!");
        }
    }
}
