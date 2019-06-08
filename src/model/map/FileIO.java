package model.map;

import java.io.*;
import java.util.ArrayList;

import model.active.Hunter;

public class FileIO {
    private ArrayList<User> info;

    public ArrayList<User> loadInfo() {
        info = new ArrayList<User>();
        try {
            String fileName = "./resourceFolder/UserInfo/info.txt";
            // String fileName = "C:\\Users\\JiHee\\IntelliJProJect\\HuntAnimal\\resourceFolder\\UserInfo\\info.txt";
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                // System.out.println(temp);
                String[] tempInfo = temp.split(",");
                String tempId = tempInfo[0];
                String tempPw = tempInfo[1];
                int tempMoney = Integer.parseInt(tempInfo[2]);
                int[] tempAnimal = new int[4];
                int tempLevel = Integer.parseInt(tempInfo[7]);

                for (int i = 0; i < tempAnimal.length; i++) {
                    tempAnimal[i] = Integer.parseInt(tempInfo[i + 3]);
                }

                User tempUser = new User(tempId, tempPw, tempMoney, tempAnimal, tempLevel);

                info.add(tempUser);
                /*System.out.print(tempUser.getId() + " ");
                System.out.print(tempUser.getPw() + " ");
                System.out.print(tempUser.getMoney() + " ");
                System.out.print(tempUser.getAnimal() + " ");
                System.out.println(tempUser.getHunterDog());*/

                /*for (int i = 0; i < tempInfo.length; i++) {
                    System.out.print(tempInfo[i] + " ");
                }
                System.out.println();*/


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // saveInfo Test
        this.saveInfo(info);
        return info;
    }

    public void saveInfo(ArrayList<User> info) {
        try {
            String fileName = "./resourceFolder/UserInfo/info.txt";
            File file = new File(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < info.size(); i++) {
                String arrayToString = Integer.toString(info.get(i).getAnimal()[0]) + "," + Integer.toString(info.get(i).getAnimal()[1]) + "," + Integer.toString(info.get(i).getAnimal()[2]) + "," + Integer.toString(info.get(i).getAnimal()[3]);
                // System.out.println(arrayToString);
                String temp = info.get(i).getId() + "," + info.get(i).getPw() + "," + Integer.toString(info.get(i).getMoney()) + "," + arrayToString + "," + Integer.toString(info.get(i).getHunterDog());
                // System.out.println(temp);
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean logIn(String id, String pw) {
        boolean flag = false;
        this.info = this.loadInfo();

        for (int i = 0; i < info.size(); i++) {
            if (info.get(i).getId().equals(id) && info.get(i).getPw().equals(pw)) {
                System.out.println("�α��� ����");
                flag = true;
                break;
            }
        }

        return flag;
    }

    public boolean join(String id, String pw) {

        return false;
    }

    public boolean save(Hunter hunter) {
        return false;
    }

    public void showRank() {
    }

}
