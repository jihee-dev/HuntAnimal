public class Bird extends Animal {
	
	//Bird �����ڿ� this.�߰�
	//����Ȯ�� 5->50���� �ٲ�
	
    public Bird(String name){
    	super(name);
    	this.setWhere(Forest.getInstance());
    	this.setPrice(500000);
    	this.setCatch_per(50);
    }

    public void ����ϱ�(){
    	System.out.println(this.getName()+"��(��) ������ ������Դϴ�.");
    }

    public void ����θ���(){
    	System.out.println(this.getName()+"��(��) ���ۺ��� �����մϴ�!");
    }

   


}
