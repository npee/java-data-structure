package example.set;

import java.util.HashSet;

import static example.configuration.PrintConfig.print;

public class HashSetTest {

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(3);

        print(hashSet);
    }
}
