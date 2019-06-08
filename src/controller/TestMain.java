package controller;

import model.active.Hunter;
import model.map.FileIO;

public class TestMain {
    public static Hunter hunter = Hunter.getInstance();
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

        /*if (fileIO.logIn("jihee2060", "5678")) System.out.println("Log in Success!");
        else System.out.println("Log in Fail!");*/

        fileIO.loadInfo();

        fileIO.join("wdagwasdgasdgh", "3333");
    }
}
