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
				System.out.println("�������� �����ϼ̽��ϴ�.");
				break;
			default :
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				break;
			}
		}
	}
	
	public int chooseMenu() {
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		System.out.println("==========MENU==========");
		System.out.println("  1. ���� �ȱ�");
		System.out.println("  2. ������ ���");
		System.out.println("  3. ������");
		System.out.println("========================");
		
		choice=sc.nextInt();
		
		return choice;
	}

	public int chooseItems() {
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		System.out.println("==========MENU==========");
		System.out.println("  1. Ʈ��");
		System.out.println("  2. �׹�");
		System.out.println("  3. ������");
		System.out.println("  4. ���");
		System.out.println("========================");
		
		choice=sc.nextInt();
		
		return choice;
	}
	
	public void buyAnimal(Animal ani){
    	
    }
	
	public void sellItem(Item it){
    	
    }
}
