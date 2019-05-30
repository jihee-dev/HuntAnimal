package Model;

public class Feed extends Item {
	public static Feed instance=null;
	//int feed=0;
	
	private Feed() {
		this.price=0;
		this.count=0;
	}
	
	public static Feed getInstance() {
		if(instance==null)
			instance=new Feed();
		
		return instance;
	}
}
