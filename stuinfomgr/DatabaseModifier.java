import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DatabaseModifier {
    private static final Path DATABASE_PATH = Path.of(".\\database_store\\database.csv");
    private static final Path TEMP_DATABASE_PATH = Path.of(".\\database_store\\temp_database.csv");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    private static final int BASE_FIELD_COUNT = 4;  //学生基本数据所占数量
    private static final int SUBJECT_FIELD_COUNT = 3;   //单科目数据所占数量

    public static void addOneStu(){  //增加一个学生
        addOneStu(PublicScanner.sc);
    }

    public static void addOneStu(Scanner sc){  //增加一个学生
        try {
            List<String[]> rows = readRows();

            System.out.print("请输入新学生的学号：");
            String id = readStudentId(sc);
            if (findStudentIndex(rows, id) != -1) {
                System.out.println("该学号已存在！");
                return;
            }

            System.out.print("请输入新学生的姓名：");
            String name = readStudentName(sc);

            System.out.print("请输入需要添加的科目数量：");
            int subjectCount = readNonNegativeInt(sc);
            if (subjectCount < 0) {
                System.out.println("科目数量不能为负数！");
                return;
            }

            List<String> row = new ArrayList<>();
            row.add(id);
            row.add(name);
            row.add("00000");
            row.add(currentTime());

            for (int index = 1; index <= subjectCount; index++) {
                System.out.print("请输入第 " + index + " 个科目的编号（1-5位数字，将自动补足为5位）：");
                String subjectId = readSubjectId(sc);
                if (findSubjectDataIndex(row, subjectId) != -1) {
                    System.out.println("该学生已存在这个科目编号！");
                    return;
                }

                System.out.print("请输入第 " + index + " 个科目的学分：");
                double level = readCredit(sc);
                System.out.print("请输入第 " + index + " 个科目的达成率：");
                double achievement = readAchievement(sc);
                row.add(subjectId);
                row.add(Double.toString(level));
                row.add(String.format(Locale.US, "%.4f", achievement));
            }

            String[] newRow = row.toArray(String[]::new);
            refreshRatingAndTime(newRow);
            rows.add(newRow);
            writeRows(rows);
            System.out.println("学生添加成功！");
        } catch (NumberFormatException e) {
            System.err.println("输入数据格式有误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    public static void addOneSubject(){  //增加一个学生的一个科目学分与科目达成率
        addOneSubject(PublicScanner.sc);
    }

    public static void addOneSubject(Scanner sc){  //增加一个学生的一个科目学分与科目达成率
        try {
            List<String[]> rows = readRows();
            int rowIndex = askStudentIndex(sc, rows);
            if (rowIndex == -1) {
                return;
            }

            String[] row = rows.get(rowIndex);
            List<String> newRow = new ArrayList<>(List.of(row));

            System.out.print("请输入新增科目的编号（1-5位数字，将自动补足为5位）：");
            String subjectId = readSubjectId(sc);
            if (findSubjectDataIndex(row, subjectId) != -1) {
                System.out.println("该学生已存在这个科目编号！");
                return;
            }

            System.out.print("请输入新增科目的学分：");
            double level = readCredit(sc);
            System.out.print("请输入新增科目的达成率：");
            double achievement = readAchievement(sc);

            newRow.add(subjectId);
            newRow.add(Double.toString(level));
            newRow.add(String.format(Locale.US, "%.4f", achievement));

            String[] updatedRow = newRow.toArray(String[]::new);
            refreshRatingAndTime(updatedRow);
            rows.set(rowIndex, updatedRow);
            writeRows(rows);
            System.out.println("科目添加成功！");
        } catch (NumberFormatException e) {
            System.err.println("输入数据格式有误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    public static void delAStudent(){    //删除一个学生
        delAStudent(PublicScanner.sc);
    }

    public static void delAStudent(Scanner sc){    //删除一个学生
        try {
            List<String[]> rows = readRows();
            int rowIndex = askStudentIndex(sc, rows);
            if (rowIndex == -1) {
                return;
            }

            rows.remove(rowIndex);
            writeRows(rows);
            System.out.println("学生删除成功！");
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    public static void delASubject(){    //删除一个学生的一个科目
        delASubject(PublicScanner.sc);
    }

    public static void delASubject(Scanner sc){    //删除一个学生的一个科目
        try {
            List<String[]> rows = readRows();
            int rowIndex = askStudentIndex(sc, rows);
            if (rowIndex == -1) {
                return;
            }

            String[] row = rows.get(rowIndex);
            int subjectCount = getSubjectCount(row);
            if (subjectCount == 0) {
                System.out.println("该学生暂无科目！");
                return;
            }

            printSubjects(row);
            System.out.print("请输入需要删除的科目编号：");
            String subjectId = readSubjectId(sc);
            int dataIndex = findSubjectDataIndex(row, subjectId);
            if (dataIndex == -1) {
                System.out.println("科目编号不存在！");
                return;
            }

            List<String> newRow = new ArrayList<>(List.of(row));
            newRow.remove(dataIndex);           //学生的每一个科目都占用3个csv数据，连续删3次同一个位置即可完全删除该科目
            newRow.remove(dataIndex);
            newRow.remove(dataIndex);

            String[] updatedRow = newRow.toArray(String[]::new);
            refreshRatingAndTime(updatedRow);
            rows.set(rowIndex, updatedRow);
            writeRows(rows);
            System.out.println("科目删除成功！");
        } catch (NumberFormatException e) {
            System.err.println("输入数据格式有误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    public static void modifyASubject(){ //修改学生的一个科目
        modifyASubject(PublicScanner.sc);
    }

    public static void modifyASubject(Scanner sc){ //修改学生的一个科目
        try {
            List<String[]> rows = readRows();
            int rowIndex = askStudentIndex(sc, rows);
            if (rowIndex == -1) {
                return;
            }

            String[] row = rows.get(rowIndex);
            int subjectCount = getSubjectCount(row);
            if (subjectCount == 0) {
                System.out.println("该学生暂无科目！");
                return;
            }

            printSubjects(row);
            System.out.print("请输入需要修改的科目编号：");
            String subjectId = readSubjectId(sc);
            int dataIndex = findSubjectDataIndex(row, subjectId);
            if (dataIndex == -1) {
                System.out.println("科目编号不存在！");
                return;
            }

            System.out.print("请输入新的科目编号（1-5位数字，将自动补足为5位，直接回车则不修改）：");
            String newSubjectId = readOptionalLine(sc);
            if (!newSubjectId.isEmpty()) {
                newSubjectId = InputDataCheck.formatSubjectId(newSubjectId);
                if (newSubjectId == null) {
                    System.out.println("科目编号必须是不超过5位的数字！");
                    return;
                }
                int sameSubjectIndex = findSubjectDataIndex(row, newSubjectId);
                if (!newSubjectId.equals(row[dataIndex]) && sameSubjectIndex != -1) {
                    System.out.println("该学生已存在这个科目编号！");
                    return;
                }
                row[dataIndex] = newSubjectId;
            }

            System.out.print("请输入新的学分（直接回车则不修改）：");
            String levelInput = readOptionalLine(sc);
            if (!levelInput.isEmpty()) {
                if (!InputDataCheck.isCredit(levelInput)) {
                    System.out.println("学分必须是非负数字！");
                    return;
                }
                row[dataIndex + 1] = Double.toString(Double.parseDouble(levelInput));
            }

            System.out.print("请输入新的达成率（直接回车则不修改）：");
            String achievementInput = readOptionalLine(sc);
            if (!achievementInput.isEmpty()) {
                if (!InputDataCheck.isAchievement(achievementInput)) {
                    System.out.println("科目达成率必须是大于等于0且小于等于101.0000的小数，最多4位小数！");
                    return;
                }
                double achievement = Double.parseDouble(achievementInput);
                row[dataIndex + 2] = String.format(Locale.US, "%.4f", achievement);
            }

            refreshRatingAndTime(row);
            rows.set(rowIndex, row);
            writeRows(rows);
            System.out.println("科目修改成功！");
        } catch (NumberFormatException e) {
            System.err.println("输入数据格式有误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    public static void modifyAStudent(){ //修改学生信息
        modifyAStudent(PublicScanner.sc);
    }

    public static void modifyAStudent(Scanner sc){ //修改学生信息
        try {
            List<String[]> rows = readRows();
            int rowIndex = askStudentIndex(sc, rows);
            if (rowIndex == -1) {
                return;
            }

            String[] row = rows.get(rowIndex);
            System.out.print("请输入新的学号（直接回车则不修改）：");
            String newId = readOptionalLine(sc);
            if (!newId.isEmpty() && !InputDataCheck.isStudentId(newId)) {
                System.out.println("学生学号必须是10位数字！");
                return;
            }
            if (!newId.isEmpty() && !newId.equals(row[0]) && findStudentIndex(rows, newId) != -1) {
                System.out.println("该学号已存在！");
                return;
            }

            System.out.print("请输入新的姓名（直接回车则不修改）：");
            String newName = readOptionalLine(sc);
            if (!newName.isEmpty() && !InputDataCheck.isStudentName(newName)) {
                System.out.println("学生姓名不能为空，且只能包含文字、数字、空格、下划线、间隔号和连字符！");
                return;
            }

            if (!newId.isEmpty()) {
                row[0] = newId;
            }
            if (!newName.isEmpty()) {
                row[1] = newName;
            }

            row[3] = currentTime();
            rows.set(rowIndex, row);
            writeRows(rows);
            System.out.println("学生信息修改成功！");
        } catch (IOException e) {
            System.err.println("文件读写错误！");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("其他错误！");
        }
    }

    private static List<String[]> readRows() throws IOException {
        List<String[]> rows = new ArrayList<>();
        if (!Files.exists(DATABASE_PATH)) {
            return rows;
        }

        for (String line : Files.readAllLines(DATABASE_PATH, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                rows.add(line.split(",", -1));
            }
        }
        return rows;
    }

    private static void writeRows(List<String[]> rows) throws IOException {
        Path parentPath = DATABASE_PATH.getParent();
        if (parentPath != null) {
            Files.createDirectories(parentPath);
        }

        List<String> lines = new ArrayList<>();
        for (String[] row : rows) {
            for (String field : row) {
                if (!InputDataCheck.isCsvSafeData(field)) {
                    throw new IllegalArgumentException("数据不能包含英文逗号！");
                }
            }
            lines.add(String.join(",", row));
        }
        Files.write(TEMP_DATABASE_PATH, lines, StandardCharsets.UTF_8);
        try {
            Files.move(TEMP_DATABASE_PATH, DATABASE_PATH, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(TEMP_DATABASE_PATH, DATABASE_PATH, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static int askStudentIndex(Scanner sc, List<String[]> rows) {
        System.out.print("请输入学生学号：");
        String id = readStudentId(sc);
        int rowIndex = findStudentIndex(rows, id);
        if (rowIndex == -1) {
            System.out.println("未找到该学生！");
        }
        return rowIndex;
    }

    private static int findStudentIndex(List<String[]> rows, String id) {
        for (int index = 0; index < rows.size(); index++) {
            String[] row = rows.get(index);
            if (row.length > 0 && row[0].equals(id)) {
                return index;
            }
        }
        return -1;
    }

    private static void refreshRatingAndTime(String[] row) {
        row[2] = formatRating(calcRating(row));
        row[3] = currentTime();
    }

    private static int calcRating(String[] row) {
        int totalRating = 0;
        for (int index = BASE_FIELD_COUNT; index + 2 < row.length; index += SUBJECT_FIELD_COUNT) {
            double level = Double.parseDouble(row[index + 1]);
            double achievement = Double.parseDouble(row[index + 2]);
            totalRating += RatingCalc.calc(level, achievement);
        }
        return totalRating;
    }

    private static String formatRating(int rating) {
        return String.format("%05d", Math.max(0, rating));
    }

    private static String currentTime() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    private static int getSubjectCount(String[] row) {
        return Math.max(0, (row.length - BASE_FIELD_COUNT) / SUBJECT_FIELD_COUNT);
    }

    private static void printSubjects(String[] row) {
        for (int index = BASE_FIELD_COUNT, subjectNumber = 1; index + 2 < row.length; index += SUBJECT_FIELD_COUNT, subjectNumber++) {
            System.out.println("【" + subjectNumber + "】科目编号：" + row[index] + " 学分：" + row[index + 1] + " 达成率：" + row[index + 2]);
        }
    }

    private static int findSubjectDataIndex(String[] row, String subjectId) {
        for (int index = BASE_FIELD_COUNT; index + 2 < row.length; index += SUBJECT_FIELD_COUNT) {
            if (row[index].equals(subjectId)) {
                return index;
            }
        }
        return -1;
    }

    private static int findSubjectDataIndex(List<String> row, String subjectId) {
        for (int index = BASE_FIELD_COUNT; index + 2 < row.size(); index += SUBJECT_FIELD_COUNT) {
            if (row.get(index).equals(subjectId)) {
                return index;
            }
        }
        return -1;
    }

    private static String readRequiredLine(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
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

    private static String readOptionalLine(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (InputDataCheck.isCsvSafeData(input)) {
                return input;
            }
            System.out.print("输入不能包含英文逗号，请重新输入（直接回车则不修改）：");
        }
    }

    private static String readStudentId(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            if (InputDataCheck.isStudentId(input)) {
                return input;
            }
            System.out.print("学生学号必须是10位数字，请重新输入：");
        }
    }

    private static String readStudentName(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            if (InputDataCheck.isStudentName(input)) {
                return input;
            }
            System.out.print("学生姓名只能包含文字、数字、空格、下划线、间隔号和连字符，请重新输入：");
        }
    }

    private static String readSubjectId(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            String subjectId = InputDataCheck.formatSubjectId(input);
            if (subjectId != null) {
                return subjectId;
            }
            System.out.print("科目编号必须是不超过5位的数字，请重新输入：");
        }
    }

    private static int readNonNegativeInt(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                
            }
            System.out.print("请输入非负整数：");
        }
    }

    private static double readCredit(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            if (InputDataCheck.isCredit(input)) {
                return Double.parseDouble(input);
            }
            System.out.print("学分必须是非负数字，请重新输入：");
        }
    }

    private static double readAchievement(Scanner sc) {
        while (true) {
            String input = readRequiredLine(sc);
            if (InputDataCheck.isAchievement(input)) {
                return Double.parseDouble(input);
            }
            System.out.print("科目达成率必须是大于等于0且小于等于101.0000的小数，最多4位小数，请重新输入：");
        }
    }
}
