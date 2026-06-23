public class Player extends Entity {
    private int Attack;

    public void setAttack(int Attack){
        this.Attack = Attack;
    }
    public int getAttack(){
        return Attack;
    }

    public Player(String Name,int Health,int Attack,int[] Position,int Navigation){
        setType_family(1);
        setPhysics(true);
        setName(Name);
        setHealth(Health);
        setPosition(Position);
        setNavigation(Navigation);
        setAttack(Attack);
    }

    
    
}
