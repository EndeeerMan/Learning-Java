import java.util.Scanner;
import java.util.InputMismatchException;

public class StudentScoreManagementMain {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String name = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine();
            String score = sc.nextLine();
            StudentScore StuScore = new StudentScore();
            StuScore.setName(name);
            StuScore.setAge(age);
            StuScore.setScore(score);
            int flag = 1;
            while(flag!=0){
                flag = sc.nextInt();
                switch (flag) {
                    case 1:
                        System.out.println("Name: "+StuScore.getName());
                        break;
                    case 2:
                        System.out.println("Age: "+StuScore.getAge());
                        break;
                    case 3:
                        System.out.println("Score: "+StuScore.getScore());
                        break;
                    default:
                        break;
                }
            }
        }catch(InputMismatchException e){
            System.err.println("Input Mismatch ERROR!");
        }
    }
}
