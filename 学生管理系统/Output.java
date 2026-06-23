class Output extends Student{
    public void Out(Student Stu,int num){
        System.out.println("姓名：" + Stu.getName(num));
        System.out.println("性别：" + Stu.getGender(num));
        System.out.println("年龄：" + Stu.getAge(num));
        System.out.println("年级：" + Stu.getGrade(num));
        System.out.println("学号：" + Stu.getNumber(num));
    }
}
