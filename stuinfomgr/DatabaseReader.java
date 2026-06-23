import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class DatabaseReader {
    public static void list(){
        list(PublicScanner.sc);
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void list(Scanner sc){
        try(BufferedReader fread = new BufferedReader(new FileReader(".\\database_store\\database.csv",StandardCharsets.UTF_8))){
            String line;

            System.out.println("==========================");
            System.out.println("【1】信息概要");
            System.out.println("【2】列出学生所有信息");
            System.out.println("【3】列出学生单个科目信息");
            System.out.println("【4】列出所有学生信息概要");
            System.out.println("【0】返回上一级菜单");
            System.out.println("==========================");
            System.out.print("请输入选项：");

            int detailedInfoSwitch = PublicScanner.readInt(sc);

            switch (detailedInfoSwitch) {
                case 0 -> {
                    PublicScanner.clearScreen();
                    System.out.println("返回上一级菜单！");
                    return;
                }
                case 1 -> {
                    System.out.print("请输入你需要查询的学生的学号：");
                    String targetRow = PublicScanner.readRequiredLine(sc);

                    boolean flag = false;

                    PublicScanner.clearScreen();

                    while((line = fread.readLine()) != null){
                        int counter = 1;
                        
                        String[] lineData = line.split(",");
                        
                        for(String buf : lineData){
                            if(counter == 1 && !buf.equals(targetRow)){
                                break;
                            }
                            if(counter == 1) System.out.print("当前学生的 学号 姓名 评分：" + buf + " ");
                            if(counter == 2) System.out.print(buf + " ");
                            if(counter == 3) System.out.println(buf);
                            if(counter == 4){
                                System.out.println("该条数据最后一次修改时间：" + buf);
                                flag = true;
                                break;
                            }
                            counter++;
                        }
                    }
                    if(!flag){
                        System.out.println("学号不存在或数据不完整！");
                    }
                    break;
                }

                case 2 -> {
                    System.out.print("请输入你需要查询的学生的学号：");
                    String targetRow = PublicScanner.readRequiredLine(sc);
                    boolean flag = false;

                    PublicScanner.clearScreen();
                    
                    while((line = fread.readLine()) != null){
                        int counter = 1;
                        
                        String[] lineData = line.split(",");

                        for(String buf : lineData){
                            if(counter == 1 && !buf.equals(targetRow)){
                                break;
                            }
                            if(counter == 1)System.out.print("当前学生的 学号 姓名 评分：" + buf + " ");
                            if(counter == 2) System.out.print(buf + " ");
                            if(counter == 3) System.out.println(buf);
                            if(counter == 4){
                                System.out.println("该条数据最后一次修改时间：" + buf);
                                flag = true;
                            }
                            if(counter == 5) System.out.println("以下是该学生每个学科学分以及完成比例：");
                            if(counter > 4 && counter % 3 == 2) System.out.print("学科 " + buf + " ：");
                            if(counter > 4 && counter % 3 == 0) System.out.print(buf + " ");
                            if(counter > 4 && counter % 3 == 1){
                                System.out.println(buf);
                            }
                            
                            counter++;
                        }
                    }
                    if(!flag){
                        System.out.println("学号不存在或数据不完整！");
                    }
                    break;
                }
                
                case 3 -> {
                    System.out.print("请输入你需要查询的学生的学号：");
                    String targetRow = PublicScanner.readRequiredLine(sc);
                    System.out.print("请输入目标科目编号：");
                    String subjectNum = PublicScanner.readRequiredLine(sc);
                    subjectNum = InputDataCheck.formatSubjectId(subjectNum);
                    if(subjectNum == null) {
                        PublicScanner.clearScreen();
                        System.out.println("科目编号必须是不超过5位的数字！");
                        return;
                    }
                    boolean studentFound = false;
                    boolean subjectFound = false;

                    PublicScanner.clearScreen();

                    while((line = fread.readLine()) != null){
                        int counter = 1;
                        
                        String[] lineData = line.split(",");
                        
                        for(String buf : lineData){
                            if(counter == 1 && !buf.equals(targetRow)){
                                break;
                            }
                            if(counter == 1) {
                                System.out.print("当前学生的 学号 姓名 评分：" + buf + " ");
                                studentFound = true;
                            }
                            if(counter == 2) System.out.print(buf + " ");
                            if(counter == 3) System.out.println(buf);
                            if(counter == 4) System.out.println("该条数据最后一次修改时间：" + buf);
                            if(counter == 5) System.out.println("以下是该学生学科学分以及完成比例：");
                            if(counter > 4 && counter % 3 == 2 && buf.equals(subjectNum)){
                                System.out.print("学科 " + buf + " ：");
                                subjectFound = true;
                            }
                            if(counter > 4 && counter % 3 == 0 && subjectFound) System.out.print(buf + " ");
                            if(counter > 4 && counter % 3 == 1 && subjectFound){
                                System.out.println(buf);
                                break;
                            }
                            counter++;
                        } 
                    }
                    if(!studentFound){
                        System.out.println("学号不存在或数据不完整！");
                    } else if(!subjectFound) {
                        System.out.println("该学生没有此科目！");
                    }
                    break;
                }

                case 4 ->{
                    PublicScanner.clearScreen();
                    System.out.println("所有学生的 学号 姓名 评分 最后一次修改时间 如下：");
                    while((line = fread.readLine()) != null){
                        int counter = 1;
                        
                        String[] lineData = line.split(",");

                        for(String buf : lineData){
                            if(counter == 1) System.out.print(buf + "\t\t");
                            if(counter == 2) System.out.print(buf + "\t\t");
                            if(counter == 3) System.out.print(buf + "\t\t");
                            if(counter == 4){
                                System.out.println(buf);
                                break;
                            }
                            counter++;
                        }
                    }
                    break;
                }
                default -> {
                    PublicScanner.clearScreen();
                    System.out.println("没有此选项！");
                }
            }
        }catch(IOException e) {
            System.err.println("文件读写错误！");
        }catch(Exception e){
            System.err.println("其他错误！");
        }
    }
}
