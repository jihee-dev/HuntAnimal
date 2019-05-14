public class Main {
    //Main에 메소드 추가
    public static void print_money(Hunter hunter, ZooManager manager) {

        System.out.println("\n동물원 매니저의 현재 잔액: " + manager.getMoney());
        System.out.println("사냥꾼의 현재 잔액: " + hunter.getMoney() + "\n");


    }

    public static void main(String[] args) {
        Zoo zoo = Zoo.getInstance(); //싱글톤으로 동물원 생성
        Forest forest = Forest.getInstance(); //싱글톤으로 숲속 생성
        Prison prison = Prison.getInstance(); //싱글톤으로 사냥꾼의 우리 생성
        Tiger tiger = new Tiger("호랑이"); //호랑이 생성
        Bird bird = new Bird("새"); //새 생성
        Hunter hunter = new Hunter(); //사냥꾼 생성
        HunterDog dog = HunterDog.getInstance();

        //dog.setCatch_per(1); // 사냥개를 사용하면 사냥에 성공할 확률이 +10----->HunterDog에서 default로 설정함.
        //이름추가


        ZooManager manager = new ZooManager(10000000); //동물원 매니저 생성
        //manager.setMoney(10000000);

        //forest.ani.add(tiger);-------------->삭제 tiger setWhere할 때 add 해줬으니 넣으면 중복으로 들어감.
        //forest.ani.add(bird);   -------------->삭제


        print_money(hunter, manager);

        System.out.println("사냥꾼이 새 사냥에 성공할 때까지 혼자서 사냥을 합니다.");
        while (bird.getWhere() instanceof Forest) {
            hunter.사냥하기(bird); // 사냥개 없이 새 사냥
        }
        System.out.println();

        System.out.println("사냥꾼이 사냥개를 데리고 사냥에 성공할 때까지 호랑이 사냥을 합니다.");
        while (tiger.getWhere() instanceof Forest) {
            dog.사냥하기(tiger); // 사냥개 사용하여 사냥
        }
        System.out.println();

        prison.print_array();
        System.out.println("사냥꾼이 동물을 판매합니다!!");
        hunter.sell(bird, manager, prison);
        System.out.println();

        hunter.sell(tiger, manager, prison);
        System.out.println();

        print_money(hunter, manager);

        //추가
        zoo.print_array();
    }

}