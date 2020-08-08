package example.list;

import example.MutableObject;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        // List<int> intList; // primitive 형은 List의 요소가 될 수 없다.
        List<Integer> intList = new ArrayList<>(); // int를 사용하려면 Wrapper class로 감싸면 된다.
        List<String> strList = new ArrayList<>();
        List<MutableObject> mutableObjList = new ArrayList<>();

        /** 자바에서 제공하는 리스트 메서드 */

        /* add */
        intList.add(1); // Integer 형으로 바뀌어 들어간다.
        intList.add(3);
        intList.add(5);

        strList.add("Java");
        strList.add("Data");
        strList.add("Structure");

        mutableObjList.add(new MutableObject(10));
        mutableObjList.add(new MutableObject(30));
        mutableObjList.add(new MutableObject(50));

        print(intList);
        print(strList);
        print(mutableObjList);




//        /** object copy (가변 객체) */
//        List<MutableObject> sources = new ArrayList<>();
//        sources.add(new MutableObject(1));
//        sources.add(new MutableObject(2));
//        sources.add(new MutableObject(3));
//        sources.add(new MutableObject(4));
//
//
//        /* reference(shallow copy) */
//        System.out.println("객체 리스트 - 참조");
//        print(true, sources);
//        List<MutableObject> targetRef = sources;
//
//        targetRef.add(new MutableObject(50));
//        System.out.println("복사본 변경 후(얕은 복사)");
//        print(true, sources);
//        print(false, targetRef);
//        sources.remove(sources.size() - 1); // 추가했던 객체 제거
//        System.out.println("--------------------------------------\n");
//
//
//
//        /* copy constructor */
//        System.out.println("객체 리스트 - 복사 생성자");
//        print(true, sources);
//        List<MutableObject> targetConstructor = new ArrayList<>(sources); // 복사 생성자
//
//        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
//        targetConstructor.add(new MutableObject(100));
//
//        print(true, sources);
//        print(false, targetConstructor);
//        System.out.println("--------------------------------------\n");
//
//        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
//        targetConstructor.get(0).setX(50);
//
//        print(true, sources);
//        print(false, targetConstructor);
//
//        sources.get(0).setX(1); // 원상복구
//        System.out.println("--------------------------------------\n");
//
//
//        /* Collections.copy() */
//        System.out.println("객체 리스트 - copy()");
//        print(true, sources);
//        List<MutableObject> targetCopy = new ArrayList<>();
//        targetCopy.add(new MutableObject(0)); // 더미 객체로 메모리 확보
//        targetCopy.add(new MutableObject(0));
//        targetCopy.add(new MutableObject(0));
//        targetCopy.add(new MutableObject(0));
//
//        // 메모리 용량이 미리 확보되어 있어야 해서 때문에 클론 생성 용도로 쓰기 힘들다
//        Collections.copy(targetCopy, sources);
//
//        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
//        targetCopy.add(new MutableObject(10));
//
//        print(true, sources);
//        print(false, targetCopy);
//        System.out.println("--------------------------------------\n");
//
//        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
//        targetCopy.get(1).setX(50);
//
//        print(true, sources);
//        print(false, targetCopy);
//
//        targetCopy.get(1).setX(2); // 원상복구
//        System.out.println("--------------------------------------\n");
//
//
//        /* Collections.addAll() */
//        System.out.println("객체 리스트 - addAll()");
//        print(true, sources);
//        List<MutableObject> targetAddAll = new ArrayList<>();
//        targetAddAll.addAll(sources);
//
//        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
//        targetAddAll.add(new MutableObject(300));
//
//        print(true, sources);
//        print(false, targetAddAll);
//        System.out.println("--------------------------------------\n");
//
//        System.out.println("2. 복사본 객체 변경 후(값 변경됨)");
//        targetAddAll.get(2).setX(20);
//
//        print(true, sources);
//        print(false, targetAddAll);
//
//        sources.get(2).setX(3); // 원상복구
//        System.out.println("--------------------------------------\n");
//
//
//        /* with stream */
//        System.out.println("객체 리스트 - stream() 이용)");
//        print(true, sources);
//        List<MutableObject> targetStream;
//        targetStream = sources.stream().collect(Collectors.toList());
//
//        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
//        targetStream.add(new MutableObject(500));
//
//        print(true, sources);
//        print(false, targetStream);
//        System.out.println("--------------------------------------\n");
//
//        System.out.println("2. 복사본 객체 변경 후(얕은 복사가 됨)");
//        targetStream.get(0).setX(50);
//
//        print(true, sources);
//        print(false, targetStream);
//
//        sources.get(0).setX(1); // 원상복구
//        System.out.println("--------------------------------------\n");
//
//
//        /* direct copy */
//        System.out.println("객체 리스트 - 직접 복사");
//        print(true, sources);
//        List<MutableObject> targetDirect = new ArrayList<>();
//        for (MutableObject source : sources) {
//            targetDirect.add(new MutableObject(source.x));
//        }
//        System.out.println("1. 복사본에 객체 추가 후(깊은 복사?)");
//        targetDirect.add(new MutableObject(50));
//
//        print(true, sources);
//        print(false, targetDirect);
//
//        System.out.println("2. 복사본 객체 변경 후(깊은 복사!)");
//        targetDirect.get(0).setX(100);
//
//        print(true, sources);
//        print(false, targetDirect);
//        System.out.println("--------------------------------------\n");
    }

    public static <T> String toCustomString(List<?> T) {
        StringBuilder listStr = new StringBuilder("[");
        for (int i = 0; i < T.size(); i++) {
            listStr.append(T.get(i).toString());
            if (i == T.size() - 1) {
                listStr.append("]");
            } else {
                listStr.append(", ");
            }
        }

        return listStr.toString();
    }

    public static void print(Object T) {
        if (T instanceof List<?>) {
            System.out.println(toCustomString((List<?>) T));
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
}
