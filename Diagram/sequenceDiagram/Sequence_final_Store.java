
public class Sequence_final_Store {
	/**
	@startuml

	loop until the game ends
	alt Sell Animal
		Hunter -> Store : sellAni(String name)
		Hunter -> Hunter : checkAni(String name)
		Hunter -> Animal : prison.remove(Animal ani)
		Hunter -> Hunter : setMoney(int money)
		Hunter -> Animal : checkAniNum()
	else Buy Item
		Store -> Hunter : checkMoney(Hunter hunter, Item it)
		Hunter -> Store : buyItem(Item it)
		Item -> Item : setCount(int count)
		Hunter -> Hunter : setMoney(int money)
	end
					
	@enduml
	*/
}
