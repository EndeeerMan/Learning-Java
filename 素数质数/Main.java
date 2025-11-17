import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Judge jde = new Judge();
        try(Scanner sc = new Scanner(System.in)){
            int a = sc.nextInt();
            sc.nextLine();
            int b = sc.nextInt();
            sc.nextLine();
            if(a % 2 == 0){
                for(int i=a+1;i<=b;i+=2){
                    if(jde.Prime(i) && jde.Palin(i)){
                        System.out.println(i);
                    }
                }
            }else{
                for(int i=a;i<=b;i+=2){
                    if(jde.Prime(i) && jde.Palin(i)){
                        System.out.println(i);
                    }
                }
            }
        }catch(Exception e){
            System.err.println("FUCK YOU!");
        }
    }
}