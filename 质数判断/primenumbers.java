import java.util.InputMismatchException;
import java.util.Scanner;

public class primenumbers {
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            Checker check = new Checker();
            System.out.print("输入正整数，且 > 1: ");
            int num = scanner.nextInt();
            switch(check.check(num)){
                case 1 -> System.out.println("是质数！");
                case -1 -> System.err.println("输入数字范围错误！");
                default -> System.out.println("不是质数！");
            }
        }catch(InputMismatchException e){
            System.err.println("输入类型错误！");
        }
    }
}