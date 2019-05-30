package sequenceDiagram;

public class Sequence_Map {
	/**
	@startuml

	loop until the game ends
	HunterController->MapController : mapCont.chooseMap() //choice¿¡ ÀúÀå
	alt (choice==1) //Store
		MapController->StoreController : mapCont.visitStore()
	else (choice==2) //Forest
		MapController->ForestController : mapCont.visitForest()
	else (choice==3) //Zoo
		MapController->ZooController : mapCont.visitZoo()
	end
		
	@enduml
	*/
}
