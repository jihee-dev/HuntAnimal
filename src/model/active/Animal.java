package model.active;

import java.util.ArrayList;

public class Animal {

    private int price;
    private ArrayList<String> btmImg;
    Active actionInfo = new Active();

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public ArrayList<String> getBtmImg() {
        return btmImg;
    }

    public void setBtmImg(ArrayList<String> btmImg) {
        this.btmImg = btmImg;
    }

    public Active getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Active actionInfo) {
        this.actionInfo = actionInfo;
    }
}
