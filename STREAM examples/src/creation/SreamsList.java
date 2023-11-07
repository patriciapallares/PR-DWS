package creation;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class SreamsList {

    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();

        nombres.add("Ana");
        nombres.add("Laura");
        nombres.add("Gerson");

        nombres.stream().forEach(System.out::println);



    }
}
