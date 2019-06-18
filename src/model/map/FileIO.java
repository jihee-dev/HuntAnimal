package model.map;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import model.active.Hunter;
import model.active.HunterDog;
import model.active.Prey;
import model.item.*;

public class FileIO {
    private ArrayList<User> info;
    // id(0), pw(1), money(2), prison_deer(3), prison_rabbit(4), prison_tiger(5), prison_lion(6), dog_level(7), trap(8), net(9), gun(10), feed(11), asset(12)
   
    public static FileIO instance=null;

    public static FileIO getInstance() {
        if (instance == null)
            instance = new FileIO();
        return instance;
    }
    public ArrayList<User> getInfo(){
    	return this.info;
    }
    
    public ArrayList<User> loadInfo() {
        this.info = new ArrayList<User>();

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

                this.info.add(tempUser);

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
        this.saveInfo(this.info);
        return this.info;
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

        try {
            String encrypt = Encryption.sha256(pw);

            for (int i = 0; i < this.info.size(); i++) {
                if (this.info.get(i).getId().equals(id) && this.info.get(i).getPw().equals(encrypt)) {
                    System.out.println("로그인 성공");
                    flag = true;
                    this.hunterSetting(i);
                    break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!flag) {
            System.out.println("로그인 실패");
        }

        return flag;
    }

    public boolean join(String id, String pw) {
        boolean flag = true;
        this.info = this.loadInfo();

        if (!(Pattern.matches("^[a-zA-Z0-9]*$", id))) {
            System.out.println("아이디는 영문자와 숫자의 조합만 가능합니다.");
            flag = false;
        }

        if (flag) {
            for (int i = 0; i < this.info.size(); i++) {
                if (this.info.get(i).getId().equals(id)) {
                    System.out.println("아이디 중복 " + id + "는 사용할 수 없습니다.");
                    flag = false;
                    break;
                }
            }
        }

        int[] tempAnimal = {0, 0, 0, 0};
        int[] tempItems = {0, 0, 0, 0};

        String encrypt = null;
        if (flag) {
            try {
                encrypt = Encryption.sha256(pw);

                User newUser = new User(id, encrypt, 0, tempAnimal, 0, tempItems, 0);
                this.info.add(newUser);
                this.saveInfo(this.info);
                System.out.println(newUser.getId() + "님 회원가입에 성공했습니다!");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }

        return flag;
    }

    public boolean save(Hunter hunter) {
        String tempPw = "";
        int idx = 0;
        this.info = this.loadInfo();
        boolean flag = false;

        for (int i = 0; i < this.info.size(); i++) {
            if (hunter.getActionInfo().getName().equals(this.info.get(i).getId())) {
                idx = i;
                tempPw = this.info.get(idx).getPw();
                flag = true;
                break;
            }
        }

        Item tempTrap = hunter.getItems()[0];
        Item tempNet = hunter.getItems()[1];
        Item tempGun = hunter.getItems()[2];
        Item tempFeed = hunter.getItems()[3];
        int itemTotalPrice = (tempTrap.getPrice() * tempTrap.getCount()) + (tempNet.getPrice() * tempNet.getCount()) + (tempGun.getPrice() * tempGun.getCount()) + (tempFeed.getPrice() * tempFeed.getCount());
        int[] itemArray = {hunter.getItems()[0].getCount(), hunter.getItems()[1].getCount(), hunter.getItems()[2].getCount(), hunter.getItems()[3].getCount()};

        // Deer(50000), Rabbit(50000), Tiger(100000), Lion(100000)
        int tempDeer = 0;
        int tempRabbit = 0;
        int tempTiger = 0;
        int tempLion = 0;

        for (int i = 0; i < hunter.getPrison().size(); i++) {
            if (hunter.getPrison().get(i).getActionInfo().getName().equals("Deer")) {
                tempDeer++;
            } else if (hunter.getPrison().get(i).getActionInfo().getName().equals("Rabbit")) {
                tempRabbit++;
            } else if (hunter.getPrison().get(i).getActionInfo().getName().equals("Tiger")) {
                tempTiger++;
            } else if (hunter.getPrison().get(i).getActionInfo().getName().equals("Lion")) {
                tempLion++;
            }
        }

        int animalTotalPrice = tempDeer * 50000 + tempRabbit * 50000 + tempTiger * 100000 + tempLion * 100000;
        int[] animalArray = {tempDeer, tempRabbit, tempTiger, tempLion};

        hunter.setAsset(hunter.getMoney() + itemTotalPrice + animalTotalPrice);

        // String id, String pw, int money, int[] animal, int hunterDog, int[] items, int asset
        User tempUser = new User(hunter.getActionInfo().getName(), tempPw, hunter.getMoney(), animalArray, hunter.getDog().getLevel(), itemArray, hunter.getAsset());

        this.info.set(idx, tempUser);
        this.saveInfo(this.info);

        return flag;
    }

    public void sortRank() {
        this.info = loadInfo();
        int minIdx = 0;

        for (int i = this.info.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (this.info.get(j).getAsset() <= this.info.get(minIdx).getAsset()) {
                    minIdx = j;
                }
            }

            User temp = this.info.get(i);
            this.info.set(i, this.info.get(minIdx));
            this.info.set(minIdx, temp);
            minIdx = 0;
        }

//        System.out.println("===== Ranking! =====");
//        for (int i = 0; i < this.info.size(); i++) {
//           System.out.println((i + 1) + ". " + this.info.get(i).getId() + " " + this.info.get(i).getAsset());
//       }
    }

    void hunterSetting(int idx) {
        // id(0), pw(1), money(2), prison_deer(3), prison_rabbit(4), prison_tiger(5), prison_lion(6), dog_level(7), trap(8), net(9), gun(10), feed(11), asset(12)

        Hunter.getInstance().getActionInfo().setName(this.info.get(idx).getId());
        Hunter.getInstance().setMoney(this.info.get(idx).getMoney());

        int tempDeer = this.info.get(idx).getAnimal()[0];
        int tempRabbit = this.info.get(idx).getAnimal()[1];
        int tempTiger = this.info.get(idx).getAnimal()[2];
        int tempLion = this.info.get(idx).getAnimal()[3];

        ArrayList<Prey> tempPrison = new ArrayList<Prey>();

        for (int i = 0; i < tempDeer; i++) {
            // int delay, int price, String name, String BtnImg1, String BtnImg2
            Prey tempAni = new Prey(45, 50000, "Deer", "./resourceFolder/image/animal/DeerLeft.png", "./resourceFolder/image/animal/DeerRight.png");
            tempPrison.add(tempAni);
        }

        for (int i = 0; i < tempRabbit; i++) {
            // int delay, int price, String name, String BtnImg1, String BtnImg2
            Prey tempAni = new Prey(40, 50000, "Rabbit", "./resourceFolder/image/animal/RabbitLeft.png", "./resourceFolder/image/animal/RabbitRight.png");
            tempPrison.add(tempAni);
        }

        for (int i = 0; i < tempTiger; i++) {
            // int delay, int price, String name, String BtnImg1, String BtnImg2
            Prey tempAni = new Prey(35, 100000, "Tiger", "./resourceFolder/image/animal/TigerLeft.png", "./resourceFolder/image/animal/TigerRight.png");
            tempPrison.add(tempAni);
        }

        for (int i = 0; i < tempLion; i++) {
            // int delay, int price, String name, String BtnImg1, String BtnImg2
            Prey tempAni = new Prey(35, 100000, "Lion", "./resourceFolder/image/animal/LionLeft.png", "./resourceFolder/image/animal/LionRight.png");
            tempPrison.add(tempAni);
        }

        Hunter.getInstance().setPrison(tempPrison);

        HunterDog dog = HunterDog.getInstance();
        dog.setLevel(this.info.get(idx).getHunterDog());
        Hunter.getInstance().setDog(dog);

        Trap.getInstance().setCount(info.get(idx).getItems()[0]);
        Net.getInstance().setCount(info.get(idx).getItems()[1]);
        Gun.getInstance().setCount(info.get(idx).getItems()[2]);
        Feed.getIstance().setCount(info.get(idx).getItems()[3]);

        Hunter.getInstance().setAsset(this.info.get(idx).getAsset());
    }
}
