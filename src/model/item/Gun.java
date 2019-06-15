package model.item;

import model.active.Hunter;

public class Gun extends Item {
	public static Gun instance=null;

    private Gun(){
    	this.setPrice(1500000);
    	this.setCount(0);
    }

    public static Gun getInstance(){
    	if(instance==null)
            instance=new Gun();
          return instance;
    }
    
    public void used() {
    	Hunter.getInstance().setIncreRange(0);
    }
}
