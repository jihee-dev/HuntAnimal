package model.active;

import java.util.ArrayList;

public class Active {
    private String name;
    private int delay;//
    private ArrayList<String> btmImg;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        if (delay > 2000)
            this.delay = 2000;
        else if (delay < 15)
            this.delay = 15;
        else
            this.delay = delay;
    }

    public ArrayList<String> getBtmImg() {
        return btmImg;
    }

    public void setBtmImg(ArrayList<String> btmImg) {
        this.btmImg = btmImg;
    }
}