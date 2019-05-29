package Ver3;

public class SequenceDiagram {
	/**
	@startuml

	loop until the game ends
	HunterController->MapController : mapCont.chooseMap() //choice�� ����
		alt (choice==1) //Store
			MapController->StoreController : mapCont.visitStore()
			MapController->HunterView : Message : "Hunter enters the Store"
			MapController->StoreView : storeView.showStoreView() //Store ȭ�� ���
			StoreController->StoreView : storeView.showMenu() //������ or ���� �ŷ� ���� â
			StoreController->HunterController : storeCont.chooseMenu() //ch�� ����
			alt (ch==0) //sell animal
				HunterController->HunterView : hunterView.showPrison() //prison�� ���� ���̱�
				HunterController->HunterController : hunterCont.chooseAnimal() //�Ǹ��� ���� ����
				HunterController->StoreController : hunterCont.sellAnimal(ani) //������ ���� �Ǹ�
				HunterController->HunterModel : hunterModel.deleteAnimal(ani) //prison���� �Ǹ��� ���� ����
				StoreController->HunterModel : hunterModel.setAsset(getAsset()+ani.price) //�� ��� ����
				StoreController->HunterModel : hunterModel.setMoney(getMoney()+ani.price) //���� �ڻ� ����
				StoreController->HunterController: storeCont.buyAni(ani) //������ ���� ����
				HunterController->HunterView : hunterView.showPrison() //prison�� ���� ���̱�
				StoreController->HunterView : Message : "Hunter sells an animal"
			else (ch==1) //buy item
				StoreController->StoreView : storeView.showItems() //������ ������ ���̱�
				HunterController->HunterController : hunterCont.chooseItem() //������ ������ ����
				alt (hunter.money>=it.price)
					StoreController->HunterController : storeCont.sellItem(it) //������ ������ �Ǹ�
					HunterController->StoreController : hunterCont.buyItem(it) //������ ������ ����
					StoreController->HunterModel : hunterModel.addItem(it) //item[] �߰�
					StoreController->HunterModel : hunterModel.setAsset(getAsset()-it.price) //�� ��� ����
					StoreController->HunterModel : hunterModel.setMoney(getMoney()) //���� �ڻ� ����
					StoreController->HunterView : hunterView.showItems() //��ɲ��� ������ ���̱�
					StoreController->HunterView : Message : "Hunter buys item"
				else (hunter.money<it.price)
					StoreController->HunterView : Message : "Hunter can't buy item"
				end
			end
		else (choice==2) //Forest
			MapController->ForestController : mapCont.visitForest()
			MapController->HunterView : Message : "Hunter enters the Forest"
			MapController->ForestView : forestView.showForestView() //Forest ȭ�� ���
			ForestController->ForestModel : forestModel.setPrey(animal) //�������� ��ɰ� ��ġ
			ForestController->ForestView : forestView.showPrey() //��ɰ� ���
			ForestController->PreyController : preyCont.move(speed) //��ɰ� �̵�
			PreyController->PreyModel : preyModel.changeLocation(location) //��ɰ��� ��ġ ����
			ForestController->ForestView : forestView.showPrey() //��ɰ� ���
			ForestController->HunterDogView : hunterDogView.showHunterDog() //��ɰ� ���
			ForestController->HunterDogController : hunterDogCont.move(speed) //��ɰ� �̵�
			HunterDogController->HunterDogModel : hunterDogModel.changeLocation(location) //��ɰ��� ��ġ ����
			ForestController->HunterDogView : hunterDogView.showHunterDog() //��ɰ� ���
			ForestController->MushroomView : mushroomView.showMushroom() //���� ���
			ForestController->MushroomController : mushroomCont.move(speed) //���� �̵�
			MushroomController->MushroomModel : mushroomModel.changeLocation(location) //������ ��ġ ����
			ForestController->MushroomView : mushroomView.showMushroom() //���� ���
			ForestController->HunterController : hunterCont.chooseItem() //ch�� ����
			alt (ch==1) //Trap
				HunterController->TrapController : hunterCont.use(trap) //Ʈ�� ��ġ
				HunterController->TrapView : trapView.showItemUsed() //��ġ�� Ʈ�� ���
				TrapController->PreyController : trapCont.attack(ani) //������ ���� �ɷȴ°�
				alt (result==1) //������ ���� �ɸ�
					TrapController->PreyView : preyView.showAnimalCaught(ani) //���� �ɸ� ���� ���
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //��ɲ��� ü�� ����
					ForestController->ForestModel : forestModel.deletPrey(ani) //���� ���� ����
					ForestController->ForestView : forestView.showPrey() //��ɰ� ���
					ForestController->TrapView : trapView.deleteTrap() //ȭ�鿡�� Ʈ�� ����
					TrapController->TrapModel : trapModel.usedItem() //item[]���� Ʈ�� ����
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison�� ���� �߰�
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice()-trapModel.getPrice()) //�� �ڻ� ����
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //���� �ڻ� ����
					ForestController->HunterView : Message : "Animal is caught in a trap"
				end
			else (ch==2) //Net
				HunterController->NetController : hunterCont.use(net) //�׹� ����
				HunterController->NetView : netView.showItemUsed() //������ �׹� ���
				NetController->PreyController : netCont.attack(ani) //������ �׹��� �ɷȴ°�
				alt (result==1) //������ �׹��� �ɸ�
					NetController->PreyView : preyView.showAnimalCaught(ani) //�׹��� �ɸ� ���� ���
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //��ɲ��� ü�� ����
					ForestController->ForestModel : forestModel.deletPrey(ani) //���� ���� ����
					ForestController->ForestView : forestView.showPrey() //��ɰ� ���
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison�� ���� �߰�
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //�� �ڻ� ����
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //���� �ڻ� ����
					ForestController->HunterView : Message : "Animal is caught in a net"
				end
			else (ch==3) //Gun
				HunterController->GunController : hunterCont.use(gun) //������ ����
				HunterController->GunView : gunView.showItemUsed() //������ ������ ���
				GunController->PreyController : gunCont.attack(ani) //������ �����ѿ� �¾Ҵ°�
				alt (result==1) //������ �����ѿ� ����
					GunController->PreyView : preyView.showAnimalCaught(ani) //�����ѿ� ���� ���� ���
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //��ɲ��� ü�� ����
					ForestController->ForestModel : forestModel.deletPrey(ani) //���� ���� ����
					ForestController->ForestView : forestView.showPrey() //��ɰ� ���
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison�� ���� �߰�
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //�� �ڻ� ����
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //���� �ڻ� ����
					ForestController->HunterView : Message : "Animal got shot by a gun"
				else (result==0) //�������� ������
					ForestController->HunterView : Message : "Shot went astray"
				end
			else (ch==4) //Feed
				HunterController->FeedController : feedCont.feed(f) //��ɰ����� ��� �ֱ�
				FeedController->FeedModel : feedModel.usedItem() //item[]���� ��� ����
				HuntingDogController->HuntingDogView : huntingDogView.showHuntingDogHeal //ȸ���� ��ɰ� ���
				HunterController->HuntingDogModel : huntingDogModel.healHp(huntingDog) //��ɰ��� ü�� ����
				HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()-feedModel.getPrice) //�� �ڻ� ����
				ForestController->HunterView : Message : "HunterDog's Hp goes up"
			else //No Item
				HunterController->PreyController : attack(ani) //������ ���� ��ɿ� �����ߴ°�
				alt (result==1) //��� ����
					HunterController->PreyView : preyView.showAnimalCaught(ani) //��ɲۿ��� ���� ���� ���
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //��ɲ��� ü�� ����
					ForestController->ForestModel : forestModel.deletPrey(ani) //���� ���� ����
					ForestController->ForestView : forestView.showPrey() //��ɰ� ���
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison�� ���� �߰�
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //�� �ڻ� ����
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //���� �ڻ� ����
					ForestController->HunterView : Message : "Hunter succeeded in hunting"
				end
			end
			HuntingDogController->PreyController : huntingDogCont.attack(ani) //��ɰ��� ��ɿ� �����ߴ°�
			alt (result==1) //��ɰ��� ��� ����
				HuntingDogController->PreyView : preyView.showAnimalCaught(ani) //��ɰ����� ���� ���� ���
				ForestController->ForestModel : forestModel.deletPrey(ani) //���� ���� ����
				ForestController->ForestView : forestView.showPrey() //��ɰ� ���
				HunterController->HunterModel : hunterModel.addAnimal(ani) //prison�� ���� �߰�
				HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //�� �ڻ� ����
				HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //���� �ڻ� ����
				HunterDogController->HunterDogModel : hunterDogModel.setNum(hunterDogModel.getNum()+1) //��ɰ��� ���� ����
				HunterDogController->HunterDogModel : hunterDogModel.setSpeed(hunterDogModel.getSpeed()-5) //��ɰ��� ü�� ����
				alt (huntingDog.num>=5) //������ 5 �̻��� ���
					HunterDogController->HunterDogModel : hunterDogModel.setLevel(hunterDogModel.getLevel()+1) //��ɰ��� ���õ� ����
					HunterDogController->HunterDogModel : hunterDogModel.setSpeed(hunterDogModel.getSpeed()+10) //��ɰ��� ü�� ����
					HunterDogController->HunterDogModel : hunterDogModel.setNum(0) //��ɰ��� ���� ����
					ForestController->HunterView : Message : "HunterDog's level up"
				end
				ForestController->HunterView : Message : "HunterDog succeeded in hunting"
			end
			HunterController->MushroomController : hunterCont.meetMushroom() //������ �Ծ��°�?
			alt (result==1) //Mushroom ���� ���
				MushroomController->MushroomView : mushroomView.deleteMushroom() //ȭ�鿡�� ���� ����
				MushroomController->HunterModel : hunterModel.heal() //��ɲ��� Hp ����
			end
		else (choice==3) //Zoo
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
		end
		
	@enduml
	*/
}