import java.util.Scanner;
public class Test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        leap lp = new leap();
        lp.judge(year);
    }
}