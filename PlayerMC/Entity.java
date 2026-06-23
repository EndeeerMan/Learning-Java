public class Entity {
    private int Health;
    private String Name;
    private int Type_family;         //定义实体分组（1Player、2Animal、3Monster、4Item）
    private int[] Position = new int[3];
    private boolean Physics;            //定义实体是否受重力等物理量
    private boolean Floats_in_liquid;   //定义实体是否能浮在液体上
    private int Navigation;         //定义实体如何移动（1正常移动，2飞行，3游泳）

    public int getHealth(){
        return Health;
    }
    public void setHealth(int Health){
        this.Health = Health;
    }

    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }

    public int getType_family(){
        return Type_family;
    }
    public void setType_family(int Type_family){
        this.Type_family = Type_family;
    }

    public int[] getPosition(){
        return Position;
    }
    public void setPosition(int[] Position){
        this.Position = Position;
    }

    public boolean getPhysics(){
        return Physics;
    }
    public void setPhysics(boolean Physics){
        this.Physics = Physics;
    }

    public boolean getFloats_in_liquid(){
        return Floats_in_liquid;
    }
    public void setFloats_in_liquid(boolean Floats_in_liquid){
        this.Floats_in_liquid = Floats_in_liquid;
    }

    public int getNavigation(){
        return Navigation;
    }
    public void setNavigation(int Navigation){
        this.Navigation = Navigation;
    }
}
