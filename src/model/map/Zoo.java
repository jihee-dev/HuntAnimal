package model.map;
import java.util.Random;

import model.active.Hunter;

public class Zoo extends Map {
	public static Zoo instance=null;

    private Zoo(){
    	this.setName("µ¿¹°¿ø");
    	this.getBackgroundImg().add(0, "./resourceFolder/image/store/zoobuff.png");
    	this.getBackgroundImg().add(1, "./resourceFolder/image/store/zoodebuff.png");
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
