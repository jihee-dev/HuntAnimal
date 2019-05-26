package Ver3;
import java.util.ArrayList;

public class Map {
    private String name;
    public ArrayList<Animal> ani = new ArrayList<Animal>();

    public void setName(String str) {
        this.name = str;

    }

    public String getName() {
        return this.name;

    }

    //추가
    public void print_array() {
        System.out.printf("==========%s=========\n", this.getName());
        for (int i = 0; i < this.ani.size(); i++)
            System.out.printf("[%d]번째 동물 : %s\n", (i + 1), this.ani.get(i).getName());
        System.out.println("=========================");
    }
}
