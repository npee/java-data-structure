package example.list.arraylist;

import example.MutableObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static example.configuration.PrintConfig.print;

public class ArrayListTest {
    public static void main(String[] args) {
        // List<int> numList; // primitive 형은 List의 요소가 될 수 없다.
        // List<Integer> numList = new List<>(); // List는 Interfate이다.
        List<Number> numList = new ArrayList<>(); // 숫자를 요소로 사용하려면 Wrapper class로 감싸면 된다.
        ArrayList<Integer> intArrayList = new ArrayList<>(); // 좀 더 구체적인 선언방법
        List<String> strList = new ArrayList<>();
        List<MutableObject> mutableObjList = new ArrayList<>();

        /** [rt.jar] java.util.ArrayList.class
         * ArrayList
         * 데이터를 *배열로 관리*하며, 순서에 변화가 일어나면 새로운 배열로 데이터를 *복사*하여 해결한다.
         * 배열로 관리하기 때문에 배열의 인덱스가 갖는 장점을 그대로 가지고 있다.
         * 순서에 변동이 생길 때 마다 새 배열을 만들어 복사하는 작업이 반복되므로
         * 이런 작업이 빈번하다면 성능 저하가 생길 수 있다.
         * */

        /** 자바에서 제공하는 ArrayList 메서드 */

        /* add, addAll */
        numList.add(1); // Integer 형으로 바뀌어 들어간다(Boxing).
        numList.add(3);
        numList.add(5);

        strList.add("Java");
        strList.add("Data");
        strList.add("Structure");

        mutableObjList.add(new MutableObject(10));
        // 인덱스를 지정하지 않은 경우에는 성공여부를 반환한다.
        System.out.println("추가 성공? " + mutableObjList.add(new MutableObject(30)));
        mutableObjList.add(1, new MutableObject(50)); // 1번 인덱스에 삽입된다.

        print(numList);
        print(strList);
        print(mutableObjList);

        List<Integer> intList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();

        intList.add(10);
        intList.add(20);
        intList.add(30);

        floatList.add(1.2f);
        floatList.add(1.5F);

        // addAll은 기존 자료형의 확장된 자료형을 요소로 하는 리스트를 더할 수 있다. (성공 여부 리턴)
        // addAll(Collections<? extends Number> c);
        numList.addAll(intList); // Integer는 Number의 확장
        numList.addAll(floatList); // Float도 Number의 확장

        print(numList); // [1, 3, 5, 10, 20, 30, 1.2, 1.5]

        /* size */
        System.out.printf("strList의 크기(길이): %d\n", strList.size());

        /* get */
        System.out.println("mutableObjList의 2번째 요소의 x값: " + mutableObjList.get(1).x);

        /* Iterator, ListIterator */
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ListIterator<Number> listIterator = numList.listIterator();
        ListIterator<Number> listIteratorByIndex = numList.listIterator(1); // 2번째 요소부터 순회한다.
        // Iterator를 확장한 ListIterator를 이용하여 리스트를 순회, 역순회 할 수 있다.
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + ", ");
            if (!listIterator.hasNext()) {
                while (listIterator.hasPrevious()) {
                    System.out.print(listIterator.previous() + ", ");
                }
                System.out.println();
                break;
            }
        }


        /* indexOf, lastIndexOf */
        numList.add(1.5f);
        print(numList); // [1, 3, 5, 10, 20, 30, 1.2, 1.5, 1.5]

        System.out.print("1.5가 처음으로 나타나는 인덱스(앞에서부터 검색): ");
        System.out.println(numList.indexOf(1.5f)); // 7

        System.out.print("1.5가 마지막으로 나타나는 인덱스(뒤에서부터 검색): ");
        System.out.println(numList.lastIndexOf(1.5f)); // 8

        // String 객체의 character나 substring의 index를 추출하는 데 유용하다.
        System.out.println("strList의 3번째 요소에서 r이 처음으로 나타나는 인덱스: ");
        System.out.println(strList.get(2).indexOf('r')); // 2

        System.out.println("strList의 3번째 요소에서 r이 마지막으로 나타나는 인덱스: ");
        System.out.println(strList.get(2).lastIndexOf('r')); // 7


        /* set */
        strList.set(1, "Collection");
        strList.set(2, "Framework");

        print(strList); // [Java, Collection, Framework]


        /* clear */
        strList.clear();

        System.out.printf("strList의 크기(길이): %d\n", strList.size());
        print(strList); // []


        /* java 8 */







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
}
