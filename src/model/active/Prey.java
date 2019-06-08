package model.active;

import java.util.ArrayList;

public class Prey extends Animal {

	public Prey(){
    	this.setPrice(0);
    	this.actionInfo.setName(null);
    	this.actionInfo.setDelay(0);
    	this.actionInfo.setBtmImg(null);
    }

    public Prey(int delay){
    	this.setPrice(0);
    	this.actionInfo.setName(null);
    	this.actionInfo.setDelay(delay);
    	this.actionInfo.setBtmImg(null);
    }

    public Prey(int delay, int price){
    	this.setPrice(price);
    	this.actionInfo.setName(null);
    	this.actionInfo.setDelay(delay);
    	this.actionInfo.setBtmImg(null);
    }
    
    public Prey(int delay, int price, String name, ArrayList<String> btnImg){//
    	this.setPrice(price);
    	this.actionInfo.setName(name);
    	this.actionInfo.setDelay(delay);
    	this.actionInfo.setBtmImg(btnImg);
    }

}
