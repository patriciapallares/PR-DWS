package creation;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamEmpty {


    public static void main(String[] args) {

        Stream<String> nombreStream = Stream.empty();

        ArrayList<String> nombres2 = new ArrayList<>();

        nombres2.add("Claudia");
        nombres2.add("Jose");
        nombres2.add("Ivan");

        nombreStream = nombres2.stream();
        Stream<String> nombreStream2 = Stream.empty();

        // Stream.concat(nombreStream. nombreStream2);
    }
}
