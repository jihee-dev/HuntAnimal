public class Hunter implements Huntable {
    private int money;
    private int rand;
    
//money-> this.money�� ����
    public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	//�޼��� ����
	public void ����ϱ�() {
		System.out.println("� ������ ������� �����ؼ� ����� �Ұ����մϴ�!");
	}

    public void ����ϱ�(Animal a){
    	System.out.println("��ɲ��� " + a.getName() + "��/�� ����� �����մϴ�.");
    	//1~100������ ��������
    	rand = (int) (Math.random() * 100)+1;
    	if ((rand <= a.getCatch_per()) && (a.getWhere() instanceof Forest)) {
			System.out.println(a.getName() + " ��ɿ� �����߽��ϴ�.");
			a.getWhere().ani.remove(a);
			a.setWhere(Prison.getInstance());
			
		}
    	
    	else {
    		System.out.println(a.getName() + "��ɿ� �����߽��ϴ�.");
    	}
    }

   //��ɰ� �����ؼ� ����ϴ°� HunterDog�� �ű�.

    public void sell(Animal ani,ZooManager zm,Prison p) {
        if(p.ani.isEmpty()) {//������ �ִ� ������ ���� ���->sell �Ұ�
           System.out.println("������ �ִ� ������ �����ϴ�");
        }
        else {
        	   //�ڵ��ߺ� �����ϰ� ZooManager buy����
        	ani.getWhere().ani.remove(ani);
               this.money+=zm.buy(ani);
        }
     }

 //print cage Map���� �̵�

	
}
/**
@startuml

loop until the game ends
User->Hunter : hunter.����ϱ�()
	alt (rand>=a.catch_per())
		Hunter->Forest : animal.getWhere().ani.remove(animal)
		Hunter->Prison : animal.getWhere().ani.add(animal)
		Hunter->Animal : animal.setWhere(Prison.getInstance())
	else
		Hunter->User : Message : "��ɿ� �����߽��ϴ�"
	end
User->HunterDog : hunter.����ϱ�()
	alt (rand>=a.catch_per()+HunterDog.catch_per())
		HunterDog->Forest : remove.animal
		HunterDog->Prison : add.animal
		HunterDog->Animal : setWhere(Prison.getInstance())
	else
		HunterDog->User : Message : "��ɿ� �����߽��ϴ�"
	end

User->Hunter : Message : "Hunter sell the Animal"
	alt (p.ani.IsEmpty())
		Hunter->User : Message : "������ �ִ� ������ �����ϴ�"
	else
		Hunter->Animal : animal.getWhere().ani.remove(animal)
		alt (zm.getMoney()<ani.getPrice())
			ZooManager->Hunter : Message : "ZooManager�� ���� �����ϴ�. ������ ���� �Ұ�"
		else
			ZooManager->Hunter : hunter.money+=zm.buy(animal)
			Hunter->Animal : animal.setWhere(Zoo.getInstance())
			Hunter->Animal : animal.getWhere().ani.add(animal)
			Hunter->ZooManager : zm.setMoney(zm.getMoney()-animal.getPrice())
		end
	end
	
@enduml
*/