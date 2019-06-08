package Model.map;
import java.util.Random;

import Model.active.Hunter;

public class Zoo extends Map {
	public static Zoo instance=null;

    private Zoo(){
    	this.setName("������");
    	this.setBackgroundImg(null);
    }

    public static Zoo getInstance(){
    	if(instance==null)
            instance=new Zoo();
          return instance;
    }

    public boolean changeHunterSpeed(Hunter hunter){
    	Random r=new Random();
    	int num=r.nextInt(10)+1;
    	if(num<=3) {
    		hunter.getActionInfo().setDelay(hunter.getActionInfo().getDelay()+num*3);
    		return false;
    	}
    	else {
    		hunter.getActionInfo().setDelay(hunter.getActionInfo().getDelay()-num);
    		return true;
    	}
    }
}
