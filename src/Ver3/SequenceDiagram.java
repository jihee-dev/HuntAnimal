package Ver3;

public class SequenceDiagram {

}

/**
@startuml

loop until the game ends
Hunter->Map : chooseMap()
	alt (choice==1) //Store
		Map->Store : visitStore()
		Map->Hunter : Message : "Hunter enters the Store"
		alt (chooseMenu()==0) //sell animal
			Hunter->Store : showPrison
			Hunter->Store : sellAni(ani)
			Hunter->Hunter : prison.remove(ani)
			Store->Hunter : setAsset(getAsset()+ani.price)
			Store->Hunter : setMoney(getMoney()+ani.price)
			Store->Hunter : buyAni(ani)
			Store->Hunter : Message : "Hunter sells an animal"
		else (chooseMenu()==1) //buy item
			alt (hunter.money>=it.price)
				Store->Hunter : showItems()
				Hunter->Store : buyItem(it)
				Hunter->Hunter : item.add(it)
				Hunter->Store : setAsset(getAsset()-it.price)
				Hunter->Store : setMoney(getMoney()-it.price)
				Store->Hunter : sellItem(it)
				Store->Hunter : Message : "Hunter buys item"
			else (hunter.money<it.price)
				Store->Hunter : Message : "Hunter can't buy item"
			end
		end
	else (choice==2) //Forest
		Map->Forest : visitForest()
		Map->Hunter : Message : "Hunter enters the Forest"
		Forest->Forest : setPrey(animal)
		Forest->Prey : prey.move(speed)
		Forest->HunterDog : move(speed)
		Forest->Forest : showMushroom()
		Forest->Mushroom : mushroom.move(speed)
		alt (chooseItem()==1) //Trap
			Hunter->Item : use(ani,trap)
			Hunter->Item : setLocation(location)
			alt (Item->Prey : attack(ani)==1) //사냥 성공
				Hunter->Hunter : setSpeed(getSpeed()-5)
				Hunter->Forest : deletPrey(ani)
				Item->Item : usedItem()
				Hunter->Hunter : prison.add(ani.price)
				Hunter->Hunter : setAsset(getAsset()+ani.price)
				Forest->Hunter : Message : "Animal is caught in a trap"
			end
		else (chooseItem()==2) //Net
			Hunter->Item : use(ani,trap)
			alt (Item->Prey : attack(ani)==1) //사냥 성공
				Hunter->Hunter : setSpeed(getSpeed()-5)
				Hunter->Hunter : setSpeed(getSpeed()-5)
				Hunter->Forest : deletPrey(ani)
				Hunter->Hunter : prison.add(ani.price)
				Hunter->Hunter : setAsset(getAsset()+ani.price)
				Forest->Hunter : Message : "Animal is caught in a net"
			end
		else (chooseItem()==3) //Gun
			Hunter->Item : use(ani,gun)
			alt (Item->Prey : attack(ani)==1) //사냥 성공
				Hunter->Hunter : setSpeed(getSpeed()-5)
				Hunter->Forest : deletPrey(ani)
				Hunter->Hunter : prison.add(ani.price)
				Hunter->Hunter : setAsset(getAsset()+ani.price)
				Forest->Hunter : Message : "Animal got shot by a gun"
			else (Item->Prey : attack(ani)==0) //사냥 실패
				Forest->Hunter : Message : "Shot went astray"
			end
		else (chooseItem()==4) //Feed
			Hunter->Item : feed(f)
			Item->Item : usedItem()
			Item->HunterDog : healHp(dog)
			Forest->Hunter : Message : "HunterDog's Hp goes up"
		else //No Item
			alt (Item->Prey : attack(ani)==1) //사냥 성공
				Hunter->Hunter : setSpeed(getSpeed()-5)
				Hunter->Forest : deletPrey(ani)
				Hunter->Hunter : prison.add(ani.price)
				Hunter->Hunter : setAsset(getAsset()+ani.price)
				Forest->Hunter : Message : "Hunter succeeded in hunting"
			end
		end
		alt (HunterDog->Prey : attack(ani)==1) //사냥개가 사냥 성공
			HunterDog->HunterDog : setNum(getNum()+1)
			HunterDog->HunterDog : setSpeed(getSpeed()-5)
			HunterDog->Forest : deletPrey(ani)
			HunterDog->Hunter : prison.add(ani.price)
			HunterDog->Hunter : addAssets(ani)
			alt (dog.num>=5)
				HunterDog->HunterDog : setLevel(getLevel()+1)
				HunterDog->HunterDog : setSpeed(getSpeed()+10)
				HunterDog->HunterDog : setNum(0)
				Forest->Hunter : Message : "HunterDog's level up"
			end
			Forest->Hunter : Message : "HunterDog succeeded in hunting"
		end
		alt (Hunter->Mushroom : meetMushroom()==1) //Mushroom 먹은 경우
			Mushroom->Hunter : heal(hunter)
			
		end
	else (choice==3) //Zoo
		Map->Zoo : visitZoo()
		Zoo->Hunter : changeHp()
		Map->Hunter : Message : "Hunter enters the Zoo"
	end
	
@enduml
*/