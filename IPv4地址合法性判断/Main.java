import java.util.Scanner;

public class Main{
    public static boolean Ipv4Match(String str){
        String[] splt_str = str.split("\\.",-1);
        if(splt_str.length == 4){
            for(int i=0;i<=3;++i){
                if(splt_str[i].length() < 1 || splt_str[i].length() > 3){
                    return false;
                }

                for(int j=0;j<=splt_str[i].length()-1;++j){
                    if(splt_str[i].charAt(j) <= '0' || splt_str[i].charAt(j) >= '9'){
                        return false;
                    }
                }

                if(splt_str[i].compareTo("0") == 0){
                    continue;
                }

                if(splt_str[i].charAt(0) == '0'){
                    return false;
                }
                
                if(Integer.parseInt(splt_str[i]) >= 255 || Integer.parseInt(splt_str[i]) <= 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String str = sc.nextLine();
            if(Ipv4Match(str)){
                System.out.println("Y");
            }else{
                System.out.println("N");
            }
        }
    }
}