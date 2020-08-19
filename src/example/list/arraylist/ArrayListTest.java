package example.list.arraylist;

import example.MutableObject;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static example.configuration.PrintConfig.print;

public class ArrayListTest {
    public static void main(String[] args) {
        // List<int> originNumList; // primitive 형은 List의 요소가 될 수 없다.
        // List<Integer> originNumList = new List<>(); // List는 Interfate이다.
        List<Number> originNumList = new ArrayList<>(); // 숫자를 요소로 사용하려면 Wrapper class로 감싸면 된다.
        ArrayList<Integer> originIntArrayList = new ArrayList<>(); // 좀 더 구체적인 선언방법
        List<String> originStrList = new ArrayList<>();
        List<MutableObject> originMutableObjList = new ArrayList<MutableObject>(){{
            add(new MutableObject(5));
            add(new MutableObject(15));
        }};

        /** [rt.jar] java.util.ArrayList.class
         * ArrayList
         * 데이터를 *배열로 관리*하며, 순서에 변화가 일어나면 새로운 배열로 데이터를 *복사*하여 해결한다.
         * 배열로 관리하기 때문에 배열의 인덱스가 갖는 장점을 그대로 가지고 있다.
         * 순서에 변동이 생길 때 마다 새 배열을 만들어 복사하는 작업이 반복되므로
         * 이런 작업이 빈번하다면 성능 저하가 생길 수 있다.
         * */

        /* 복사본으로 테스트하기 위하여 복사 기능부터 테스트(하단) */
        /* Collection은 깊은 복사를 지원하지 않으므로 직접 구현해야 한다.(하단) */


        /** 자바에서 제공하는 ArrayList 메서드 */

        /* add, addAll */
        originNumList.add(1); // Integer 형으로 바뀌어 들어간다(Boxing).
        originNumList.add(3);
        originNumList.add(5);

        originStrList.add("Java");
        originStrList.add("Data");
        originStrList.add("Structure");

        originMutableObjList.add(new MutableObject(10));
        // 인덱스를 지정하지 않은 경우에는 성공여부를 반환한다.
        System.out.println("추가 성공? " + originMutableObjList.add(new MutableObject(30)));
        originMutableObjList.add(1, new MutableObject(50)); // 1번 인덱스에 삽입된다.

        print(originNumList);
        print(originStrList);
        print(originMutableObjList);

        /* 사용할 리스트 복사 */
        // 불변 객체를 요소로 하면 얕은 복사로 테스트해도 된다.
        List<Number> numList = new ArrayList<>(originNumList);
        List<String> strList = new ArrayList<>(originStrList);

        // 불변 객체는 직접 복사를 해준다.
        List<MutableObject> mutableObjList = new ArrayList<>();
        for (MutableObject obj : originMutableObjList) {
            mutableObjList.add(new MutableObject(obj.x));
        }

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

        /* hashCode */
        // Collection에서 기본으로 제공하는 메서드이다.
        // hash값이 필요한 Collection에서 주로 사용한다.
        // 객체가 같은지 비교하는 용도이며, 오버라이딩 하지 않았을 경우 기본적으로 객체의 메모리 주소를 반환한다.
        System.out.println(strList.hashCode());

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

        Iterator<String> iterator1 = strList.iterator();

        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + ", ");
        }

        ListIterator<String> listIterator2 = strList.listIterator(2);
        System.out.println();

        while (listIterator2.hasPrevious()) {
            System.out.print(listIterator2.previousIndex() + ": " + listIterator2.previous() + ", ");
        }
        System.out.println();

        listIterator2 = strList.listIterator(2);

        while (listIterator2.hasNext()) {
            System.out.print(listIterator2.nextIndex() + ": " + listIterator2.next() + ", ");
        }
        System.out.println();





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
        System.out.println(strList.set(1, "Collection"));
        strList.set(2, "Framework");

        print(strList); // [Java, Collection, Framework]


        /* clear */
        strList.clear();

        System.out.printf("strList의 크기(길이): %d\n", strList.size());
        print(strList); // []

        // Shallow copy
        strList = new ArrayList<>(originStrList);


        /* remove, removeAll */

        // 1. index로 제거하기
        strList.remove(2);
        print(strList); // [Java, Data]

        // 2. 객체로 제거하기
        strList.remove("Java");
        print(strList); // [Data]

        strList.add("Hello");
        strList.add("Language");
        strList.add("Data");

        // 2-1. 같은 객체가 여러개 일 경우
        strList.remove("Data");

        // 제일 앞에 나오는 하나만 제거된다.
        print(strList); // [Hello, Language, Data]

        // removeAll
        // 같은 객체를 여러개 지우는 데 쓰는 메서드가 아니다.
        List<String> languageList = new ArrayList<>();
        languageList.add("Java");
        languageList.add("C");
        languageList.add("C++");

        strList.add("C");

        print(strList); // [Hello, Language, Data, C]

        // languageList에 포함된 것 중에 같은 것이 있으면 삭제한다. (차집합)
        strList.removeAll(languageList);

        // "C" 가 발견되어 제거되었다.
        System.out.print("removeAll(str) if str is element of languageList: ");
        print(strList); // [Hello, Language, Data]


        /* removeIf */
        strList.add("Java");
        strList.add("Data");
        strList.add("Structure");
        strList.add("Data");
        strList.add("C");
        strList.add("C#");
        strList.add("C++");

        strList.removeIf(str -> str.equals("Data"));
        System.out.print("removeIf str equals \"Data\": ");
        print(strList); // [Hello, Language, Java, Structure, C, C#, C++]

        strList.removeIf(Predicate.isEqual("Hello").or(Predicate.isEqual("Language")));
        print(strList); // [Java, Structure, C, C#, C++]

        strList.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("C");
            }
        });
        print(strList); // [Java, Structure]


        /* retainAll */
        // Shallow copy로 초기화
        strList = new ArrayList<>(originStrList);

        // 파라미터로 받은 컬렉션에 포함된 객체와 같은 것만 남긴다. (교집합)
        strList.retainAll(languageList);
        print(strList); // [Java]


        /* isEmpty */
        // Shallow copy
        strList = new ArrayList<>(originStrList);
        System.out.println("Is strList emtpy?: " + (strList.isEmpty() ? "true" : "false"));


        /* contains, containsAll */
        System.out.println("Does strList have Java?: " +  (strList.contains("Java") ? "true" : "false"));
        System.out.println("Does strList have all elements of languageList?: " +  (strList.containsAll(languageList) ? "true" : "false"));


        /* subList */
        // 첫 번쨰 파라미터의 인덱스부터 두 번쨰 파라미터의 인덱스 전의 요소까지 잘라낸다.
        // (0, 2) 이면 0번쨰부터 1번째(2번째 직전) 까지 추출한다.
        // print(strList.subList(0, 2)); // [Java, Data]
        strList.subList(0, 2).forEach(System.out::println);
        print(strList);


        /* toArray */
        // String[] strArray = (String[]) strList.toArray(); // Error
        // String[] 배열에 리스트를 복사하고 싶으면 파라미터로 타겟 배열을 전달한다.
        Object[] strArray = strList.toArray();
        System.out.println("toArray()");
        for (Object obj: strArray) {
            System.out.println(obj);
        }

        // String[] strArray2 = new String[strList.size()]; // 리스트 사이즈만큼의 길이를 가진 배열 생성
        // strList.toArray(strArray2);

        String[] strArray2 = strList.toArray(new String[0]); // 한줄로 가장 간결하게 하는 방법

        System.out.println("toArray(String[])");
        for (String str: strArray2) {
            System.out.println(str);
        }

        // 반대로 배열에서 리스트로 변환할 때 Arrays.asList 메서드를 사용할 수 있다.
        // 단, ArrayList가 아니므로 추가 연산이 필요하면 ArrayList의 복사 생성자를 이용한다.
        List<String> newStrList = Arrays.asList(strArray2); // ArrayList가 아님
        // newStrList.add("C"); // Error

        List<String> newStrList2 = new ArrayList<>(Arrays.asList(strArray2));
        newStrList2.add("C");
        print(newStrList2);


        /* replaceAll */
        strList.replaceAll(str -> str += "(replaced)");
        print(strList);


        // 제거
        strList.replaceAll(str -> str.replaceAll("\\(replaced\\)", ""));
        print(strList);

        strList.replaceAll(s -> s + "-replaced");

        print(strList);

        /* sort */

        // 1. Comparable 인터페이스를 구현하여 compareTo 메서드 오버라이드
        // 2. Comparator 인터페이스를 익명으로 구현하여 compare 메서드 오버라이드
        Collections.sort(strList, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });

        // 3. compareTo 메서드를 람다식으로 호출
        Collections.sort(strList, (str1, str2) -> str1.compareTo(str2));

        // 4. sort 메서드를 list에서 호출
        strList.sort((str1, str2) -> str1.compareTo(str2));

        // 5. compareTo 메서드를 double colon으로 호출
        strList.sort(String::compareTo);

        print(strList);

        print(true, originStrList);


        /* java 8 */

        intList.add(40);
        intList.add(50);
        intList.add(60);
        intList.add(70);
        intList.add(80);
        intList.add(90);

        print(intList);

        /* spliterator */

        // intList.sort(Collections.reverseOrder()); // 역순 정렬
        Spliterator<Integer> integerSpliterator = intList.spliterator();


        // 위에서 만든 spliterator 나누기
        Spliterator<Integer> integerSpliterator2 = integerSpliterator.trySplit();

        // size 체크
        System.out.println("순회 전 Size 체크");
        System.out.println(integerSpliterator.estimateSize());
        System.out.println(integerSpliterator.getExactSizeIfKnown()); // SIZED 특성을 가질 때 estimateSize() 대신 사용가능

        // 순회 출력
        // 순회되지 않은 요소들에 대한 순회
        integerSpliterator.forEachRemaining(System.out::println); // 50 40 30 20 10
        System.out.println("-----------");
        integerSpliterator2.forEachRemaining(System.out::println); // 90 80 70 60
        // 순회되지 않은 요소에 대한 체크 후 액션
        integerSpliterator.tryAdvance(System.out::println); // 아무일도 일어나지 않음(순회 완료된 Spliterator)
        integerSpliterator2.tryAdvance(System.out::println); // 90
        integerSpliterator2.tryAdvance(System.out::println); // 80

        // 순회 후 size 체크
        System.out.println("순회 후 Size 체크");
        System.out.println(integerSpliterator.estimateSize()); // 순회가 끝나면 0이다
        System.out.println(integerSpliterator.getExactSizeIfKnown());

        // 사용된 Comparator 체크
        // System.out.println(integerSpliterator.getComparator()); // Error. SORTED 속성이 필요하다.

        // Integer Spliterator 특성 체크
        if (integerSpliterator.hasCharacteristics(Spliterator.ORDERED)) {
            System.out.println("ORDERED");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.DISTINCT)) {
            System.out.println("DISTINCT");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.SORTED)) {
            System.out.println("SORTED");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.SIZED)) {
            System.out.println("SIZED");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.CONCURRENT)) {
            System.out.println("CONCURRENT");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.IMMUTABLE)) {
            System.out.println("IMMUTABLE");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.NONNULL)) {
            System.out.println("NONNULL");
        }

        if (integerSpliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            System.out.println("SUBSIZED");
        }

        /* copy */

        copyTest();
        
    }

    public static void copyTest() {
        List<String> strList = new ArrayList<>();

        strList.add("Java");
        strList.add("Data");
        strList.add("Structure");

        // List<String> newStrList = strList;
        // List<String> newStrList = new ArrayList<>(strList);
        // List<String> newStrList = new ArrayList<String>(){{
        //     add("");
        //     add("");
        //     add("");
        // }};
        // Collections.copy(newStrList, strList);

        // List<String> newStrList = new ArrayList<>();
        // newStrList.addAll(strList);

        // List<String> newStrList = strList.stream().collect(Collectors.toList());

        List<String> newStrList = new ArrayList<>();

        strList.forEach(newStrList::add);

        newStrList.remove(2);

        print(true, strList);
        print(false, newStrList);

        List<MutableObject> mutableObjectList = new ArrayList<>();
        mutableObjectList.add(new MutableObject(0));
        mutableObjectList.add(new MutableObject(1));
        mutableObjectList.add(new MutableObject(2));
        mutableObjectList.add(new MutableObject(3));
        mutableObjectList.add(new MutableObject(4));

        List<MutableObject> newMutableObjectList = new ArrayList<>(mutableObjectList);
        newMutableObjectList.get(0).setX(10);

        mutableObjectList.forEach(obj -> System.out.println(obj.x));
        newMutableObjectList.forEach(obj -> System.out.println(obj.x));




        /* object copy (가변 객체) */
        List<MutableObject> sources = new ArrayList<>();
        sources.add(new MutableObject(1));
        sources.add(new MutableObject(2));
        sources.add(new MutableObject(3));
        sources.add(new MutableObject(4));


        // reference(shallow copy)
        System.out.println("객체 리스트 - 참조");
        print(true, sources);
        List<MutableObject> targetRef = sources;

        targetRef.add(new MutableObject(50));
        System.out.println("복사본 변경 후(얕은 복사)");
        print(true, sources);
        print(false, targetRef);
        sources.remove(sources.size() - 1); // 추가했던 객체 제거
        System.out.println("--------------------------------------\n");



        // copy constructor
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


        // Collections.copy()
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


        // Collections.addAll()
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


        // with stream
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


        // direct copy
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
}
