package Model.map;

import java.util.ArrayList;
import java.util.Random;

import Model.active.Prey;
import Model.active.Animal;

public class Forest extends Map {
private ArrayList<Animal> prey;
    
    public Forest(){
    	this.prey=null;
    	this.setName(null);
    	this.setBackgroundImg(null);
    }

    public Forest(String name){
    	this.prey=null;
    	this.setName(name);
    	this.setBackgroundImg(null);
    }

    public ArrayList<Animal> getPrey(){
        return this.prey;
    }

    public void setPrey(ArrayList<Animal> prey){ 
    	this.prey=prey;
    }
    
    public void enter(int num) { //
    	Random r=new Random();
    	int preyNum=r.nextInt(6)+1;
    	
    	if(num==1) {
	    	for(int i=0;i<preyNum;i++) {
	    		prey.add(new Prey(45,50000,"»ç½¿",null));//
	    	}
	    	for(int i=0;i<6-preyNum;i++) {
	    		prey.add(new Prey(40,50000,"Åä³¢",null));//
	    	}
    	}
    	if(num==2) {
	    	for(int i=0;i<preyNum;i++) {
	    		prey.add(new Prey(35,100000,"È£¶ûÀÌ",null));//
	    	}
	    	for(int i=0;i<6-preyNum;i++) {
	    		prey.add(new Prey(35,100000,"»çÀÚ",null));//
	    	}
    	}
    }
}
