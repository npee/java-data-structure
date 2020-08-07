package example.array;
import example.MutableObject;

import java.util.*;
import java.util.stream.Collectors;

// 기본 동작, static 함수로 추가
// 요소는 자연수로 제한
public class ArrayTest {
    public static final int LENGTH = 10;

    public static void main(String[] args) {
        // LENGTH 만큼의 길이를 가진 배열 할당(배열의 크기 = 길이)
        int[] myArray = new int[LENGTH];
        // 선언과 동시에 배열 초기화. 길이는 4가 된다.
        int[] myArray2 = {10, 20, 30, 40};
        /*
        // {} 은 배열 초기화 용도이므로 정의할 때는 사용할 수 없다.
        int[] myArray2_1;
        myArray2_1 = {1, 2, 3, 4};
        */
        // 변수명에 []을 붙이면 C 스타일
        int myArray3[] = {100, 200, 300, 400};
        // 2차원 배열 동적할당
        int[] myArray4[] = new int[5][];
        int myArray5[][][] = new int[10][][];
        int myArray6[][] = {{1, 2}, {3, 4}, {5, 6}};

        /* 1차원 배열 모두 출력 */
        for (int i = 0; i < myArray2.length; i++) {
            myArray2[i] += 10; // 배열 연산
            System.out.println("myArray2[" + i + "]: " + myArray2[i]);
        }

        /* 2차원 배열 모두 출력 */
        for (int i = 0; i < myArray6.length; i++) {
            for (int j = 0; j < myArray6[0].length; j++) {
                System.out.println("myArray6[" + i + "][" + j +  "]: " + myArray6[i][j]);
            }
        }

        /* 배열 마지막에 값 추가하기 */
        add(myArray, 1);
        add(myArray, 2);

        for (int i = 0; i < 5; i++) {
            add(myArray, 10);
        }

        /* 해당 인덱스에 값 삽입하기 */
        insert(myArray, 1, 5);

        /* 해당 인덱스의 값 삭제하기 */
        remove(myArray, 0);
        remove(myArray, 4);

        print(myArray);

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

        print(myArray);

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

        
        /** copy (불변 객체) */
        int[] source1d = {5, 10, 15, 20, 25};
        int[][] source2d = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};


        /* reference(shallow copy) */
        int[] target1dRef;
        int[][] target2dRef;

        System.out.println("참조 - 1차원 배열");
        print(true, source1d);
        target1dRef = source1d; // 참조

        System.out.println("복사본 변경 후(얕은 복사)");
        target1dRef[3] = 100;

        print(true, source1d);
        print(false, target1dRef);

        System.out.println("--------------------------------------\n");
        System.out.println("참조 - 2차원 배열(깊은 복사?)");
        target2dRef = source2d;

        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        target2dRef[1][4] = 999;

        print(true, source2d);
        print(false, target2dRef);

        target2dRef[1][4] = 10; // 원상복구
        System.out.println("--------------------------------------\n");


        /* Object.clone() */
        int[] target1dClone;
        int[][] target2dClone;

        System.out.println("clone() - 1차원 배열");
        print(true, source1d);

        target1dClone = source1d.clone(); // copy

        System.out.println("복사본 변경 후");
        target1dClone[4] = 100;

        print(true, source1d);
        print(false, target1dClone);

        System.out.println("--------------------------------------\n");
        System.out.println("clone() - 2차원 배열(깊은 복사?)");
        print(true, source2d);

        target2dClone = source2d.clone(); // incomplete deep copy

        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        target2dClone[1][2] = 200;

        print(true, source2d);
        print(false, target2dClone);

        source2d[1][2] = 8; // 원상복구
        System.out.println("--------------------------------------\n");

        System.out.println("clone() - 2차원 배열(깊은 복사)");
        for (int i = 0; i < source2d.length; i++) {
            target2dClone[i] = source2d[i].clone(); // complete deep copy
        }

        System.out.println("복사본 변경 후");
        target2dClone[1][2] = 200;

        print(true, source2d);
        print(false, target2dClone);
        System.out.println("--------------------------------------\n");


        /* System.arraycopy() */
        // clone(): 초기화 필요없음
        // arraycopy(): 초기화 필요함 - 내용만 카피하기 때문. 변수를 재사용 할 경우 새 인스턴스로 초기화 해야한다.
        int[] target1dArraycopy = new int[source1d.length];
        int[][] target2dArraycopy = new int[source2d.length][source2d[0].length];

