class Judge {
    public boolean Prime(int num){
        if(num <= 1) return false;
        if(num == 2) return true;
        if(num % 2 == 0) return false;
        for(int i=3;i<=num/i;i+=2){
            if(num % i == 0) return false;
        }
        return true;
    }
    public boolean Palin(int num) {
        if (num < 0) return false;
        int originalNum = num;
        int reversedNum = 0;
        while (num > 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }
        return originalNum == reversedNum;
    }
}
