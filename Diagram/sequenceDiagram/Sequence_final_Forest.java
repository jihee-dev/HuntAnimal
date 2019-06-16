
public class Sequence_final_Forest {
	/**
	@startuml

	loop until the game ends
	Forest -> Forest : enter(int num)
	alt Hunt with Item
		Hunter -> Item : useItem(Item i)
		alt Use Trap
			Item -> Item : used()
			Item -> Item : setCount(int count)
		else Use Net
			Item -> Item : used()
			Item -> Hunter : setIncreRange(int range)
		else Use Gun
			Item -> Item : used()
			Item -> Hunter : setIncreRange(0)
		else Use Feed
			Item -> Item : used()
			Item -> HunterDog : healDog(HunterDog dog)
			HunterDog -> HunterDog : setHp(int hp)
		end
	end
	alt Animal is catched
		Forest -> Hunter : catchAni(Animal ani, Forest forest)
		alt catched Animal is a Mushroom
			Animal -> Hunter : heal(Hunter hunter)
		else catched Animal is not a Mushroom
			Hunter -> Hunter : prison.add(Prey ani)
		end
	end
					
	@enduml
	*/
}
