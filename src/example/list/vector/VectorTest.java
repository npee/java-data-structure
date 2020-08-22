package example.list.vector;

import java.util.List;
import java.util.Vector;

import static example.configuration.PrintConfig.print;

public class VectorTest {
    public static void main(String[] args) {
        List<String> vector = new Vector<>();

        vector.add("Java");
        vector.add("Data");
        vector.add("Structure");

        print(vector);
    }
}
