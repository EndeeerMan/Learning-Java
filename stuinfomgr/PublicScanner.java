import java.io.IOException;
import java.util.Scanner;

public class PublicScanner {
    //建立公共Scanner，防止在其他类里关闭导致System.in流死亡
    public static final Scanner sc = new Scanner(System.in);

    public static int readInt() {
        return readInt(sc);
    }

    public static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("输入格式错误，请重新输入：");
            }
        }
    }

    public static String readRequiredLine(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("输入不能为空，请重新输入：");
                continue;
            }
            if (!InputDataCheck.isCsvSafeData(input)) {
                System.out.print("输入不能包含英文逗号，请重新输入：");
                continue;
            }
            return input;
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
