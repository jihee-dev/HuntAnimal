package testCode;

import java.util.Scanner;

public class Store extends Map {
	public static Store instance=null;
	Item[] items;
	
	private Store() {
		this.name=null;
		this.items= new Item[4];
		this.items[0]=Trap.getInstance();
		this.items[1]=Net.getInstance();
		this.items[2]=Gun.getInstance();
		this.items[3]=Feed.getInstance();
	}
	
	public static Store getInstance() {
		if(instance==null)
			instance=new Store();
		
		return instance;
	}
	
	public void visitStore(Hunter hunter) {
		int ch=chooseMenu();
		
		while(ch!=3) {
			switch(ch) {
			case 1 : 
				Animal ani=null;
				ani=hunter.chooseAnimal();
				hunter.sellAni(ani);
				buyAnimal(ani);
				break;
			case 2 : 
				Item[] item= {Trap.getInstance(),Net.getInstance(),Gun.getInstance(),Feed.getInstance()};
				int choice=chooseItems();
				hunter.buyItem(choice-1,item[choice-1]);
				sellItem(item[choice-1]);//////////
				break;
			case 3 :
				System.out.println("상점에서 퇴장하셨습니다.");
				break;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	public int chooseMenu() {
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		System.out.println("==========MENU==========");
		System.out.println("  1. 동물 팔기");
		System.out.println("  2. 아이템 사기");
		System.out.println("  3. 나가기");
		System.out.println("========================");
		
		choice=sc.nextInt();
		
		return choice;
	}

	public int chooseItems() {
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		System.out.println("==========MENU==========");
		System.out.println("  1. 트랩");
		System.out.println("  2. 그물");
		System.out.println("  3. 마취총");
		System.out.println("  4. 사료");
		System.out.println("========================");
		
		choice=sc.nextInt();
		
		return choice;
	}
	
	public void buyAnimal(Animal ani){
    	
    }
	
	public void sellItem(Item it){
    	
    }
}
