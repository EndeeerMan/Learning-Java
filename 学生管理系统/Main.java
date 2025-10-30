import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Student Stu = new Student();
        Output O = new Output();
        int counter = 0;
        int selection;
        try(Scanner sc = new Scanner(System.in)){
            outer:while(true){
                System.out.print("录入请输入1，输出请输入2，终止程序请输入3：");
                selection = sc.nextInt();
                sc.nextLine();
                switch(selection){
                case 1:
                    System.out.println("请依次输入姓名、性别、年龄、年级、学号（前两个为字符串，后三个为数字），并回车：");
                    Stu.setName(sc.nextLine());
                    Stu.setGender(sc.nextLine());
                    Stu.setAge(sc.nextInt());
                    sc.nextLine();
                    Stu.setGrade(sc.nextInt());
                    sc.nextLine();
                    Stu.setNumber(sc.nextLong());
                    sc.nextLine();
                    System.out.println("录入成功，此为学生为学生 "+ ++counter);
                    break;
                case 2:
                    System.out.print("请输入序号：");
                    O.Out(sc.nextInt());
                    sc.nextLine();
                case 3:
                    break outer;
                }
            }
        }catch(InputMismatchException e){
            System.err.println("输入类型错误！");
        }catch(Exception e){
            System.err.println("其他错误！");
        }finally{
            System.out.println("程序运行结束！");
        }
    }
}