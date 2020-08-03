package example.array;

// 기본 동작, static 함수로 추가
public class ArrayTest {
    public static final int LENGTH = 10;
    public static int count = 0;

    public static void main(String[] args) {
        int[] myArray = new int[LENGTH];

        add(myArray, 1);
        add(myArray, 2);

        for (int i = 0; i < 5; i++) {
            add(myArray, 10);
        }

        insert(myArray, 1, 5);

        remove(myArray, 0);
        remove(myArray, 4);

        for (int i = 0; i < LENGTH; i++) {
            System.out.println("index " + i + ": " + myArray[i]);
        }
    }

    public static void add(int[] array, int x) {
        // TODO: 주어진 배열 공간이 다 찼을 경우에 대한 예외 처리
        array[count] = x;
        count++;
    }

    public static void insert(int[] array, int idx, int x) {
        // TODO: 주어진 배열 공간이 다 찼을 경우에 대한 예외 처리
        for (int i = count; i >= idx; i--) {
            array[i+1] = array[i];
        }
        array[idx] = x;
        count++;
    }

    public static void remove(int[] array, int idx) {
        for (int i = idx; i < count - 1; i++) {
            array[i] = array[i+1];
        }
        array[count - 1] = 0;
        count--;
    }
}
