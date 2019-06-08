package model.active;

import java.util.ArrayList;

import model.item.*;

public class Hunter implements Catchable {
    public static Hunter instance = null;
    private Active actionInfo = new Active();
    private int money;
    private int asset;
    private int increRange;
    private Item[] items;
    private ArrayList<Animal> prison;

    private Hunter() {
        this.money = 0;
        this.asset = 0;
        this.increRange = 0;
        this.items = new Item[4];//
        items[1] = Trap.getInstance();//
        items[2] = Net.getInstance();//
        items[3] = Gun.getInstance();//
        items[4] = Feed.getIstance();//
        this.prison = new ArrayList<Animal>(null);
        this.getActionInfo().setName(null);
        this.getActionInfo().setDelay(40);
        this.getActionInfo().setBtnImg(null);//
    }

    public static Hunter getInstance() {
        if (instance == null)
            instance = new Hunter();
        return instance;
    }

    public Active getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Active actionInfo) {
        this.actionInfo = actionInfo;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAsset() {
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public ArrayList<Animal> getPrison() {
        return prison;
    }

    public void setPrison(ArrayList<Animal> prison) {
        this.prison = prison;
    }

    public int getIncreRange() {
        return increRange;
    }

    public void setIncreRange(int increRange) {
        this.increRange = increRange;
    }

    public void sellAni(Animal ani) {
        this.prison.remove(ani);
        this.setMoney(this.money + ani.getPrice());
    }

    public void buyItem(Item it) {
        it.setCount(it.getCount() - 1);
        this.setMoney(this.money - it.getPrice());
    }

    public void useItem(Item i/*, HunterDog dog*/) {
        if (i == Trap.getInstance())
            Trap.getInstance().used();
        else if (i == Net.getInstance())
            Net.getInstance().used();
        else if (i == Gun.getInstance())
            Gun.getInstance().used();
        else if (i == Feed.getIstance()) {
            Feed.getIstance().used();
            //Feed.getIstance().healDog(dog);
        }
    }






    public void catchAni(Animal ani/*, Mushroom mushroom, Forest forest*/) {
        //if(ani==mushroom)
        //	mushroom.heal(this);
        //else if
        //	this.prison.add(ani);
        //forest.getPrey().remove(ani);

    }
}
