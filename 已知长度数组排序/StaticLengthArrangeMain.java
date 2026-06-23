import java.util.InputMismatchException;
import java.util.Scanner;

public class StaticLengthArrangeMain {
    public static void main(String[] args){
        System.out.print("请输入数组长度：");
        try(Scanner sc = new Scanner(System.in)){
            StaticLengthArrangeChecker ck = new StaticLengthArrangeChecker();
            StaticLengthArrangeSorting sorting = new StaticLengthArrangeSorting();
            int size = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[size];
            System.out.print("请输入数组：");
            for(int i=0;i<size;i++){
                arr[i] = sc.nextInt();
            }
            sc.nextLine();
            while(ck.check(arr, size) == -1){
                arr = sorting.sort(arr, size);
            }
            for(int i=0;i<=size-1;i++){
                System.out.print(arr[i] + " ");
            }
            System.out.print('\n');
        }catch(InputMismatchException e){
            System.err.println("输入格式错误！");
        }catch(NegativeArraySizeException e){
            System.err.println("数组长度不能为负数！");
        }catch(Exception e){
            System.err.println("其它错误！");
        }
    }
}