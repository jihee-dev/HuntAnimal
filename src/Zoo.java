public class Zoo extends Map {
    private static Zoo instance;

   
    public static Zoo getInstance(){
        if(instance==null) {
        	instance=new Zoo();
        	instance.setName("µ¿¹°¿ø");
        }
    	return instance;
    }



}
