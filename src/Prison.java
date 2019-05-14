public class Prison extends Map {
    private static Prison instance;


    public static Prison getInstance(){
    	 if(instance==null) {
         	instance=new Prison();
         	instance.setName("°¨¿Á");
         }
        return instance;
    }

}
