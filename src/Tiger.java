public class Tiger extends Animal {

    //Tiger �����ڿ� this.�߰�, setPrice 500,000������ 700,000������ ����, setCatch_per 3->30���� ����
    public Tiger(String name) {
        super(name);
        this.setWhere(Forest.getInstance());
        this.setPrice(7000000);
        this.setCatch_per(30);
    }

    public void ����ϱ�() {
        System.out.println(this.getName() + "��(��) �罿�� ������Դϴ�.");
    }

    public void ����θ���() {
        System.out.println(this.getName() + "��(��) �����ó�� ��ӻ�� �Ƚ��ϴ�!");
    }


}