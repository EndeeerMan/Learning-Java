import java.util.Scanner;

class Calculator{
    public int add(int a,int b){
        return a+b;
    }
    public int minus(int a, int b){
        return a-b;
    }
    public int times(int a,int b){
        return a*b;
    }
    public int div(int a,int b){
        return a/b;
    }
}

public class calculate{
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("1.加法 2.减法 3.乘法 4.除法");
            int a = scanner.nextInt();
            scanner.nextLine();
            System.out.println("输入两个数字：");
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            switch(a){
                case 1 -> System.out.println(calc.add(b,c));
                case 2 -> System.out.println(calc.minus(b,c));
                case 3 -> System.out.println(calc.times(b,c));
                case 4 -> System.out.println(calc.div(b,c));
                default -> System.out.println("Fuck You!");
            }
            System.out.println("——————程序正常执行，未错误——————");
        }catch(ArithmeticException e){
            System.out.println("算术异常！");
            System.out.println("Fuck You!");
        }
    }
}