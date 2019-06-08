package model.item;

import Controller.TestMain;

public class Net extends Item {
	public static Net instance=null;

    private Net(){
    	this.setPrice(150000);
    	this.setCount(0);
    }

    public static Net getInstance(){
    	if(instance==null)
            instance=new Net();
          return instance;
    }
    
    public void used() {
    	TestMain.hunter.setIncreRange(TestMain.hunter.getIncreRange()+15);
    }
}
