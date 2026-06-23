public class RatingCalc {   //学生评分算法
    public static int calc(double level, double achievement) {
        // 达成率只计算不高于100.5000%的部分
        double calcAchievement = Math.min(achievement, 100.5000);
        
        double coeff;   //对应系数
        
        // 根据学科达成率下限匹配系数
        if (achievement >= 100.5000) {
            coeff = 22.4;
        } else if (achievement >= 100.4999) {
            coeff = 22.2;
        } else if (achievement >= 100.0000) {
            coeff = 21.6;
        } else if (achievement >= 99.9999) {
            coeff = 21.4;
        } else if (achievement >= 99.5000) {
            coeff = 21.1;
        } else if (achievement >= 99.0000) {
            coeff = 20.8;
        } else if (achievement >= 98.9999) {
            coeff = 20.6;
        } else if (achievement >= 98.0000) {
            coeff = 20.3;
        } else if (achievement >= 97.0000) {
            coeff = 20.0;
        } else if (achievement >= 96.9999) {
            coeff = 17.6;
        } else if (achievement >= 94.0000) {
            coeff = 16.8;
        } else if (achievement >= 90.0000) {
            coeff = 15.2;
        } else if (achievement >= 80.0000) {
            coeff = 13.6;
        } else if (achievement >= 79.9999) {
            coeff = 12.8;
        } else if (achievement >= 75.0000) {
            coeff = 12.0;
        } else if (achievement >= 70.0000) {
            coeff = 11.2;
        } else if (achievement >= 60.0000) {
            coeff = 9.6;
        } else if (achievement >= 50.0000) {
            coeff = 8.0;
        } else if (achievement >= 40.0000) {
            coeff = 6.4;
        } else if (achievement >= 30.0000) {
            coeff = 4.8;
        } else if (achievement >= 20.0000) {
            coeff = 3.2;
        } else if (achievement >= 10.0000) {
            coeff = 1.6;
        } else {
            coeff = 0.0;
        }
        
        double Rating = level * (calcAchievement / 100.0) * coeff;
        
        return (int) Math.floor(Rating); //最终贡献分数向下取整
    }
}