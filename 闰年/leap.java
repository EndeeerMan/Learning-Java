public class leap {
    public void judge(int year){
        if(((year%4==0)&&(year%100!=0))||(year%400==0)){
            System.out.print("Y");
        }else{
            System.out.println("N");
        }
    }
}
