package Model.item;

public class Gun extends Item {
	public static Gun instance=null;

    public Gun(){
    	this.setPrice(1500000);
    	this.setCount(0);
    	this.setBtnImage(null);
    }

    public static Gun getInstance(){
    	if(instance==null)
            instance=new Gun();
          return instance;
    }
    
    public void used() {
    	//Main.hunter.setIncreRange(0);
    }
}
