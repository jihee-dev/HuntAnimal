public class Main {
    //Main�� �޼ҵ� �߰�
    public static void print_money(Hunter hunter, ZooManager manager) {

        System.out.println("\n������ �Ŵ����� ���� �ܾ�: " + manager.getMoney());
        System.out.println("��ɲ��� ���� �ܾ�: " + hunter.getMoney() + "\n");


    }

    public static void main(String[] args) {
        Zoo zoo = Zoo.getInstance(); //�̱������� ������ ����
        Forest forest = Forest.getInstance(); //�̱������� ���� ����
        Prison prison = Prison.getInstance(); //�̱������� ��ɲ��� �츮 ����
        Tiger tiger = new Tiger("ȣ����"); //ȣ���� ����
        Bird bird = new Bird("��"); //�� ����
        Hunter hunter = new Hunter(); //��ɲ� ����
        HunterDog dog = HunterDog.getInstance();

        //dog.setCatch_per(1); // ��ɰ��� ����ϸ� ��ɿ� ������ Ȯ���� +10----->HunterDog���� default�� ������.
        //�̸��߰�


        ZooManager manager = new ZooManager(10000000); //������ �Ŵ��� ����
        //manager.setMoney(10000000);

        //forest.ani.add(tiger);-------------->���� tiger setWhere�� �� add �������� ������ �ߺ����� ��.
        //forest.ani.add(bird);   -------------->����


        print_money(hunter, manager);

        System.out.println("��ɲ��� �� ��ɿ� ������ ������ ȥ�ڼ� ����� �մϴ�.");
        while (bird.getWhere() instanceof Forest) {
            hunter.����ϱ�(bird); // ��ɰ� ���� �� ���
        }
        System.out.println();

        System.out.println("��ɲ��� ��ɰ��� ������ ��ɿ� ������ ������ ȣ���� ����� �մϴ�.");
        while (tiger.getWhere() instanceof Forest) {
            dog.����ϱ�(tiger); // ��ɰ� ����Ͽ� ���
        }
        System.out.println();

        prison.print_array();
        System.out.println("��ɲ��� ������ �Ǹ��մϴ�!!");
        hunter.sell(bird, manager, prison);
        System.out.println();

        hunter.sell(tiger, manager, prison);
        System.out.println();

        print_money(hunter, manager);

        //�߰�
        zoo.print_array();
    }

}