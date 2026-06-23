import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

public class Password {  
    public static void set(){
        String storedPwd = null;
        File pwdFile = new File(".\\password");
        if (pwdFile.exists()) {
            try(BufferedReader fread = new BufferedReader(new FileReader(pwdFile))){
                storedPwd = fread.readLine();
            }catch(IOException e){
                System.err.println("密码文件读取错误！");
                return;
            }
        }
        
        try {
            if(storedPwd != null){
                System.out.print("请输入旧密码：");
                String oldPwd = PublicScanner.sc.nextLine();

                if(!InputDataCheck.isPassword(oldPwd)){
                    System.out.println("密码格式不正确！");
                    return;
                }

                if(!ValidatePwd.verify(oldPwd, storedPwd)){
                    System.out.println("旧密码输入错误！");
                    return;
                }
            }else{
                System.out.println("没有设置密码！");
            }

            System.out.print("请输入新密码：");
            String newPwd = PublicScanner.sc.nextLine();

            if(!InputDataCheck.isPassword(newPwd)){
                System.out.println("密码格式不正确！");
                return;
            }

            WritePassword.write(newPwd);
            System.out.println("密码设置完成！");
            
        }catch(InputMismatchException e){
            System.err.println("输入格式错误！");
        }catch(Exception e){
            System.err.println("其他错误！");
        }
    }

    public static boolean verify(){
        String storedPwd = null;
        File pwdFile = new File(".\\password");
        if (pwdFile.exists()) {
            try(BufferedReader fread = new BufferedReader(new FileReader(pwdFile))){
                storedPwd = fread.readLine();
            }catch(IOException e){
                throw new IllegalStateException("密码文件读写错误！",e);
            }
        }

        if(storedPwd == null){
            Password.set();
            return new File(".\\password").exists();
        }
        
        System.out.print("请输入密码：");
        String pwd = PublicScanner.sc.nextLine();
        return ValidatePwd.verify(pwd, storedPwd);
    }

    public static void del(){
        String storedPwd = null;
        File pwdFile = new File(".\\password");
        if (pwdFile.exists()) {
            try(BufferedReader fread = new BufferedReader(new FileReader(pwdFile))){
                storedPwd = fread.readLine();
            }catch(IOException e){
                System.err.println("密码文件读取错误！");
                return;
            }
        }

        if (storedPwd == null) {
            System.out.println("没有设置密码，无需删除！");
            return;
        }

        System.out.print("请输入旧密码以确认删除：");
        String oldPwd = PublicScanner.sc.nextLine();
        
        if (ValidatePwd.verify(oldPwd, storedPwd)) {
            if (pwdFile.delete()) {
                System.out.println("密码删除成功！");
            } else {
                System.out.println("密码删除失败！");
            }
        } else {
            System.out.println("旧密码输入错误，删除失败！");
        }
    }
}

class WritePassword{
    public static void write(String newPwd){
        try(BufferedWriter fprint = new BufferedWriter(new FileWriter(".\\password"))){
            fprint.append(Encrypt.compute(newPwd));
        }catch(IOException e) {
            System.out.println("密码文件读写错误！");
        }
    }
}

class ValidatePwd{
    public static boolean verify(String pwd, String storedPwd){ //验证输入的密码散列计算后是否和存储的散列一致
        if(pwd == null || storedPwd == null){
            return false;
        }
        return Encrypt.isCurrentHash(storedPwd) && storedPwd.equals(Encrypt.compute(pwd));
    }
}

class Encrypt{
    private static final String SALT = "LoveUniKawaiihappychunithm10thanniversary"; //一般的SHA-256散列安全性弱，加盐可大幅增加安全性。
    private static final String CURRENT_PREFIX = "SHA256_CHAR_SALT_V1:";

    public static String compute(String pwd){
        if(pwd == null){
            return null;
        }

        return CURRENT_PREFIX + digest("SHA-256", addSaltByChar(pwd));
    }

    public static boolean isCurrentHash(String storedPwd) {
        return storedPwd != null && storedPwd.startsWith(CURRENT_PREFIX);
    }

    private static String addSaltByChar(String pwd) {
        StringBuilder saltedPwd = new StringBuilder(pwd.length() + SALT.length());
        int maxLength = Math.max(pwd.length(), SALT.length());

        // 将密码字符和盐字符交错拼接：pwd[0] + salt[0] + pwd[1] + salt[1]...
        for (int index = 0; index < maxLength; index++) {
            if (index < pwd.length()) {
                saltedPwd.append(pwd.charAt(index));
            }
            if (index < SALT.length()) {
                saltedPwd.append(SALT.charAt(index));
            }
        }

        return saltedPwd.toString();
    }

    private static String digest(String algorithm, String data) {   //使用指定算法把输入数据进行计算
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] messageDigest = md.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            
            for(byte buf : messageDigest){
                hexString.append(String.format("%02x", buf));
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("不存在指定的加密算法！",e);
        }
    }
}
