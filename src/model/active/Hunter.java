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

    public void useItem(Item i){
    }

    public void sellAni(Animal ani){
    }

    public void buyItem(Item it){
    }

    public Hunter(){
    }

    public static Hunter getInstance(){
        return null;
    }

    public void catchAni(Animal ani){
    }

}
