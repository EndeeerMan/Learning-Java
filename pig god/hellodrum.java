class pig_god{
    public void hello(){
        System.out.println("Hello Dream!");
    }
    public void response(){
        System.out.println("OK, cuming cuming!");
    }
}

class drum{
    public void hello(){
        System.out.println("Hello Pig God!\nI want 和你 peng peng peng peng peng peng~~~");
    }
    public void response(){
        System.out.println("OK, cuming cuming!");
    }
}

public class hellodrum{
    public static void main(String[] args){
        drum Dream = new drum();
        pig_god PigGad = new pig_god();
        PigGad.hello();
        Dream.hello();
        PigGad.response();
    }
}