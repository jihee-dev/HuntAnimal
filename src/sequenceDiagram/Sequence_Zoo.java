package sequenceDiagram;

public class Sequence_Zoo {
	/**
	@startuml

	loop until the game ends
	MapController->ZooController : mapCont.visitZoo()
	MapController->HunterView : Message : "Hunter enters the Zoo"
	MapController->ZooView : zooView.showZooView() //Zoo 화면 출력
	ZooController->HunterController : zooCont.determineBuff() //버프 or 디버프 결정
	alt (zooCoont.determineBuff==0) //디버프
		ZooController->HunterView : hunterView.debuffedHunter() //디버프 받는 사냥꾼 모습
		ZooController->HunterView : Message : "Hunter's Hp goes down"
	else (zooCoont.determineBuff==1) //버프
		ZooController->HunterView : hunterView.buffedHunter() //버프 받는 사냥꾼 모습
		ZooController->HunterView : Message : "Hunter's Hp goes up"
	end
	ZooController->HunterModel : zooCont.changeHp() //사냥꾼의 Hp 수정
					
	@enduml
	*/
}
