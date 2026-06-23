public class InputDataCheck {
	//正则表达式，匹配数据格式
	private static final String STUDENT_ID_PATTERN = "\\d{10}";
	private static final String SUBJECT_ID_PATTERN = "\\d{1,5}";
	private static final String STUDENT_NAME_PATTERN = "[\\p{L}\\p{N} _·-]+";
	private static final String CREDIT_PATTERN = "\\d+(\\.\\d+)?";
	private static final String ACHIEVEMENT_PATTERN = "\\d+\\.\\d{1,4}";
	private static final String PASSWORD_PATTERN = "^[\\x00-\\x7F]+$";

	public static boolean isStudentId(String input) {
		return isCsvSafeData(input) && input.trim().matches(STUDENT_ID_PATTERN);
	}

	public static boolean isSubjectId(String input) {
		return isCsvSafeData(input) && input.trim().matches(SUBJECT_ID_PATTERN);
	}

	public static boolean isCsvSafeData(String input) {
		return input != null && !input.contains(",");
	}

	public static String formatSubjectId(String input) {
		if (!isSubjectId(input)) {
			return null;
		}

		String subjectId = input.trim();
		return "00000".substring(subjectId.length()) + subjectId;
	}

	public static boolean isStudentName(String input) {
		return isCsvSafeData(input) && input.trim().matches(STUDENT_NAME_PATTERN);
	}

	public static boolean isAchievement(String input) {
		if (!isCsvSafeData(input) || !input.trim().matches(ACHIEVEMENT_PATTERN)) {
			return false;
		}

		double achievement = Double.parseDouble(input.trim());
		return achievement >= 0 && achievement <= 101.0000;
	}

	public static boolean isCredit(String input) {
		if (!isCsvSafeData(input) || !input.trim().matches(CREDIT_PATTERN)) {
			return false;
		}

		double credit = Double.parseDouble(input.trim());
		return credit >= 0;
	}

	public static boolean isPassword(String pwd){
		return pwd != null && pwd.trim().matches(PASSWORD_PATTERN);
	}
}
