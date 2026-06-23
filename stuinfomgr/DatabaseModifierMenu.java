import java.util.Scanner;

public class DatabaseModifierMenu {
    public static void menu(){
        menu(PublicScanner.sc);
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void menu(Scanner sc){
            System.out.println("========================");
            System.out.println("【1】增加一个学生");
            System.out.println("【2】增加指定学生的科目");
            System.out.println("【3】删除指定学生");
            System.out.println("【4】删除指定学生的指定科目");
            System.out.println("【5】修改指定学生的指定科目");
            System.out.println("【6】修改指定学生的基本信息");
            System.out.println("【0】返回上一级菜单");
            System.out.println("========================");
            System.out.print("请输入你的选项：");

        int switchNum = PublicScanner.readInt(sc);
        switch(switchNum){
            case 0 -> {
                PublicScanner.clearScreen();
                System.out.println("返回上一级菜单！");
                return;
            }
            case 1 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.addOneStu(sc);
            }
            case 2 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.addOneSubject(sc);
            }
            case 3 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.delAStudent(sc);
            }
            case 4 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.delASubject(sc);
            }
            case 5 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.modifyASubject(sc);
            }
            case 6 -> {
                PublicScanner.clearScreen();
                DatabaseModifier.modifyAStudent(sc);
            }
            default -> {
                PublicScanner.clearScreen();
                System.out.println("输入选项错误！");
            }
        }
    }
}
