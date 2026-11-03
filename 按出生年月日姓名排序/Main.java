import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

final class IndiviInfo implements Comparable<IndiviInfo>{
    private String name;
    private int year,month,date;

    public IndiviInfo(String name,int year,int month,int date){
        this.name = name;
        this.year = year;
        this.month = month;
        this.date = date;
    }
    
    public String getName(){
        return name;
    }
    
    public int getYear(){
        return year;
    }

    public int gerMonth(){
        return month;
    }

    public int getDate(){
        return date;
    }

    @Override                                       //重写compareTo()，使得其数据本身具有一定顺序
    public int compareTo(IndiviInfo another){
        int cmp;
        cmp = Integer.compare(year, another.getYear());

        if(cmp == 0){
            cmp = Integer.compare(month, another.gerMonth());
        }

        if(cmp == 0){
            cmp = Integer.compare(date, another.getDate());
        }

        if(cmp == 0){
            cmp = name.compareTo(another.getName());
        }

        return cmp;
    }

    @Override                                       //重写equals()，使得不同的对象间可以进行属性比较来判断是否相同
    public boolean equals(Object obj){
        if(this == obj) return true;                //对象相同时，返回true

        if(!(obj instanceof IndiviInfo)) return false;  //对象实例都不同，那肯定是false了
        
        IndiviInfo other = (IndiviInfo) obj;            //已在前面确定obj是IndiviInfo的实例，所以可以直接转换

        return (this.name.equals(other.getName()) && this.year == other.getYear() && this.month == other.gerMonth() && this.date == other.getDate());       //具体属性比较
    }

    @Override                                       //equals()==true，则hashCode()也必须相同
    public int hashCode(){
        return Objects.hash(name,year,month,date);
    }

    @Override                                       //重写toString()，使得输出更方便
    public String toString(){
        return (name + "  " + year + "." + month + "." + date);
    }
}

public class Main{
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            List<IndiviInfo> individuals = new ArrayList<>();

            int n = Integer.parseInt(sc.nextLine());    //输入格式要求：第一行：int整数。

            String name;
            int year,month,date;
                                        
            for(int i=1;i<=n;++i){
                name = sc.next();                       //随后的几行：字符串 int整数 int整数 int整数
                year = sc.nextInt();
                month = sc.nextInt();
                date = sc.nextInt();

                individuals.add(new IndiviInfo(name, year, month, date));
            }

            System.out.println();
            
            Collections.sort(individuals);

            for(IndiviInfo idv : individuals){
                System.out.println(idv);
            }
        }
    }
}