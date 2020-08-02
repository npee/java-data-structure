package example.array;

public class ArrayTest {
    public static void main(String[] args) {
        int[] myArray = new int[10];

        for (int i = 0; i < 10; i++) {
            myArray[i] = i;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("index " + i + ": " + myArray[i]);
        }
    }
}
