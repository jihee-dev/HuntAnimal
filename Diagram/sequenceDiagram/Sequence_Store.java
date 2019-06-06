package sequenceDiagram;

public class Sequence_Store {
	/**
	@startuml

	loop until the game ends
	MapController->StoreController : mapCont.visitStore()
	MapController->HunterView : Message : "Hunter enters the Store"
	MapController->StoreView : storeView.showStoreView() //Store 화면 출력
	StoreController->StoreView : storeView.showMenu() //아이템 or 동물 거래 선택 창
	StoreController->HunterController : storeCont.chooseMenu() //ch에 저장
	alt (ch==0) //sell animal
		HunterController->HunterView : hunterView.showPrison() //prison의 동물 보이기
		HunterController->HunterController : hunterCont.chooseAnimal() //판매할 동물 선택
		HunterController->StoreController : hunterCont.sellAnimal(ani) //선택한 동물 판매
		HunterController->HunterModel : hunterModel.deleteAnimal(ani) //prison에서 판매한 동물 삭제
		StoreController->HunterModel : hunterModel.setAsset(getAsset()+ani.price) //총 재산 변경
		StoreController->HunterModel : hunterModel.setMoney(getMoney()+ani.price) //유동 자산 변경
		StoreController->HunterController: storeCont.buyAni(ani) //선택한 동물 구매
		HunterController->HunterView : hunterView.showPrison() //prison의 동물 보이기
		StoreController->HunterView : Message : "Hunter sells an animal"
	else (ch==1) //buy item
		StoreController->StoreView : storeView.showItems() //상점의 아이템 보이기
		HunterController->HunterController : hunterCont.chooseItem() //구매할 아이템 선택
		alt (hunter.money>=it.price)
			StoreController->HunterController : storeCont.sellItem(it) //선택한 아이템 판매
			HunterController->StoreController : hunterCont.buyItem(it) //선택한 아이템 구매
			StoreController->HunterModel : hunterModel.addItem(it) //item[] 추가
			StoreController->HunterModel : hunterModel.setAsset(getAsset()-it.price) //총 재산 변경
			StoreController->HunterModel : hunterModel.setMoney(getMoney()) //유동 자산 변경
			StoreController->HunterView : hunterView.showItems() //사냥꾼의 아이템 보이기
			StoreController->HunterView : Message : "Hunter buys item"
		else (hunter.money<it.price)
			StoreController->HunterView : Message : "Hunter can't buy item"
		end
	end
				
	@enduml
	*/
}
