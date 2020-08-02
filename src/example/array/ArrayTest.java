package example.array;

// 기본 동작, static 함수로 추가
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
