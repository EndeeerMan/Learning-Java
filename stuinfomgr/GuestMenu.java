public class GuestMenu {
    public static void menu() {
        int switchNum = 1;
        System.out.println("欢迎使用学生评分管理系统！（访客查询菜单）");
        System.out.println("以下为选项菜单：");
        
        while(switchNum != 0){
            System.out.println("========================");
            System.out.println("【1】读取数据库数据");
            System.out.println("【0】返回主菜单");
            System.out.println("========================");
            System.out.print("请输入你的选项：");

            switchNum = PublicScanner.readInt();

            switch(switchNum){
                case 1 ->{
                    PublicScanner.clearScreen();
                    DatabaseReader.list(PublicScanner.sc);
                }
                case 0 ->{
                    PublicScanner.clearScreen();
                    System.out.println("返回主菜单！");
                    return;
                }
                default ->{
                    PublicScanner.clearScreen();
                    System.out.println("没有此选项！");
                }
            }
        }
    }
}
