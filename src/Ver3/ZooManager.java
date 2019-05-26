package Ver3;
public class ZooManager {
    private int money;

    //생성자 추가
    public ZooManager(int m) {
        this.setMoney(m);
    }

    public void setMoney(int m) {
        this.money = m;
    }

    public int getMoney() {
        return money;
    }

    //수정
    public int buy(Animal ani) {
        if (this.getMoney() < ani.getPrice()) {//ZooManager의 돈이 없는 경우->sell 불가
            System.out.println("ZooManager가 돈이 없습니다.ㅠㅠ 동물을 살 수 없어요.");
            return 0;
        } else {
            System.out.println(ani.getName() + "을/를 동물원 관리자에게 판매합니다.");
            this.money = this.money - ani.getPrice(); //동물 구입 비용 지불
            System.out.printf("[동물원 관리자]가 [%s]을(를) %d원에 사옵니다.\n", ani.getName(), ani.getPrice());

            ani.setWhere(Zoo.getInstance()); //동물을 사냥꾼의 감옥에서 동물원으로 이동

            System.out.printf("[%s]가 [%s]에 배정되었습니다.\n\n", ani.getName(), ani.getWhere().getName());
            //동물가격 리턴하기
            return ani.getPrice();
        }
    }

}
