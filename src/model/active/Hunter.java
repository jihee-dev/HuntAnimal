package model.active;

import java.util.ArrayList;

import model.item.Item;

public class Hunter implements Catchable {
    private Active actionInfo;
    private int money;
    private int asset;
    private Item[] items;
    private ArrayList<Animal> prison;
    private int increRange;
    public static Hunter instance;

    public Hunter() {
    }

    public static Hunter getInstance() {
        return null;
    }

    public Active getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Active actionInfo) {
        this.actionInfo = actionInfo;
    }

    public int getMoney() {
        return money;
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
    }

    public void buyItem(Item it) {
    }

    public void useItem(Item i) {
    }

    public void catchAni(Animal ani) {
    }

}
