package Model.item;

public class Net extends Item {
	public static Net instance=null;

    private Net(){
    	this.setPrice(150000);
    	this.setCount(0);
    	this.setBtnImage(null);//
    }

    public static Net getInstance(){
    	if(instance==null)
            instance=new Net();
          return instance;
    }
    
    public void used() {
    	//Main.hunter.setIncreRange(Main.hunter.getIncreRange()+15);
    }
}
