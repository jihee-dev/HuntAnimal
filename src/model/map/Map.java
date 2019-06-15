package model.map;

import java.util.ArrayList;

public class Map {
	private String name;
    private ArrayList<String> backgroundImg;//

    public String getName(){
        return this.name;
    }

    public void setName(String name){
    	this.name=name;
    }

	public String getBackgroundImg(int index) {
		return backgroundImg.get(index);
	}

	public void setBackgroundImg(String path) {
		this.backgroundImg.add(path);
	}
}
