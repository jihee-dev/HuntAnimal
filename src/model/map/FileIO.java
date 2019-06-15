package model.map;

import java.io.*;
import java.util.ArrayList;

import controller.TestMain;
import model.active.Hunter;
import model.item.Item;

public class FileIO {
    private ArrayList<User> info;
    // id(0), pw(1), money(2), prison_deer(3), prison_rabbit(4), prison_tiger(5), prison_lion(6), dog_level(7), trap(8), net(9), gun(10), feed(11), asset(12)

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
                String[] tempInfo = temp.split(", ");
                String tempId = tempInfo[0];
                String tempPw = tempInfo[1];
                int tempMoney = Integer.parseInt(tempInfo[2]);
                int[] tempAnimal = new int[4];
                int tempLevel = Integer.parseInt(tempInfo[7]);
                int[] tempItem = new int[4];
                int tempAsset = Integer.parseInt(tempInfo[12]);

                for (int i = 0; i < 4; i++) {
                    tempAnimal[i] = Integer.parseInt(tempInfo[i + 3]);
                    tempItem[i] = Integer.parseInt(tempInfo[i + 8]);
                }


                User tempUser = new User(tempId, tempPw, tempMoney, tempAnimal, tempLevel, tempItem, tempAsset);

                info.add(tempUser);

                // Test Code
                /*System.out.print(tempUser.getId() + " ");
                System.out.print(tempUser.getPw() + " ");
                System.out.print(tempUser.getMoney() + " ");
                System.out.print(tempUser.getAnimal() + " ");
                System.out.print(tempUser.getHunterDog() + " ");
                System.out.println(tempUser.getItems());*/

                // Test Code
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
                String animalToString = Integer.toString(info.get(i).getAnimal()[0]) + ", " + Integer.toString(info.get(i).getAnimal()[1]) + ", " + Integer.toString(info.get(i).getAnimal()[2]) + ", " + Integer.toString(info.get(i).getAnimal()[3]);
                String itemToString = Integer.toString(info.get(i).getItems()[0]) + ", " + Integer.toString(info.get(i).getItems()[1]) + ", " + Integer.toString(info.get(i).getItems()[2]) + ", " + Integer.toString(info.get(i).getItems()[3]);
                // System.out.println(arrayToString);
                String temp = info.get(i).getId() + ", " + info.get(i).getPw() + ", " + Integer.toString(info.get(i).getMoney()) + ", " + animalToString + ", " + Integer.toString(info.get(i).getHunterDog()) + ", " + itemToString + ", " + Integer.toString(info.get(i).getAsset());
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
                System.out.println("로그인 성공");
                flag = true;
                this.setHunter(i);
                break;
            }
        }

        // 헌터 객체 만들고 저장

        return flag;
    }

    public boolean join(String id, String pw) {
        boolean flag = true;
        this.info = this.loadInfo();

        for (int i = 0; i < info.size(); i++) {
            if (info.get(i).getId().equals(id)) {
                System.out.println("아이디 중복");
                flag = false;
                break;
            }
        }

        int[] tempAnimal = {0, 0, 0, 0};
        int[] tempItems = {0, 0, 0, 0};

        if (flag) {
            User newUser = new User(id, pw, 0, tempAnimal, 0, tempItems, 0);
            info.add(newUser);
            this.saveInfo(info);
            return true;
        } else {
            System.out.println("회원가입에 실패했습니다.");
            return false;
        }
    }

    public boolean save(Hunter hunter) {
        // 미완성
        String tempPw;
        this.info = this.loadInfo();

        for (int i = 0; i < this.info.size(); i++) {
            if (hunter.getActionInfo().getName().equals(info.get(i).getId())) {
                tempPw = info.get(i).getPw();
                break;
            }
        }

        Item tempTrap = hunter.getItems()[0];
        Item tempNet = hunter.getItems()[1];
        Item tempGun = hunter.getItems()[2];
        Item tempFeed = hunter.getItems()[3];
        String tempItem = hunter.getItems()[0].getCount() + ", " + hunter.getItems()[1].getCount() + ", " + hunter.getItems()[2].getCount() + ", " + hunter.getItems()[3].getCount();
        int itemTotalPrice = (tempTrap.getPrice() * tempTrap.getCount()) + (tempNet.getPrice() * tempNet.getCount()) + (tempGun.getPrice() * tempGun.getCount()) + (tempFeed.getPrice() * tempFeed.getCount());

        hunter.setAsset(hunter.getMoney() + itemTotalPrice);

        // User tempUser = new User(hunter.getActionInfo().getName(), "", )

        return false;
    }

    public void sortRank() {
        this.info = loadInfo();
        int minIdx = 0;

        for (int i = info.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (info.get(j).getAsset() <= info.get(minIdx).getAsset()) {
                    minIdx = j;
                }
            }

            User temp = info.get(i);
            info.set(i, info.get(minIdx));
            info.set(minIdx, temp);
            minIdx = 0;
        }

        System.out.println("===== Ranking! =====");
        for (int i = 0; i < info.size(); i++) {
            System.out.println(info.get(i).getId() + " " + info.get(i).getAsset());
        }
    }

    void setHunter(int idx) {
        // id(0), pw(1), money(2), prison_deer(3), prison_rabbit(4), prison_tiger(5), prison_lion(6), dog_level(7), trap(8), net(9), gun(10), feed(11), asset(12)

        TestMain.hunter.getActionInfo().setName(info.get(idx).getId());
        TestMain.hunter.setMoney(info.get(idx).getMoney());
        // TestMain.hunter.setPrison();

    }
}