        System.out.println("arraycopy() - 1차원 배열");
        print(true, source1d);

        System.arraycopy(source1d, 0, target1dArraycopy, 0, source1d.length); // copy

        System.out.println("복사본 변경 후");
        target1dArraycopy[1] = 100;

        print(true, source1d);
        print(false, target1dArraycopy);
        System.out.println("--------------------------------------\n");

        System.out.println("arraycopy() - 2차원 배열(깊은 복사?)");
        print(true, source2d);

        // incomplete deep copy
        System.arraycopy(source2d, 0, target2dArraycopy, 0, source2d.length);

        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        target2dArraycopy[1][3] = 500;

        print(true, source2d);
        print(false, target2dArraycopy);

        source2d[1][3] = 9; // 원상복구
        target2dArraycopy = new int[source2d.length][source2d[0].length]; // 레퍼런스 초기화
        System.out.println("--------------------------------------\n");

        System.out.println("arraycopy() - 2차원 배열(깊은 복사)");
        print(true, source2d);

        for (int i = 0; i < source2d.length; i++)
            System.arraycopy(source2d[i], 0, target2dArraycopy[i], 0, source2d[0].length); // complete deep copy

        System.out.println("복사본 변경 후");
        target2dArraycopy[1][3] = 500;

        print(true, source2d);
        print(false, target2dArraycopy);
        System.out.println("--------------------------------------\n");


        /* Arrays.copyOf(), Arrays.copyOfRange()*/
        System.out.println("copyOf() - 1차원 배열");
        print(true, source1d);

        int[] copyOfSource1d = Arrays.copyOf(source1d, source1d.length);
        int[] copyOfRangeSource1d = Arrays.copyOfRange(source1d, 0, source1d.length);

        System.out.println("복사본 변경 후");
        copyOfSource1d[1] = 100;
        copyOfRangeSource1d[0] = 200;

        print(true, source1d);
        print(false, copyOfSource1d);
        print(false, copyOfRangeSource1d);
        System.out.println("--------------------------------------\n");

        System.out.println("copyOf() - 2차원 배열(깊은 복사?)");
        print(true, source2d);

        // incomplete deep copy
        int[][] copyOfSource2d = Arrays.copyOf(source2d, source2d.length);
        int[][] copyOfRangeSource2d = Arrays.copyOfRange(source2d, 0, source2d.length);

        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        copyOfSource2d[0][4] = 50;
        copyOfRangeSource2d[1][0] = 100;

        print(true, source2d);
        print(false, copyOfSource2d);
        print(false, copyOfRangeSource2d);

        source2d[0][4] = 5; // 원상복구
        source2d[1][0] = 6;
        System.out.println("--------------------------------------\n");

        System.out.println("copyOf() - 2차원 배열(깊은 복사)");
        print(true, source2d);
        // complete deep copy
        for (int i = 0; i < source2d.length; i++) {
            copyOfSource2d[i] = Arrays.copyOf(source2d[i], source2d[i].length);
            copyOfRangeSource2d[i] = Arrays.copyOfRange(source2d[i], 0, source2d[i].length);
        }

        System.out.println("복사본 변경 후");
        copyOfSource2d[0][4] = 50;
        copyOfRangeSource2d[1][0] = 100;

        print(true, source2d);
        print(false, copyOfSource2d);
        print(false, copyOfRangeSource2d);
        System.out.println("--------------------------------------\n");


        /* direct copy */
        int[] target1dDirect = new int[source1d.length];
        int[][] target2dDirect = new int[source2d.length][source2d[0].length];

        System.out.println("직접 복사 - 1차원 배열");
        print(true, source1d);

        for (int i = 0; i < source1d.length; i++)
            target1dDirect[i] = source1d[i]; // copy

        System.out.println("복사본 변경 후");
        target1dDirect[3] = 200; // 복사본 값 변경

        print(true, source1d);
        print(false, target1dDirect);

        System.out.println("--------------------------------------\n");
        System.out.println("직접 복사 - 2차원 배열");
        print(true, source2d);

