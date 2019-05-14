public class Tiger extends Animal {

    //Tiger 생성자에 this.추가, setPrice 500,000원에서 700,000원으로 수정, setCatch_per 3->30으로 수정
    public Tiger(String name) {
        super(name);
        this.setWhere(Forest.getInstance());
        this.setPrice(7000000);
        this.setCatch_per(30);
    }

    public void 사냥하기() {
        System.out.println(this.getName() + "이(가) 사슴을 사냥중입니다.");
    }

    public void 묘기부리기() {
        System.out.println(this.getName() + "이(가) 고양이처럼 사뿐사뿐 걷습니다!");
    }


}