package example.array;

// 기본 동작, static 함수로 추가
// 요소는 자연수로 제한
public class ArrayTest {
    public static final int LENGTH = 10;

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

        System.out.println(toCustomString(myArray));

        int[] source1 = new int[LENGTH];
        int[] source2 = new int[LENGTH];

        add(source1, 1);
        add(source1, 2);
        add(source1, 3);
        add(source2, 1);
        add(source2, 2);
        add(source2, 3);

        // 배열의 주소값을 비교하게 되므로 다를 수 밖에 없다
        System.out.println(toCustomString(source1));
        System.out.println(toCustomString(source2));

        if (source1 == source2) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

        // equals
        if (equals(source1, source2)) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
        int[] source3 = source1;
        int[] source4 = source1;

        if (source3 == source4) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }

    public static String toCustomString(int[] array) {
        String arrayStr = "[";
        for (int i = 0; array[i] != 0; i++) {
            if (array[i + 1] == 0) {
                arrayStr += array[i] + "]";
            } else {
                arrayStr += array[i] + ", ";
            }
        }
        return arrayStr;
    }

    public static void add(int[] array, int x) {
        if (array[array.length - 1] != 0) {
            System.out.println("배열에 남은 공간이 없습니다.");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = x;
                break;
            }
        }
    }

    public static void insert(int[] array, int idx, int x) {
        if (array[array.length - 1] != 0) {
            System.out.println("배열에 남은 공간이 없습니다.");
            return;
        }
        for (int i = array.length - 2; i >= idx; i--) {
            array[i+1] = array[i];
        }
        array[idx] = x;
    }

    public static void remove(int[] array, int idx) {
        for (int i = idx; array[i] != 0; i++) {
            array[i] = array[i+1];
        }
    }

    public static boolean equals(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}
