public abstract class Animal implements Huntable, Trickable {
	private String name;
    private Map where;
    private int price;
    private int catch_per;
    
    //super this Ȱ��
    public Animal() {}
    public Animal(String n) {
    	this.name=n;
    }
    //����Ȯ�� 1~100���� �����ϱ�
    public void setWhere(Zoo z){
    	System.out.println(this.name+" ��(��) �������� ��ϴ�.");
        this.where=z;
        z.ani.add(this);
    }
    public void setWhere(Forest f){
    	System.out.println(this.name+" ��(��) ���ӿ� ��ϴ�.");
        this.where=f;
        f.ani.add(this);
    }
    public void setWhere(Prison p){
    	System.out.println(this.name+" ��(��) ������ ��ϴ�.");
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

    public void ����ϱ�(){}

    public void ����θ���(){}
//if ����
    
    public void play(){
    	if(this.getWhere()instanceof Zoo) {
            this.����θ���();
       }else if(this.getWhere()instanceof Forest) {
            this.����ϱ�();
       }else {
    	   System.out.println("������ �����־ �ƹ��͵� �� �� �����ϴ�.");
       }
    }

    
    public void Ż���ϱ�(){
    	if(this.getWhere()instanceof Zoo) {
            this.getWhere().ani.remove(this);
            System.out.printf("Ż���Ͽ� �������� ����Ĩ�ϴ�!\n ���� ");
            this.setWhere(Forest.getInstance());
            this.getWhere().ani.add(this); 
    	} else {System.out.println("�̹� ���ӿ� �ֽ��ϴ�.");}
    }

  }