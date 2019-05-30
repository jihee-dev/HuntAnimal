package Model;
public class HunterDog extends Animal implements Attackable {
    private int level;
    private int hp;
    public static HunterDog instance;

    public void attack(Animal ani){
    }

    public void setLevel(int a){
    }

    public int getLevel(){
        return 0;
    }

    public void setHp(){
    }

    public int getHp(){
        return 0;
    }

    public void move(int speed){
    }

    private HunterDog(){
    }

    public HunterDog getInstance(){
        return null;
    }

}