        for (int i = 0; i < source2d.length; i++)
            for (int j = 0; j < source2d[0].length; j++)
                target2dDirect[i][j] = source2d[i][j];

        System.out.println("복사본 변경 후");
        target2dDirect[1][1] = 100; // 복사본 값 변경

        print(false, source2d);
        print(false, target2dDirect);
        System.out.println("--------------------------------------\n");

        /** string copy */
        String[] strSource1d = {"abc", "def", "ghi", "jkl", "mno"};
        String[][] strSource2d = new String[][]{{"aaa", "bbb"}, {"ccc", "ddd"}};

        /* shallow copy */
        System.out.println("문자열 배열 참조 - 1차원 배열");
        print(true, strSource1d);
        String[] strTarget1dRef = strSource1d;

        System.out.println("복사본 변경 후(얕은 복사)");
        strTarget1dRef[4] = "pqr";

        print(true, strSource1d);
        print(false, strTarget1dRef);

        strSource1d[4] = "mno"; // 원상복구
        System.out.println("--------------------------------------\n");

        System.out.println("문자열 배열 참조 - 2차원 배열(깊은 복사?)");
        print(true, strSource2d);
        String[][] strTarget2dRef = strSource2d;

        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        strTarget2dRef[1][0] = "xxx";

        print(true, strSource2d);
        print(false, strTarget2dRef);

        strSource2d[1][0] = "ccc"; // 원상복구
        System.out.println("--------------------------------------\n");


        /* incomplete deep copy */
        System.out.println("문자열 배열 clone() - 1차원 배열");
        print(true, strSource1d);
        String[] strTarget1dClone = strSource1d.clone();

        System.out.println("복사본 변경 후");
        strTarget1dClone[0] = "zzz";

        print(true, strSource1d);
        print(false, strTarget1dClone);
        System.out.println("--------------------------------------\n");

        System.out.println("문자열 배열 clone() - 2차원 배열(깊은 복사?)");
        print(true, strSource2d);
        String[][] strTarget2dClone = strSource2d.clone();


        System.out.println("복사본 변경 후(얕은 복사가 됨)");
        strTarget2dClone[1][1] = "ooo";

        print(true, strSource2d);
        print(false, strTarget2dClone);

        strSource2d[1][1] = "ddd"; // 원상복구
        System.out.println("--------------------------------------\n");

        /* complete deep copy */
        System.out.println("문자열 배열 clone() - 2차원 배열(깊은 복사)");
        print(true, strSource2d);
        String[][] strTarget2dDeepClone = new String[strSource2d.length][];

        for (int i = 0; i < source2d.length; i++)
            strTarget2dDeepClone[i] = strSource2d[i].clone();

        System.out.println("복사본 변경 후");
        strTarget2dDeepClone[0][0] = "ABC";

        print(true, strSource2d);
        print(false, strTarget2dDeepClone);
        System.out.println("--------------------------------------\n");


        /** object copy (가변 객체) */
        List<MutableObject> sources = new ArrayList<>();
        sources.add(new MutableObject(1));
        sources.add(new MutableObject(2));
        sources.add(new MutableObject(3));
        sources.add(new MutableObject(4));


        /* reference(shallow copy) */
        System.out.println("객체 리스트 - 참조");
        print(true, sources);
        List<MutableObject> targetRef = sources;

        targetRef.add(new MutableObject(50));
        System.out.println("복사본 변경 후(얕은 복사)");
        print(true, sources);
        print(false, targetRef);
        sources.remove(sources.size() - 1); // 추가했던 객체 제거
        System.out.println("--------------------------------------\n");


        /* copy constructor */
        System.out.println("객체 리스트 - 복사 생성자");
        print(true, sources);
        List<MutableObject> targetConstructor = new ArrayList<>(sources); // 복사 생성자

        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
        targetConstructor.add(new MutableObject(100));

        print(true, sources);
        print(false, targetConstructor);
        System.out.println("--------------------------------------\n");

        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
        targetConstructor.get(0).setX(50);

        print(true, sources);
        print(false, targetConstructor);

        sources.get(0).setX(1); // 원상복구
        System.out.println("--------------------------------------\n");


        /* Collections.copy() */
        System.out.println("객체 리스트 - copy()");
        print(true, sources);
        List<MutableObject> targetCopy = new ArrayList<>();
        targetCopy.add(new MutableObject(0)); // 더미 객체로 메모리 확보
        targetCopy.add(new MutableObject(0));
        targetCopy.add(new MutableObject(0));
        targetCopy.add(new MutableObject(0));

