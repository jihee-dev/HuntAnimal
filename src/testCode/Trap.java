package testCode;

import java.awt.Point;

public class Trap extends Item {
	public static Trap instance=null;
	private Point location=null;
	
	private Trap() {
		this.price=0;
		this.count=0;
		this.location.setLocation(0, 0);
	}
	
	public static Trap getInstance() {
		if(instance==null)
			instance=new Trap();
		
		return instance;
	}
}
