package model.item;

public class Trap extends Item {
    private String btmImage;
	public static Trap instance=null;
    
    private Trap(){
    	this.setCount(0);
    	this.setPrice(300000);
    	this.setBtmImage("");//
    }

    public static Trap getInstance(){
    	if(instance==null)
            instance=new Trap();
          return instance;
    }

    public static void setInstance(Trap instance) {
        Trap.instance = instance;
    }

    public String getBtmImage() {
        return btmImage;
    }

    public void setBtmImage(String btmImage) {
        this.btmImage = btmImage;
    }

    public void used(){
    	this.setCount(this.getCount()-1);
    }
}
