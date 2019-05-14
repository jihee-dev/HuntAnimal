public class Bird extends Animal {
	
	//Bird 생성자에 this.추가
	//잡힐확률 5->50으로 바꿈
	
    public Bird(String name){
    	super(name);
    	this.setWhere(Forest.getInstance());
    	this.setPrice(500000);
    	this.setCatch_per(50);
    }

    public void 사냥하기(){
    	System.out.println(this.getName()+"이(가) 벌레를 사냥중입니다.");
    }

    public void 묘기부리기(){
    	System.out.println(this.getName()+"이(가) 빙글빙글 비행합니다!");
    }

   


}
