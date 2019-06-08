package Model.active;
import java.util.ArrayList;

import Model.item.Feed;
import Model.item.Gun;
import Model.item.Item;
import Model.item.Net;
import Model.item.Trap;

public class Hunter implements Catchable {
	public static Hunter instance=null;
    private Active actionInfo=new Active();
	private int money;
    private int asset;
    private int increRange;
    private Item[] item;
    private ArrayList<Animal> prison;
    
    private Hunter(){
    	this.money=0;
    	this.asset=0;
    	this.increRange=0;
    	this.item= new Item[4];//
    	item[1]=Trap.getInstance();//
    	item[2]=Net.getInstance();//
    	item[3]=Gun.getInstance();//
    	item[4]=Feed.getIstance();//
    	this.prison=new ArrayList<Animal>(null);
    	this.getActionInfo().setName(null);
    	this.getActionInfo().setDelay(40);
    	this.getActionInfo().setBtnImg(null);//
    }

    public static Hunter getInstance(){
    	if(instance==null)
            instance=new Hunter();
          return instance;
    }
    
    public int getMoney(){
        return this.money;
    }

    public void setMoney(int money){
    	this.money=money;
    }

    public int getAsset(){
        return this.asset;
    }

    public void setAsset(int asset){
    	this.asset=asset;
    }

    public int getIncreRange() {
		return increRange;
	}

	public void setIncreRange(int increRange) {
		this.increRange = increRange;
	}

	public Item[] getItem(){
        return this.item;
    }

    public void setItem(Item[] item){
    	this.item=item;
    }

    public ArrayList<Animal> getPrison(){
        return this.prison;
    }

    public void setPrison(ArrayList<Animal> prison){
    	this.prison=prison;
    }
	
    public Active getActionInfo() {
		return actionInfo;
	}

	public void setActionInfo(Active actionInfo) {
		this.actionInfo = actionInfo;
	}

	public void useItem(Item i/*, HunterDog dog*/){
    	if(i==Trap.getInstance())
    		Trap.getInstance().used();
    	else if(i==Net.getInstance())
    		Net.getInstance().used();
    	else if(i==Gun.getInstance())
    		Gun.getInstance().used();
    	else if(i==Feed.getIstance()) {
    		Feed.getIstance().used();
    		//Feed.getIstance().healDog(dog);
    	}
    }

	public void sellAni(Animal ani){
    	this.prison.remove(ani);
    	this.setMoney(this.money+ani.getPrice());
    }

    public void buyItem(Item it){
    	it.setCount(it.getCount()-1);
    	this.setMoney(this.money-it.getPrice());
    }

    public void catchAni(Animal ani/*, Mushroom mushroom, Forest forest*/) {
    	//if(ani==mushroom)
    	//	mushroom.heal(this);
    	//else if
    	//	this.prison.add(ani);
    	//forest.getPrey().remove(ani);
    	
    }
}
