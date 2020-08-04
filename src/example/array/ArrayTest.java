package example.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 기본 동작, static 함수로 추가
// 요소는 자연수로 제한
class MutableObject {
    public int x;

    public MutableObject(int x) {
        this.x = x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
public class ArrayTest {
    public static final int LENGTH = 10;

    public static void main(String[] args) {
        // LENGTH 만큼의 길이를 가진 배열 할당(배열의 크기 = 길이)
        int[] myArray = new int[LENGTH];
        // 선언과 동시에 배열 초기화. 길이는 4가 된다.
        int[] myArray2 = {10, 20, 30, 40};
        int[] myArray2_1;
        /*
        // {} 은 배열 초기화 용도이므로 정의할 때는 사용할 수 없다.
        int[] myArray2_2;
        myArray2_2 = {1, 2, 3, 4};
        */
        // 변수명에 []을 붙이면 C 스타일
        int myArray3[] = {100, 200, 300, 400};
        // 2차원 배열 동적할당
        int[] myArray4[] = new int[5][];
        int myArray5[][][] = new int[10][][];
        int myArray6[][] = {{1, 2}, {3, 4}, {5, 6}};

        for (int i = 0; i < myArray2.length; i++) {
            myArray2[i] += 10;
            System.out.println("myArray[" + i + "]: " + myArray2[i]);
        }

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

        myArray = new int[LENGTH];

        add(myArray, 1);
        add(myArray, 2);
        add(myArray, 3);
        add(myArray, 4);
        add(myArray, 5);
        add(myArray, 6);
        add(myArray, 7);
        add(myArray, 8);
        add(myArray, 9);
        add(myArray, 10);

        remove(myArray, 9);

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
        System.out.println(source1);
        System.out.println(source2);

        if (source1 == source2) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

        System.out.println(source1 == source2);

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

        // copy
        int[] source15 = {5, 10, 15, 20, 25};
        int[][] source32 = {{10, 20}, {30, 40}, {50, 60}};

        int[] ref15;
        int[] source15clone = source15.clone();
        int[] copyOfSrc15;
        int[] copyOfRangeSrc15;

        int[][] ref32 = source32; // 참조
        int[][] ref32deep;
        int[][] reftest = new int[source32.length][source32[0].length];
        int[][] source32clone = new int[source32.length][source32[0].length];
        int[][] copyOfSrc32 = new int[source32.length][source32[0].length];
        int[][] copyOfRangeSrc32 = new int[source32.length][source32[0].length];

        String[] strArr = new String[5];
        String[][] str2dArr = new String[][]{{"aaa", "bbb"}, {"ccc", "ddd"}};
        strArr[0] = "abc";
        strArr[1] = "def";
        strArr[2] = "ghi";
        strArr[3] = "jkl";
        strArr[4] = "mno";
        String[] strRef = strArr;
        String[] strRef2;
        strRef[4] = "pqr";
        String[][] str2dRef;

        List<MutableObject> mutableObjects = new ArrayList<>();
        mutableObjects.add(new MutableObject(1));
        mutableObjects.add(new MutableObject(2));
        mutableObjects.add(new MutableObject(3));
        mutableObjects.add(new MutableObject(4));

        List<MutableObject> mutabRef = mutableObjects;
        List<MutableObject> mutabNew = new ArrayList<>(mutableObjects); // 복사 생성자
        List<MutableObject> mutabSet = new ArrayList<>(mutableObjects); // 가변 객체

        for (MutableObject mutableObject : mutableObjects) {
            System.out.println(mutableObject.x);
        }

        mutabRef.add(new MutableObject(5));
        mutabNew.add(new MutableObject(6));
        // shallow copy(mutable object)
        mutabSet.get(0).setX(10);

        for (MutableObject mutableObject : mutableObjects) {
            System.out.println(mutableObject.x);
        }

        for (MutableObject mutableObject : mutabNew) {
            System.out.println(mutableObject.x);
        }

        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }

        strRef2 = strArr.clone();
        strRef2[4] = "stu";

        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }

        for (int i = 0; i < strRef2.length; i++) {
            System.out.println(strRef2[i]);
        }


        for (int i = 0; i < str2dArr.length; i++) {
            for (int j = 0; j < str2dArr[0].length; j++) {
                System.out.println(str2dArr[i][j]);
            }
        }

        str2dRef = str2dArr.clone();
        str2dRef[1][1] = "xxx";

        for (int i = 0; i < str2dArr.length; i++) {
            for (int j = 0; j < str2dArr[0].length; j++) {
                System.out.println(str2dArr[i][j]);
            }
        }

        ref15 = deepcopy(source15);
        ref32deep = deepcopy2d(source32);

        // shallow copy
        System.arraycopy(source32, 0, reftest, 0, source32.length);

        copyOfSrc15 = Arrays.copyOf(source15, source15.length);
        copyOfRangeSrc15 = Arrays.copyOfRange(source15, 0, source15.length);

        // shallow copy
        // copyOfSrc32 = Arrays.copyOf(source32, source32.length);
        // copyOfRangeSrc32 = Arrays.copyOfRange(source32, 0, source32.length);

        for (int i = 0; i < source32.length; i++) {
            source32clone[i] = source32[i].clone();
            copyOfSrc32[i] = Arrays.copyOf(source32[i], source32[i].length);
            copyOfRangeSrc32[i] = Arrays.copyOfRange(source32[i], 0, source32[i].length);
        }

        System.out.println(toCustomString(source15));

        for (int i = 0; i < 3; i++) {
            System.out.println(toCustomString(source32[i]));
        }

        ref15[4] = 750;
        source15clone[4] = 1050;
        copyOfSrc15[4] = 120;
        copyOfRangeSrc15[4] = 250;

        ref32[2][1] = 100;
        ref32deep[2][1] = 200;

        System.out.println(toCustomString(source15));
        System.out.println(toCustomString(ref15));
        System.out.println(toCustomString(source15clone));
        System.out.println(toCustomString(copyOfSrc15));
        System.out.println(toCustomString(copyOfRangeSrc15));

        System.out.println(source32[2][1]);
        System.out.println(ref32[2][1]);
        System.out.println(ref32deep[2][1]);
        System.out.println(reftest[2][1]);
        System.out.println(source32clone[2][1]);
        System.out.println(copyOfSrc32[2][1]);
        System.out.println(copyOfRangeSrc32[2][1]);

        reftest[2][1] = 300;
        source32clone[2][1] = 600;
        copyOfSrc32[2][1] = 900;
        copyOfRangeSrc32[2][1] = 1200;

        System.out.println(source32[2][1]);
        System.out.println(ref32[2][1]);
        System.out.println(ref32deep[2][1]);
        System.out.println(reftest[2][1]);
        System.out.println(source32clone[2][1]);
        System.out.println(copyOfSrc32[2][1]);
        System.out.println(copyOfRangeSrc32[2][1]);

    }

    public static String toCustomString(int[] array) {
        String arrayStr = "[";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
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
        if (array[idx] == 0) {
            System.out.println("삭제할 데이터가 없습니다.");
            return;
        }
        if (idx == array.length - 1) {
            array[idx] = 0;
        } else {
            for (int i = idx; array[i] != 0; i++) {
                array[i] = array[i+1];
            }
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

    public static int[] deepcopy(int[] array) {
        int[] dest = new int[array.length];
        System.arraycopy(array, 0, dest, 0, array.length);
        return dest;
    }

    public static int[][] deepcopy2d(int[][] array) {
        int[][] dest = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, array[i], 0, array[0].length);
        }
        return dest;
    }
}
