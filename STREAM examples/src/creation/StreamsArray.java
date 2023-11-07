package creation;

import java.util.Arrays;
import java.util.List;

public class StreamsArray {

    public static void main(String[] args) {

        int [] numeros = {1,2,3,4,5};

        long cantNumero = Arrays.stream(numeros).count();
        long sumNumero = Arrays.stream(numeros).sum();


        System.out.println(cantNumero);
    }
}
