package sequenceDiagram;

public class Sequence_Store {
	/**
	@startuml

	loop until the game ends
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
				
	@enduml
	*/
}