        // 메모리 용량이 미리 확보되어 있어야 해서 때문에 클론 생성 용도로 쓰기 힘들다
        Collections.copy(targetCopy, sources);

        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
        targetCopy.add(new MutableObject(10));

        print(true, sources);
        print(false, targetCopy);
        System.out.println("--------------------------------------\n");

        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
        targetCopy.get(1).setX(50);

        print(true, sources);
        print(false, targetCopy);

        targetCopy.get(1).setX(2); // 원상복구
        System.out.println("--------------------------------------\n");


        /* Collections.addAll() */
        System.out.println("객체 리스트 - addAll()");
        print(true, sources);
        List<MutableObject> targetAddAll = new ArrayList<>();
        targetAddAll.addAll(sources);

        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
        targetAddAll.add(new MutableObject(300));

        print(true, sources);
        print(false, targetAddAll);
        System.out.println("--------------------------------------\n");

        System.out.println("2. 복사본 객체 변경 후(값 변경됨)");
        targetAddAll.get(2).setX(20);

        print(true, sources);
        print(false, targetAddAll);

        sources.get(2).setX(3); // 원상복구
        System.out.println("--------------------------------------\n");


        /* with stream */
        System.out.println("객체 리스트 - stream() 이용)");
        print(true, sources);
        List<MutableObject> targetStream;
        targetStream = sources.stream().collect(Collectors.toList());

        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
        targetStream.add(new MutableObject(500));

        print(true, sources);
        print(false, targetStream);
        System.out.println("--------------------------------------\n");

        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
        targetStream.get(0).setX(50);

        print(true, sources);
        print(false, targetStream);

        sources.get(0).setX(1); // 원상복구
        System.out.println("--------------------------------------\n");


        /* direct copy */
        System.out.println("객체 리스트 - 직접 복사");
        print(true, sources);
        List<MutableObject> targetDirect = new ArrayList<>();
        for (MutableObject source : sources) {
            targetDirect.add(new MutableObject(source.x));
        }
        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
        targetDirect.add(new MutableObject(50));

        print(true, sources);
        print(false, targetDirect);

        System.out.println("2. 복사본 객체 변경 후(깊은 복사!)");
        targetDirect.get(0).setX(100);

        print(true, sources);
        print(false, targetDirect);
        System.out.println("--------------------------------------\n");
    }
    
    public static void print(Object T) {
        if (T instanceof int[]) {
            Integer[] newInteger1dArr = Arrays.stream((int[]) T).boxed().toArray(Integer[]::new);
            System.out.println(toCustomString(newInteger1dArr));
        }
        else if (T instanceof int[][]) {
            Integer[][] newInteger2dArr = new Integer[((int[][]) T).length][];
            for (int i = 0; i < ((int[][]) T).length; i++) {
                newInteger2dArr[i] = Arrays.stream(((int[][]) T)[i]).boxed().toArray(Integer[]::new);
            }
            System.out.println(toCustomString(newInteger2dArr));
        }
        else if (T instanceof String[])
            System.out.println(toCustomString((String[]) T));
        else if (T instanceof String[][])
            System.out.println(toCustomString((String[][]) T));
        else if (T instanceof List<?>) {
            if (((List<?>) T).get(0) instanceof MutableObject) {
                int i = 0;
                System.out.println();
                for (MutableObject obj : (List<MutableObject>) T) {
                    System.out.println("item " + i + ": " + obj.toString());
                    i++;
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static void print(boolean isOriginal, Object T) {
        if (isOriginal) System.out.print("원본  : ");
        else System.out.print("복사본: ");
        print(T);
    }

    public static <T> String toCustomString(T[][] array) {
        String arrayStr = "[\n";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length -1)
                arrayStr += "\t" + toCustomString(array[i]);
            else
                arrayStr += "\t" + toCustomString(array[i]) + ",\n";
        }
        arrayStr += "]";
        return arrayStr;
    }

    public static <T> String toCustomString(T[] array) {
        String arrayStr = "[";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1)
                arrayStr += array[i] + "]";
            else
                arrayStr += array[i] + ", ";
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