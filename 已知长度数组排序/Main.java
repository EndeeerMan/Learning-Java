import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.print("请输入数组长度：");
        try(Scanner sc = new Scanner(System.in)){
            Checker Checker = new Checker();
            Sorting Sorting = new Sorting();
            int size = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[size];
            System.out.print("请输入数组：");
            for(int i=0;i<size;i++){
                arr[i] = sc.nextInt();
            }
            sc.nextLine();
            while(Checker.Checker(arr, size) == -1){
                arr = Sorting.Sorting(arr, size);
            }
            for(int i=0;i<=size-1;i++){
                System.out.print(arr[i] + " ");
            }
            System.out.print('\n');
        }catch(InputMismatchException e){
            System.err.println("输入格式错误！");
        }
    }
}