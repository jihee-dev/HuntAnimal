public class Forest extends Map {
    private static Forest instance;


    public static Forest getInstance() {
        if (instance == null) {
            instance = new Forest();
            instance.setName("������");
        }
        return instance;
    }


}
