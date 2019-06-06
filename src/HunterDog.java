public class HunterDog extends Animal implements Useable, Catchable {
    private int level;
    private int hp;
    public static HunterDog instance;
    private int record;
    private int levelUpPoint;
    private boolean flag;

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

    private HunterDog(){
    }

    public static HunterDog getInstance(){
        return null;
    }

    public void setRecord(int n){
    }

    public int getRecord(){
        return 0;
    }

    public void catchAni(Animal ani){
    }

    public void catchAni(Anima ani, Hunterl hunter){
    }

    public void used(){
    }

}
