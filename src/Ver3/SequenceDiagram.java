package Ver3;

public class SequenceDiagram {
	/**
	@startuml

	loop until the game ends
	HunterController->MapController : mapCont.chooseMap() //choice에 저장
		alt (choice==1) //Store
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
		else (choice==2) //Forest
			MapController->ForestController : mapCont.visitForest()
			MapController->HunterView : Message : "Hunter enters the Forest"
			MapController->ForestView : forestView.showForestView() //Forest 화면 출력
			ForestController->ForestModel : forestModel.setPrey(animal) //랜덤으로 사냥감 배치
			ForestController->ForestView : forestView.showPrey() //사냥감 출력
			ForestController->PreyController : preyCont.move(speed) //사냥감 이동
			PreyController->PreyModel : preyModel.changeLocation(location) //사냥감의 위치 변경
			ForestController->ForestView : forestView.showPrey() //사냥감 출력
			ForestController->HunterDogView : hunterDogView.showHunterDog() //사냥개 출력
			ForestController->HunterDogController : hunterDogCont.move(speed) //사냥개 이동
			HunterDogController->HunterDogModel : hunterDogModel.changeLocation(location) //사냥개의 위치 변경
			ForestController->HunterDogView : hunterDogView.showHunterDog() //사냥개 출력
			ForestController->MushroomView : mushroomView.showMushroom() //버섯 출력
			ForestController->MushroomController : mushroomCont.move(speed) //버섯 이동
			MushroomController->MushroomModel : mushroomModel.changeLocation(location) //버섯의 위치 변경
			ForestController->MushroomView : mushroomView.showMushroom() //버섯 출력
			ForestController->HunterController : hunterCont.chooseItem() //ch에 저장
			alt (ch==1) //Trap
				HunterController->TrapController : hunterCont.use(trap) //트랩 설치
				HunterController->TrapView : trapView.showItemUsed() //설치된 트랩 출력
				TrapController->PreyController : trapCont.attack(ani) //동물이 덫에 걸렸는가
				alt (result==1) //동물이 덫에 걸림
					TrapController->PreyView : preyView.showAnimalCaught(ani) //덫에 걸린 동물 출력
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //사냥꾼의 체력 감소
					ForestController->ForestModel : forestModel.deletPrey(ani) //잡힌 동물 삭제
					ForestController->ForestView : forestView.showPrey() //사냥감 출력
					ForestController->TrapView : trapView.deleteTrap() //화면에서 트랩 삭제
					TrapController->TrapModel : trapModel.usedItem() //item[]에서 트랩 삭제
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison에 동물 추가
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice()-trapModel.getPrice()) //총 자산 변경
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //유동 자산 변경
					ForestController->HunterView : Message : "Animal is caught in a trap"
				end
			else (ch==2) //Net
				HunterController->NetController : hunterCont.use(net) //그물 장착
				HunterController->NetView : netView.showItemUsed() //장착한 그물 출력
				NetController->PreyController : netCont.attack(ani) //동물이 그물에 걸렸는가
				alt (result==1) //동물이 그물에 걸림
					NetController->PreyView : preyView.showAnimalCaught(ani) //그물에 걸린 동물 출력
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //사냥꾼의 체력 감소
					ForestController->ForestModel : forestModel.deletPrey(ani) //잡힌 동물 삭제
					ForestController->ForestView : forestView.showPrey() //사냥감 출력
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison에 동물 추가
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //총 자산 변경
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //유동 자산 변경
					ForestController->HunterView : Message : "Animal is caught in a net"
				end
			else (ch==3) //Gun
				HunterController->GunController : hunterCont.use(gun) //마취총 장착
				HunterController->GunView : gunView.showItemUsed() //장착한 마취총 출력
				GunController->PreyController : gunCont.attack(ani) //동물이 마취총에 맞았는가
				alt (result==1) //동물이 마취총에 맞음
					GunController->PreyView : preyView.showAnimalCaught(ani) //마취총에 맞은 동물 출력
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //사냥꾼의 체력 감소
					ForestController->ForestModel : forestModel.deletPrey(ani) //잡힌 동물 삭제
					ForestController->ForestView : forestView.showPrey() //사냥감 출력
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison에 동물 추가
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //총 자산 변경
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //유동 자산 변경
					ForestController->HunterView : Message : "Animal got shot by a gun"
				else (result==0) //마취총이 빗나감
					ForestController->HunterView : Message : "Shot went astray"
				end
			else (ch==4) //Feed
				HunterController->FeedController : feedCont.feed(f) //사냥개에게 사료 주기
				FeedController->FeedModel : feedModel.usedItem() //item[]에서 사료 삭제
				HuntingDogController->HuntingDogView : huntingDogView.showHuntingDogHeal //회복된 사냥개 출력
				HunterController->HuntingDogModel : huntingDogModel.healHp(huntingDog) //사냥개의 체력 변경
				HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()-feedModel.getPrice) //총 자산 변경
				ForestController->HunterView : Message : "HunterDog's Hp goes up"
			else //No Item
				HunterController->PreyController : attack(ani) //아이템 없이 사냥에 성공했는가
				alt (result==1) //사냥 성공
					HunterController->PreyView : preyView.showAnimalCaught(ani) //사냥꾼에게 잡힌 동물 출력
					HunterController->HunterModel : hunterModel.setSpeed(getSpeed()-5) //사냥꾼의 체력 감소
					ForestController->ForestModel : forestModel.deletPrey(ani) //잡힌 동물 삭제
					ForestController->ForestView : forestView.showPrey() //사냥감 출력
					HunterController->HunterModel : hunterModel.addAnimal(ani) //prison에 동물 추가
					HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //총 자산 변경
					HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //유동 자산 변경
					ForestController->HunterView : Message : "Hunter succeeded in hunting"
				end
			end
			HuntingDogController->PreyController : huntingDogCont.attack(ani) //사냥개가 사냥에 성공했는가
			alt (result==1) //사냥개가 사냥 성공
				HuntingDogController->PreyView : preyView.showAnimalCaught(ani) //사냥개에게 잡힌 동물 출력
				ForestController->ForestModel : forestModel.deletPrey(ani) //잡힌 동물 삭제
				ForestController->ForestView : forestView.showPrey() //사냥감 출력
				HunterController->HunterModel : hunterModel.addAnimal(ani) //prison에 동물 추가
				HunterController->HunterModel : hunterModel.setAsset(hunterModel.getAsset()+animalModel.getPrice) //총 자산 변경
				HunterController->HunterModel : hunterModel.setMoney(hunterModel.getMoney()+animalModel.getPrice) //유동 자산 변경
				HunterDogController->HunterDogModel : hunterDogModel.setNum(hunterDogModel.getNum()+1) //사냥개의 실적 변경
				HunterDogController->HunterDogModel : hunterDogModel.setSpeed(hunterDogModel.getSpeed()-5) //사냥개의 체력 변경
				alt (huntingDog.num>=5) //실적이 5 이상인 경우
					HunterDogController->HunterDogModel : hunterDogModel.setLevel(hunterDogModel.getLevel()+1) //사냥개의 숙련도 변경
					HunterDogController->HunterDogModel : hunterDogModel.setSpeed(hunterDogModel.getSpeed()+10) //사냥개의 체력 변경
					HunterDogController->HunterDogModel : hunterDogModel.setNum(0) //사냥개의 실적 변경
					ForestController->HunterView : Message : "HunterDog's level up"
				end
				ForestController->HunterView : Message : "HunterDog succeeded in hunting"
			end
			HunterController->MushroomController : hunterCont.meetMushroom() //버섯을 먹었는가?
			alt (result==1) //Mushroom 먹은 경우
				MushroomController->MushroomView : mushroomView.deleteMushroom() //화면에서 버섯 삭제
				MushroomController->HunterModel : hunterModel.heal() //사냥꾼의 Hp 변경
			end
		else (choice==3) //Zoo
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
		end
		
	@enduml
	*/
}