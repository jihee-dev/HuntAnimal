package model.active;

import java.util.ArrayList;

public class Animal {

    private int price;
    private ArrayList<String> btmImg = new ArrayList<String>();
    Active actionInfo = new Active();

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getBtmImg(int index) {
        return btmImg.get(index);
    }

    public void setBtmImg(String path) {
        this.btmImg.add(path);
    }

    public Active getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Active actionInfo) {
        this.actionInfo = actionInfo;
    }
}
