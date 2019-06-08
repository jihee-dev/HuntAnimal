package Model.item;

import java.util.ArrayList;

public class Item implements Useable {
	private int price;
    private int count;
    private ArrayList<String> btnImage;

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
    	this.price=price;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
    	this.count=count;
    }

    public ArrayList<String> getBtnImage() {
		return btnImage;
	}

	public void setBtnImage(ArrayList<String> btnImage) {
		this.btnImage = btnImage;
	}

	public void used() {
	}
}
