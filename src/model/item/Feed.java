package model.item;

import model.active.HunterDog;

public class Feed extends Item {
	private String btmImg;
    public static Feed instance = null;
    final int increment;

    private Feed() {
        this.increment = 20;
        this.setPrice(5000);
        this.setCount(0);
        this.setBtmImg("");//
    }
    
    public String getBtmImg() {
        return btmImg;
    }

    public void setBtmImg(String path) {
        this.btmImg=path;
    }

    public static Feed getIstance() {
        if (instance == null)
            instance = new Feed();
        return instance;
    }

    public void healDog(HunterDog dog) {
        dog.setHp(dog.getHp() + this.increment);
    }

    public void used() {
        this.setCount(this.getCount() - 1);
    }
}
