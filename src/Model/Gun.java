package Model;

public class Gun extends Item {
	public static Gun instance=null;
	
	private Gun() {
		this.price=0;
		this.count=0;
	}
	
	public static Gun getInstance() {
		if(instance==null)
			instance=new Gun();
		
		return instance;
	}
}
