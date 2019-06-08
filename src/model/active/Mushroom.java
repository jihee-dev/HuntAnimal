package Model.active;

import java.util.Timer;
import java.util.TimerTask;

public class Mushroom extends Animal {
	final int reduceDelay;//
	
    public Mushroom(){
    	this.reduceDelay=5;
    	this.setPrice(0);
    	this.actionInfo.setName("�ӽ÷�");
    	this.actionInfo.setDelay(30);
    	this.actionInfo.setBtnImg(null);//
    }

    public Mushroom(int delay){
    	this.reduceDelay=5;
    	this.setPrice(0);
    	this.actionInfo.setName("�ӽ÷�");
    	this.actionInfo.setDelay(delay);
    	this.actionInfo.setBtnImg(null);//
    }

    public void heal(Hunter hunter){
    	Timer timer=new Timer();
    	TimerTask task=new TimerTask() {
    		public void run() {
    			hunter.getActionInfo().setDelay(hunter.getActionInfo().getDelay()+reduceDelay);
    			timer.cancel();
    		}
    	};
    	hunter.getActionInfo().setDelay(hunter.getActionInfo().getDelay()-this.reduceDelay);
    	timer.schedule(task, 5000);
    }
}
