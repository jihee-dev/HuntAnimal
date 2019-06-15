package controller;

import model.map.FileIO;

public class TestMain {
    // public static Hunter hunter = Hunter.getInstance();
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

        /*if (fileIO.logIn("jihee2060", "5678")) System.out.println("Log in Success!");
        else System.out.println("Log in Fail!");*/

        fileIO.loadInfo();

        /*fileIO.join("plat2", "password");
        fileIO.join("jihee2060", "pass123");
        fileIO.join("test1", "1111");*/

        fileIO.logIn("plat2", "pass");
        fileIO.logIn("plat2", "password");

        fileIO.sortRank();

        /*Forest forest1 = new Forest("forest1");
        forest1.getBackgroundImg().add(0, "");//

        Forest forest2 = new Forest("forest2");
        forest2.getBackgroundImg().add(0, "");//*/
    }
}
