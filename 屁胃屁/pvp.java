import java.util.Scanner;

public class pvp{
    public static void main(String[] args){
        Piggad piggad = new Piggad();
        Drum drum = new Drum();
        try(Scanner scanner = new Scanner(System.in)){
            piggad.setName(scanner.nextLine());
            piggad.setGender(scanner.nextLine());
            piggad.setWin(scanner.nextBoolean());
            scanner.nextLine();
            drum.setName(scanner.nextLine());
            drum.setGender(scanner.nextLine());
            drum.setWin(scanner.nextBoolean());
            scanner.nextLine();
            System.out.println("屁股勾子："+piggad.getName()+' '+piggad.getGender()+' '+piggad.getWin());
            System.out.println("坠母："+drum.getName()+' '+drum.getGender()+' '+drum.getWin());
        }
    }
}