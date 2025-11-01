import java.util.ArrayList;

class Student {
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Gender = new ArrayList<>();
    private ArrayList<Integer> Age = new ArrayList<>();
    private ArrayList<Integer> Grade = new ArrayList<>();
    private ArrayList<Long> Number = new ArrayList<>();
    
    public void setName(String Name){
        this.Name.add(Name);
    }
    public void setGender(String Gender){
        this.Gender.add(Gender);
    }
    public void setAge(int Age){
        this.Age.add(Age);
    }
    public void setGrade(int Grade){
        this.Grade.add(Grade);
    }
    public void setNumber(long Number){
        this.Number.add(Number);
    }
    public String getName(int num){
        return Name.get(num);
    }
    public String getGender(int num){
        return Gender.get(num);
    }
    public int getAge(int num){
        return Age.get(num);
    }
    public int getGrade(int num){
        return Grade.get(num);
    }
    public long getNumber(int num){
        return Number.get(num);
    }
    public void modName(int num,String Name){
        this.Name.set(num,Name);  
    }
    public void modGender(int num,String Gender){
        this.Gender.set(num,Gender);
    }
    public void modAge(int num,int Age){
        this.Age.set(num,Age);
    }
    public void modGrade(int num,int Grade){
        this.Grade.set(num,Grade);
    }
    public void modNumber(int num,long Number){
        this.Number.set(num,Number);
    }
}
