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

   //사냥개의 사냥하기는 사냥확률을 10% 더 높여준다.
    public void 사냥하기(Animal a){
    	//출력 메세지 수정
    	System.out.println("사냥꾼이 사냥개를 앞세워 " + a.getName() + "을/를 사냥을 시작합니다.");
    	//1~100까지의 난수설정
    	rand = (int) (Math.random() * 100+1);
    	if ((rand <= (a.getCatch_per()) + this.getCatch_per()) && (a.getWhere() instanceof Forest)) {
			System.out.println(a.getName() + " 사냥에 성공했습니다.");
			a.getWhere().ani.remove(a);
			a.setWhere(Prison.getInstance());
			
		}
    	
    	else {
    		System.out.println(a.getName() + "사냥에 실패했습니다.");
    	}
    }

}
