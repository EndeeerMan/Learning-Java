import java.util.Scanner;

public class RegData {
    private int Health;
    private String Name;
    private int Type_family;         //定义实体分组（1Player、2Animal、3Monster、4Item）
    private int[] Position = new int[3];
    private boolean Physics;            //定义实体是否受重力等物理量
    private boolean Floats_in_liquid;   //定义实体是否能浮在液体上
    private int Navigation;         //定义实体如何移动（1正常移动，2飞行，3游泳）
    private int Attack;

    Scanner sc = new Scanner(System.in);

    public RegData(){
        Health = sc.nextInt();
        Name = sc.next();
        Type_family = sc.nextInt();
        int[] Position = new int[3];
        int x,y,z;
        x = sc.nextInt();
        y = sc.nextInt();
        z = sc.nextInt();
        Position[0] = x;
        Position[1] = y;
        Position[2] = z;
        Floats_in_liquid = sc.nextBoolean();
        Navigation = sc.nextInt();
        if(Type_family == 1){
            System.out.print("请输入Attack：");
            Attack = sc.nextInt();
        }
    }
    public void getData(){
        switch (Type_family) {
            case 1:
                System.out.println("这位玩家 " + Name + " 的属性有：");
                System.out.println("Health：" + Health);
                System.out.println("Attack：" + Attack);
                System.out.println("Position：" + Position[0] + " " + Position[1] + " " + Position[2]);
                System.out.println("Navigation：" + Navigation);
                break;
            case 2:
                
                break;
            case 3:

                break;
            case 4:

                break;
            default:
                throw new AssertionError();
        }
    }
    
}
