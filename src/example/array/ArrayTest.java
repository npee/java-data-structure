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

        /** string */
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

        /** object */
        List<MutableObject> mutableObjects = new ArrayList<>();
        mutableObjects.add(new MutableObject(1));
        mutableObjects.add(new MutableObject(2));
        mutableObjects.add(new MutableObject(3));
        mutableObjects.add(new MutableObject(4));

        List<MutableObject> mutabRef = mutableObjects;
        List<MutableObject> mutabNew = new ArrayList<>(mutableObjects); // 복사 생성자
        List<MutableObject> mutabSet = new ArrayList<>(mutableObjects); // 가변 객체 테스트

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

        // copy
        int[] source1d = {5, 10, 15, 20, 25};
        int[][] source2d = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};

        int[] target1dRef = source1d; // 참조
        int[][] target2dRef = source2d;

        int[][] reftest = new int[source2d.length][source2d[0].length];

        /** direct copy */
        int[] target1dDirect = new int[source1d.length];
        int[][] target2dDirect = new int[source2d.length][source2d[0].length];

        System.out.println("직접 복사 - 1차원 배열-------------------");
        printArray(true, source1d);

        for (int i = 0; i < source1d.length; i++)
            target1dDirect[i] = source1d[i]; // copy

        target1dDirect[3] = 200; // 복사본 값 변경

        System.out.println("복사본 변경 후--------------------------");
        printArray(true, source1d);
        printArray(false, target1dDirect);
        System.out.println("--------------------------------------");

        System.out.println("직접 복사 - 2차원 배열-------------------");
        printArray(true, source2d);

        for (int i = 0; i < source2d.length; i++)
            for (int j = 0; j < source2d[0].length; j++)
                target2dDirect[i][j] = source2d[i][j]; // copy

        target2dDirect[1][1] = 100; // 복사본 값 변경

        System.out.println("복사본 변경 후--------------------------");
        printArray(false, source2d);
        printArray(false, target2dDirect);
        System.out.println("--------------------------------------");

        /** Object.clone() */
        int[] target1dClone;
        int[][] target2dClone;
        System.out.println("clone() - 1차원 배열--------------------");
        printArray(true, source1d);

        target1dClone = source1d.clone(); // copy
        target1dClone[4] = 100;

        System.out.println("복사본 변경 후--------------------------");
        printArray(true, source1d);
        printArray(false, target1dClone);
        System.out.println("--------------------------------------");

        System.out.println("clone() - 2차원 배열-------------------");
        printArray(true, source2d);

        target2dClone = source2d.clone(); // incomplete deep copy
        target2dClone[1][2] = 200;

        System.out.println("복사본 변경 후(얕은 복사)----------------");
        printArray(true, source2d);
        printArray(false, target2dClone);
        source2d[1][2] = 8; // 원상복구
        System.out.println("--------------------------------------");

        System.out.println("clone() - 2차원 배열--------------------");
        for (int i = 0; i < source2d.length; i++) {
            target2dClone[i] = source2d[i].clone(); // complete deep copy
        }

        target2dClone[1][2] = 200;

        System.out.println("복사본 변경 후(깊은 복사)----------------");
        printArray(true, source2d);
        printArray(false, target2dClone);
        System.out.println("--------------------------------------");


        /** System.arraycopy() */
        // clone(): 초기화 필요없음
        // arraycopy(): 초기화 필요함 - 내용만 카피하기 때문. 변수를 재사용 할 경우 새 인스턴스로 초기화 해야한다.
        int[] target1dArraycopy = new int[source1d.length];
        int[][] target2dArraycopy = new int[source2d.length][source2d[0].length];

        System.out.println("arraycopy() - 1차원 배열---------------");
        printArray(true, source1d);

        System.arraycopy(source1d, 0, target1dArraycopy, 0, source1d.length); // copy
        target1dArraycopy[1] = 100;

        System.out.println("복사본 변경 후--------------------------");
        printArray(true, source1d);
        printArray(false, target1dArraycopy);
        System.out.println("--------------------------------------");

        System.out.println("arraycopy() - 2차원 배열---------------");
        printArray(true, source2d);

        // incomplete deep copy
        System.arraycopy(source2d, 0, target2dArraycopy, 0, source2d.length);
        target2dArraycopy[1][3] = 500;

        System.out.println("복사본 변경 후(얕은 복사)----------------");
        printArray(true, source2d);
        printArray(false, target2dArraycopy);
        source2d[1][3] = 9;
        target2dArraycopy = new int[source2d.length][source2d[0].length]; // 레퍼런스 초기화
        System.out.println("--------------------------------------");

        System.out.println("arraycopy() - 2차원 배열---------------");
        printArray(true, source2d);

        for (int i = 0; i < source2d.length; i++)
            System.arraycopy(source2d[i], 0, target2dArraycopy[i], 0, source2d[0].length); // complete deep copy
        target2dArraycopy[1][3] = 500;

        System.out.println("복사본 변경 후(깊은 복사)----------------");
        printArray(true, source2d);
        printArray(false, target2dArraycopy);
        System.out.println("--------------------------------------");


        /** Arrays.copyOf(), Arrays.copyOfRange()*/
        System.out.println("arraycopy() - 1차원 배열---------------");
        printArray(true, source1d);
        // copy
        int[] copyOfSource1d = Arrays.copyOf(source1d, source1d.length);
        int[] copyOfRangeSource1d = Arrays.copyOfRange(source1d, 0, source1d.length);
        System.out.println("복사본 변경 후--------------------------");

        System.out.println("--------------------------------------");
        printArray(true, source1d);
        printArray(false, copyOfSource1d);
        printArray(false, copyOfRangeSource1d);
        System.out.println("arraycopy() - 2차원 배열---------------");
        printArray(true, source2d);
        // incomplete deep copy
        int[][] copyOfSource2d = Arrays.copyOf(source2d, source2d.length);
        int[][] copyOfRangeSource2d = Arrays.copyOfRange(source2d, 0, source2d.length);

        System.out.println("복사본 변경 후--------------------------");
        printArray(true, source2d);
        printArray(false, copyOfSource2d);
        printArray(false, copyOfRangeSource2d);
        System.out.println("--------------------------------------");

        System.out.println("arraycopy() - 2차원 배열---------------");
        printArray(true, source2d);
        // complete deep copy
        for (int i = 0; i < source2d.length; i++) {
            copyOfSource2d[i] = Arrays.copyOf(source2d[i], source2d[i].length);
            copyOfRangeSource2d[i] = Arrays.copyOfRange(source2d[i], 0, source2d[i].length);
        }

        System.out.println("복사본 변경 후--------------------------");
        printArray(true, source2d);
        printArray(false, copyOfSource2d);
        printArray(false, copyOfRangeSource2d);
        System.out.println("--------------------------------------");

    }

    public static void printArray(boolean isOriginal, Object T) {
        if (isOriginal) System.out.print("원본  : ");
        else System.out.print("복사본: ");
        if (T instanceof int[])
            System.out.println(toCustomString((int[]) T));
        else if (T instanceof int[][])
            System.out.println(toCustomString((int[][]) T));
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

    public static String toCustomString(int[][] array) {
        String arrayStr = "[\n";
        for (int i = 0; i < array.length; i++) {
            arrayStr += "\t" + toCustomString(array[i]) + ",\n";
        }
        arrayStr += "]";
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
        return array.clone();
    }

    public static int[][] deepcopy2d(int[][] array) {
        int[][] dest = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            dest[i] = array[i].clone();
        }
        return dest;
    }
}
