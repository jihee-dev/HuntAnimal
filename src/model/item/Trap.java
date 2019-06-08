package Model.item;

public class Trap extends Item {
	public static Trap instance=null;
    
    private Trap(){
    	this.setCount(0);
    	this.setPrice(300000);
    	this.setBtnImage(null);
    }

    public static Trap getInstance(){
    	if(instance==null)
            instance=new Trap();
          return instance;
    }

    public void used(){
    	this.setCount(this.getCount()-1);
    }
}
