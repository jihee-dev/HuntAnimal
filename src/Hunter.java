public class Hunter implements Huntable {
    private int money;
    private int rand;
    
//money-> this.money로 수정
    public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	//메세지 수정
	public void 사냥하기() {
		System.out.println("어떤 동물을 사냥할지 안정해서 사냥이 불가능합니다!");
	}

    public void 사냥하기(Animal a){
    	System.out.println("사냥꾼이 " + a.getName() + "을/를 사냥을 시작합니다.");
    	//1~100까지의 난수설정
    	rand = (int) (Math.random() * 100)+1;
    	if ((rand <= a.getCatch_per()) && (a.getWhere() instanceof Forest)) {
			System.out.println(a.getName() + " 사냥에 성공했습니다.");
			a.getWhere().ani.remove(a);
			a.setWhere(Prison.getInstance());
			
		}
    	
    	else {
    		System.out.println(a.getName() + "사냥에 실패했습니다.");
    	}
    }

   //사냥개 포함해서 사냥하는건 HunterDog에 옮김.

    public void sell(Animal ani,ZooManager zm,Prison p) {
        if(p.ani.isEmpty()) {//감옥에 있는 동물이 없는 경우->sell 불가
           System.out.println("감옥에 있는 동물이 없습니다");
        }
        else {
        	   //코드중복 삭제하고 ZooManager buy넣음
        	ani.getWhere().ani.remove(ani);
               this.money+=zm.buy(ani);
        }
     }

 //print cage Map으로 이동

	
}
/**
@startuml

loop until the game ends
User->Hunter : hunter.사냥하기()
	alt (rand>=a.catch_per())
		Hunter->Forest : animal.getWhere().ani.remove(animal)
		Hunter->Prison : animal.getWhere().ani.add(animal)
		Hunter->Animal : animal.setWhere(Prison.getInstance())
	else
		Hunter->User : Message : "사냥에 실패했습니다"
	end
User->HunterDog : hunter.사냥하기()
	alt (rand>=a.catch_per()+HunterDog.catch_per())
		HunterDog->Forest : remove.animal
		HunterDog->Prison : add.animal
		HunterDog->Animal : setWhere(Prison.getInstance())
	else
		HunterDog->User : Message : "사냥에 실패했습니다"
	end

User->Hunter : Message : "Hunter sell the Animal"
	alt (p.ani.IsEmpty())
		Hunter->User : Message : "감옥에 있는 동물이 없습니다"
	else
		Hunter->Animal : animal.getWhere().ani.remove(animal)
		alt (zm.getMoney()<ani.getPrice())
			ZooManager->Hunter : Message : "ZooManager가 돈이 없습니다. 동물을 구매 불가"
		else
			ZooManager->Hunter : hunter.money+=zm.buy(animal)
			Hunter->Animal : animal.setWhere(Zoo.getInstance())
			Hunter->Animal : animal.getWhere().ani.add(animal)
			Hunter->ZooManager : zm.setMoney(zm.getMoney()-animal.getPrice())
		end
	end
	
@enduml
*/