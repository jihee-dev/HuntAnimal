package Ver3;
public class Hunter implements Huntable {
    private int money;
    private int rand;

    //money-> this.money�� ����
    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //�޼��� ����
    public void ����ϱ�() {
        System.out.println("� ������ ������� �����ؼ� ����� �Ұ����մϴ�!");
    }

    public void ����ϱ�(Animal a) {
        System.out.println("��ɲ��� " + a.getName() + "��/�� ����� �����մϴ�.");
        //1~100������ ��������
        rand = (int) (Math.random() * 100) + 1;
        if ((rand <= a.getCatch_per()) && (a.getWhere() instanceof Forest)) {
            System.out.println(a.getName() + " ��ɿ� �����߽��ϴ�.");
            a.getWhere().ani.remove(a);
            a.setWhere(Prison.getInstance());

        } else {
            System.out.println(a.getName() + "��ɿ� �����߽��ϴ�.");
        }
    }

    //��ɰ� �����ؼ� ����ϴ°� HunterDog�� �ű�.

    public void sell(Animal ani, ZooManager zm, Prison p) {
        if (p.ani.isEmpty()) {//������ �ִ� ������ ���� ���->sell �Ұ�
            System.out.println("������ �ִ� ������ �����ϴ�");
        } else {
            //�ڵ��ߺ� �����ϰ� ZooManager buy����
            ani.getWhere().ani.remove(ani);
            this.money += zm.buy(ani);
        }
    }

    //print cage Map���� �̵�


}