package testCode;

import java.util.ArrayList;
import java.util.Scanner;

public class Hunter {
	public static Hunter instance=null;
	private int money=0;
	private int asset=0;
	private int[] item;
	private ArrayList<Animal> prison;
	
	private Hunter(){
		this.money=0;
		this.asset=0;
		this.item=new int[4];
		this.item[0]=0;
		this.item[1]=0;
		this.item[2]=0;
		this.item[3]=0;
		prison=new ArrayList<Animal>();
	}
	
	public static Hunter getInstance() {
		if(instance==null)
			instance=new Hunter();
		
		return instance;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getAsset() {
		return asset;
	}
	
	public ArrayList<Animal> getPrison() {
		return this.prison;
	}

	public void setAsset(int asset) {
		this.asset = asset;
	}

	public int getItem(int i) {
		return item[i];
	}

	public void setItem(int i, int item) {
		this.item[i] = item;
	}
	
	public Animal chooseAnimal() {
		Scanner sc=new Scanner(System.in);
		Animal choice=null;
		Animal ani=null;
		
		System.out.println("==========MENU==========");
		for(int i=0;i<this.prison.size();i++)
			System.out.println("  "+(i+1)+". "+this.prison.get(i));////¼öÁ¤
		System.out.println("========================");
		
		choice=(Animal) this.prison.get(sc.nextInt());
		
		return null;
	}
	
	public void sellAni(Animal ani) {
		
	}
	
	public void buyItem(int i, Item it) {
		this.setItem(i, this.getItem(i));
		this.setMoney(this.getMoney()-it.getPrice());
		this.setAsset(this.getAsset());
	}
}
