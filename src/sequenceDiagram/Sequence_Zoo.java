package sequenceDiagram;

public class Sequence_Zoo {
	/**
	@startuml

	loop until the game ends
	MapController->ZooController : mapCont.visitZoo()
	MapController->HunterView : Message : "Hunter enters the Zoo"
	MapController->ZooView : zooView.showZooView() //Zoo ȭ�� ���
	ZooController->HunterController : zooCont.determineBuff() //���� or ����� ����
	alt (zooCoont.determineBuff==0) //�����
		ZooController->HunterView : hunterView.debuffedHunter() //����� �޴� ��ɲ� ���
		ZooController->HunterView : Message : "Hunter's Hp goes down"
	else (zooCoont.determineBuff==1) //����
		ZooController->HunterView : hunterView.buffedHunter() //���� �޴� ��ɲ� ���
		ZooController->HunterView : Message : "Hunter's Hp goes up"
	end
	ZooController->HunterModel : zooCont.changeHp() //��ɲ��� Hp ����
					
	@enduml
	*/
}
