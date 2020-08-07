package example.list;

import example.MutableObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {

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
        if (T instanceof List<?>) {
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
}
