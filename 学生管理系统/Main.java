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
                System.out.print("录入请输入1，输出请输入2，修改学生请输入3，终止程序请输入4：");
                selection = sc.nextInt();
                sc.nextLine();
                switch(selection){
                case 1:
                    System.out.println("请依次输入姓名、性别、年龄、年级、学号（前两个为字符串，后三个为数字），每一种信息输入后都要回车：");
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
                    O.Out(Stu,sc.nextInt()-1);
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("请输入序号：");
                    int num = sc.nextInt()-1;
                    sc.nextLine();
                    System.out.println("该序号的学生信息如下：");
                    O.Out(Stu,num);
                    System.out.println("请依次输入姓名、性别、年龄、年级、学号（前两个为字符串，后三个为数字），每一种信息输入后都要回车：");
                    Stu.modName(num,sc.nextLine());
                    Stu.modGender(num,sc.nextLine());
                    Stu.modAge(num,sc.nextInt());
                    sc.nextLine();
                    Stu.modGrade(num,sc.nextInt());
                    sc.nextLine();
                    Stu.modNumber(num,sc.nextLong());
                    sc.nextLine();
                    System.out.println("学生"+num+"修改成功！");
                    break;
                case 4:
                    break outer;
                }
            }
        }catch(InputMismatchException e){
            System.err.println("输入类型错误！");
        }catch(IndexOutOfBoundsException e){
            System.err.println("该序号学生不存在！");
        }catch(Exception e){
            System.err.println("其他错误！");
        }finally{
            System.out.println("程序运行结束！");
        }
    }
}