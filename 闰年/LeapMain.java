import java.util.Scanner;
public class LeapMain{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        Leap lp = new Leap();
        lp.judge(year);
        sc.close();
    }
}