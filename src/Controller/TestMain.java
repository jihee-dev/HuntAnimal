package Controller;

import model.map.FileIO;

public class TestMain {
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

        /*if (fileIO.logIn("jihee2060", "5678")) System.out.println("Log in Success!");
        else System.out.println("Log in Fail!");*/

        fileIO.loadInfo();

        fileIO.join("wdagwh", "3333");
    }
}
