package model.item;

public class Trap extends Item {
    private String btmImg;
	public static Trap instance=null;
    
    private Trap(){
    	this.setCount(0);
    	this.setPrice(30);
    	this.setBtmImg("");//
    }

    public static Trap getInstance(){
    	if(instance==null)
            instance=new Trap();
          return instance;
    }

    public static void setInstance(Trap instance) {
        Trap.instance = instance;
    }

    public String getBtmImg() {
        return btmImg;
    }

    public void setBtmImg(String path) {
        this.btmImg=path;
    }

    public void used(){
    	this.setCount(this.getCount()-1);
    }
}
