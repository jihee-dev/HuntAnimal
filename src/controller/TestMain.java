package controller;

import model.active.Hunter;
import model.active.Prey;
import model.map.FileIO;
import model.map.Forest;

public class TestMain {
    public static Hunter hunter = Hunter.getInstance();
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

        /*if (fileIO.logIn("jihee2060", "5678")) System.out.println("Log in Success!");
        else System.out.println("Log in Fail!");*/

        fileIO.loadInfo();

        // fileIO.join("wdagwasdgasdgh", "3333");
        
        /*Forest forest1 = new Forest("forest1");
        forest1.getBackgroundImg().add(0, "");//

        Forest forest2 = new Forest("forest2");
        forest2.getBackgroundImg().add(0, "");//*/
    }
}
