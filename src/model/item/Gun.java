package model.item;

import controller.TestMain;

public class Gun extends Item {
	public static Gun instance=null;

    public Gun(){
    	this.setPrice(1500000);
    	this.setCount(0);
    }

    public static Gun getInstance(){
    	if(instance==null)
            instance=new Gun();
          return instance;
    }
    
    public void used() {
    	TestMain.hunter.setIncreRange(0);
    }
}
