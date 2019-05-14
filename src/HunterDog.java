public class HunterDog extends Animal {
    private static HunterDog instance = null;
    private int rand;
    
   
    public static HunterDog getInstance(){
        if(instance == null) { 
        	instance = new HunterDog();
        	instance.setCatch_per(10);
        }
    	return instance;
    }

   //��ɰ��� ����ϱ�� ���Ȯ���� 10% �� �����ش�.
    public void ����ϱ�(Animal a){
    	//��� �޼��� ����
    	System.out.println("��ɲ��� ��ɰ��� �ռ��� " + a.getName() + "��/�� ����� �����մϴ�.");
    	//1~100������ ��������
    	rand = (int) (Math.random() * 100+1);
    	if ((rand <= (a.getCatch_per()) + this.getCatch_per()) && (a.getWhere() instanceof Forest)) {
			System.out.println(a.getName() + " ��ɿ� �����߽��ϴ�.");
			a.getWhere().ani.remove(a);
			a.setWhere(Prison.getInstance());
			
		}
    	
    	else {
    		System.out.println(a.getName() + "��ɿ� �����߽��ϴ�.");
    	}
    }

}
