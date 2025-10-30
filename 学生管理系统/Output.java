class Output {
    public void Out(int num){
        Student Stu = new Student();
        System.out.println(Stu.getName(num));
        System.out.println(Stu.getGender(num));
        System.out.println(Stu.getAge(num));
        System.out.println(Stu.getGrade(num));
        System.out.println(Stu.getNumber(num));
    }
}
