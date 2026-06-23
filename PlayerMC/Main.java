import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请依次输入Health、Name、Type_family、Position、Float_in_liquid、Navigation属性：");
        RegData data = new RegData();
        data.getData();
        sc.close();
    }
}
