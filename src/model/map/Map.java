package model.map;

import java.util.ArrayList;

public class Map {
	private String name;
	protected ArrayList<String> backgroundImg;//

    public String getName(){
        return this.name;
    }

    public void setName(String name){
    	this.name=name;
    }

	public ArrayList<String> getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(ArrayList<String> backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
}
