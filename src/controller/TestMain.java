package controller;

import model.map.FileIO;

public class TestMain {
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

        fileIO.loadInfo();

        /* fileIO.join("plat2", "password");
        fileIO.join("jihee2060", "pass123");
        fileIO.join("test1", "1111"); */

        fileIO.join("57,*&", "3579");

        // fileIO.logIn("plat2", "pass");
        // fileIO.logIn("plat2", "password");

        // fileIO.sortRank();
    }
}
