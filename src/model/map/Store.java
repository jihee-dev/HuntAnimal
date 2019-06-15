package model.map;

import model.active.Hunter;
import model.item.Item;

public class Store extends Map {
	public static Store instance=null;

    private Store(){
    	this.setName("»óÁ¡");
    	this.setBackgroundImg("./resourceFolder/image/store/store.png");
    }

    public static Store getInstance(){
    	if(instance==null)
            instance=new Store();
          return instance;
    }
    
    public void checkMoney(Hunter hunter, Item it) {
    	if(hunter.getMoney()>it.getPrice())
    		hunter.buyItem(it);
    }
}
