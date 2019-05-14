public abstract class Animal implements Huntable, Trickable {
	private String name;
    private Map where;
    private int price;
    private int catch_per;
    
    //super this 활용
    public Animal() {}
    public Animal(String n) {
    	this.name=n;
    }
    //잡힐확률 1~100으로 생각하기
    public void setWhere(Zoo z){
    	System.out.println(this.name+" 이(가) 동물원에 삽니다.");
        this.where=z;
        z.ani.add(this);
    }
    public void setWhere(Forest f){
    	System.out.println(this.name+" 이(가) 숲속에 삽니다.");
        this.where=f;
        f.ani.add(this);
    }
    public void setWhere(Prison p){
    	System.out.println(this.name+" 이(가) 감옥에 삽니다.");
        this.where=p;
        p.ani.add(this);
    }
    public Map getWhere(){return this.where;}

    public void setName(String n) {this.name=n;}
    public String getName() {return this.name;}
    
    public void setCatch_per(int c) {this.catch_per = c;}
    public int getCatch_per(){return this.catch_per;}
    
    public void setPrice(int price) { this.price = price;} 
    public int getPrice() {return this.price;}

    public void 사냥하기(){}

    public void 묘기부리기(){}
//if 수정
    
    public void play(){
    	if(this.getWhere()instanceof Zoo) {
            this.묘기부리기();
       }else if(this.getWhere()instanceof Forest) {
            this.사냥하기();
       }else {
    	   System.out.println("감옥에 갇혀있어서 아무것도 할 수 없습니다.");
       }
    }

    
    public void 탈출하기(){
    	if(this.getWhere()instanceof Zoo) {
            this.getWhere().ani.remove(this);
            System.out.printf("탈출하여 숲속으로 도망칩니다!\n 이제 ");
            this.setWhere(Forest.getInstance());
            this.getWhere().ani.add(this); 
    	} else {System.out.println("이미 숲속에 있습니다.");}
    }

  }