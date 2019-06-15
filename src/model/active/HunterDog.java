package model.active;

import java.util.ArrayList;

import model.item.Useable;
import model.map.Forest;

public class HunterDog extends Animal implements Useable, Catchable {
    public static HunterDog instance = null;
    private int level;
    private int hp;
    private int record;
    private boolean flag;
    final int levelUpPoint;

    private HunterDog() {
        this.level = 0;
        this.hp = 100;
        this.record = 0;
        this.levelUpPoint = 10;
        this.setPrice(1000000);
        this.actionInfo.setName("사냥개");
        this.actionInfo.setDelay(35);
        this.getBtmImg().add(0, "./resourceFolder/image/animal/HoundLeft.png");
        this.getBtmImg().add(1, "./resourceFolder/image/animal/HoundRight.png");
    }

    public static HunterDog getInstance() {
        if (instance == null)
            instance = new HunterDog();
        return instance;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        if (hp > 100)
            this.hp = 100;
        else if (hp < 0)
            this.hp = 0;
        else
            this.hp = hp;
    }

    public int getRecord() {
        return this.record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void catchAni(Animal ani) {
    }

    public void catchAni(Animal ani, Hunter hunter) {
        this.setRecord(this.record + 1);
        if (this.record >= this.levelUpPoint) {
            this.setLevel(this.level + 1);
            this.setRecord(0);
            if (level >= 10)
                this.setHp(100);
        }
        this.used();
        hunter.catchAni(ani);

        if (ani.getActionInfo().getName().equals("Deer") || ani.getActionInfo().getName().equals("Rabbit")) {
            this.setHp(this.hp - 10); //forest1일 때 -10
        } else {
            this.setHp(getHp() - 15);
        }

    }

    public void used() {
        if (this.hp > 0)
            this.flag = true;
        else
            this.flag = false;
    }
}
