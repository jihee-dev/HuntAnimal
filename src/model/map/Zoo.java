package model.map;
import model.active.Hunter;

public class Zoo extends Map {
    public static Zoo instance;

    public boolean changeHunterSpeed(Hunter hunter){
        return false;
    }

    private Zoo(){
    }

    public static Zoo getInstance(){
        return null;
    }

}
