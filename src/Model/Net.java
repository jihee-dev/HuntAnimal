package Model;

public class Net extends Item {
	public static Net instance=null;
	
	private Net() {
		this.price=0;
		this.count=0;
	}
	
	public static Net getInstance() {
		if(instance==null)
			instance=new Net();
		
		return instance;
	}
}
