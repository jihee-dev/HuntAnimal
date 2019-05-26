package Ver3;
public class ZooManager {
    private int money;

    //������ �߰�
    public ZooManager(int m) {
        this.setMoney(m);
    }

    public void setMoney(int m) {
        this.money = m;
    }

    public int getMoney() {
        return money;
    }

    //����
    public int buy(Animal ani) {
        if (this.getMoney() < ani.getPrice()) {//ZooManager�� ���� ���� ���->sell �Ұ�
            System.out.println("ZooManager�� ���� �����ϴ�.�Ф� ������ �� �� �����.");
            return 0;
        } else {
            System.out.println(ani.getName() + "��/�� ������ �����ڿ��� �Ǹ��մϴ�.");
            this.money = this.money - ani.getPrice(); //���� ���� ��� ����
            System.out.printf("[������ ������]�� [%s]��(��) %d���� ��ɴϴ�.\n", ani.getName(), ani.getPrice());

            ani.setWhere(Zoo.getInstance()); //������ ��ɲ��� �������� ���������� �̵�

            System.out.printf("[%s]�� [%s]�� �����Ǿ����ϴ�.\n\n", ani.getName(), ani.getWhere().getName());
            //�������� �����ϱ�
            return ani.getPrice();
        }
    }

}
