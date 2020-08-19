# List - ArrayList
## ArrayList의 특징
* 배열처럼 관리한다.
    * 순서에 변화가 일어나면 배열의 복사 연산이 일어난다.
        * 변화가 잦으면 성능 저하의 원인이 되므로 다른 Collection을 알아보는게 좋다.
* 인덱스를 가진다.
    * 인덱스로 요소에 접근할 수 있어서 편리하다.
    
## basic branch
### ArrayList 생성
* Primitive Type은 List의 요소가 될 수 없다.
    * Wrapper class를 이용하여 List로 만들 수 있다.
```$xslt
List<Number> originNumList; // 선언
List<Number> originNumList2 = new ArrayList<>(); 선언 후 인스턴스 주소 할당(대중적인 방법)
ArrayList<Integer> originIntList = new ArrayList<>(); // 명시적이지만 5글자 손해
List<MutableObject> originMutableObjList = new ArrayList<MutableObject>{{
    add(new MutableObject(5));
    add(new MutableObject(15));
}}; // 객체를 리스트의 요소로 선언하며 동시에 원하는 값으로 초기화(지저분하다)
```

### `add`
리스트의 끝에 요소를 추가한다. 성공 시 `true`를 반환하며, 아니면 `false`를 반환한다.
Primitive type을 파라미터로 전달하면 Wrapper Class로 자동 변환되어 추가되며, 해당 작업이 많아질 경우 성능 저하의 원인이 된다. (Boxing, Unboxing)
* return type: boolean
* parameter:
    1. E (요소로 올 수 있는 모든 객체)  

```$xslt
List<Number> numList = new ArrayList<>();

numList.add(1); // int 1이 Number 1로 자동 변환됨(Boxing) - 성능저하의 원인
numList.add(3);
numList.add(5);

```

### `addAll`
`add()`와 마찬가지로 성공 시 `true`, 아니면 `false`를 반환한다.
단, Collection을 파라미터로 받으며, 추가할 Collection의 요소가 기존 Collection의 요소를 상속받은 객체여도 추가할 수 있다.
* return type: boolean
* parameter:
    1. Collection<? extends E>
```$xslt
List<Number> numList = new ArrayList<>();

numList.add(1); // int 1이 Number 1로 자동 변환됨(Boxing) - 성능저하의 원인
numList.add(3);
numList.add(5);

List<Integer> intList = new ArrayList<>();
List<Float> floatList = new ArrayList<>();

intList.add(10);
intList.add(20);
intList.add(30);

floatList.add(1.2f);
floatList.add(1.5F);

numList.addAll(intList); // Integer는 Number의 확장 클래스
numList.addAll(floatList); // Float도 Number의 확장 클래스

numList.forEach(System.out::println); // 1 3 5 10 20 30 1.2 1.5
```

### `size`
리스트의 크기(길이)를 반환한다. 크기가 integer의 범위를 벗어나면 integer의 최대값(Integer.MAX_VALUE)을 반환한다.
* return type: int  
* parameter: 없음
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.size()); // 3
```
### `get`
어떤 인덱스에 위치한 요소를 얻고자 할 때 사용한다.
* return type: E -해당 인덱스에 위치한 요소의 타입
* parameter: int - 인덱스
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.get(1)); // Data
```
### `hashCode`
Collection 공통 메서드로, 객체끼리 비교할 때 사용되는 값이다.
오버라이드 하지 않으면 객체의 메모리 주소를 반환한다. 
* return type: int
* parameter: 없음

### `iterator`
시퀀스 형태의 Collection의 요소를 순회할 때 사용하는 인터페이스 `Iterator` 를 반환한다.
Iterator 인터페이스의 `hasNext()`메서드로 다음 값이 있는지 판단하며 `next()` 메서드로 요소의 내용을 얻는다.  
* return type: Iterator<E>
* parameter: 없음
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

Iterator<String> iterator = strList.iterator();

while (iterator.hasNext()) {
    System.out.print(iterator.next() + ", "); // Java, Data, Structure,
}
System.out.println();
```
### `listIterator`
ListIterator 인터페이스는 Iterator 인터페이스를 확장한 후 `hasPrevious()`와 `previous()`도 구현하여 역순회도 가능하도록 한 인터페이스이다.

#### ListIterator 인덱스 계산할 때 주의할 점
> index가 2로 초기화된 ListIterator의 `previous()`가 가리키는 값은 List의 1번 인덱스의 요소이다.
그러나 `next()`가 가리키는 값은 List의 3번 인덱스의 요소가 아니라 2번 인덱스의 요소이다.
> 정의를 보면 ListIterator의 index 파라미터가 가리키는 요소는 **next()로 불러올 요소** 라고 명시되어 있다.
> 주관이지만, Iterator의 위치는 현재 인덱스와 이전 인덱스의 요소 사이에 위치한다고 생각하면 편할 것 같다.

* return type: Iterator<E>
* parameter: int - 인덱스(Optional, 아무것도 주지 않으면 0으로 계산)
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

ListIterator<String> listIterator = strList.listIterator(2); // 1번째 요소와 2번째 요소 [사이]로 초기화

while (listIterator.hasPrevious()) {
    System.out.print(listIterator.previousIndex() + ": " + listIterator.previous() + ", "); // 1: Data, 0: Java, 
}
System.out.println();

listIterator = strList.listIterator(2);

while (listIterator.hasNext()) {
    System.out.print(listIterator.nextIndex() + ": " + listIterator.next() + ", "); // 2: Structure, 
}
System.out.println();
```
### `indexOf`, `lastIndexOf`
찾고자 하는 객체가 있는 인덱스를 반환한다.
`indexOf()`는 앞에서부터 검색하여 처음 찾은 위치의 인덱스를, `lastIndexOf()`는 뒤에서부터 검색한 결과를 반환한다.
* return type: int - 인덱스
* parameter: Object

```$xslt
List<Number> numList = new ArrayList<>();

numList.add(1);
numList.add(3);
numList.add(5);
numList.add(1.5f);
numList.add(10);
numList.add(30);
numList.add(50);
numList.add(1.5f);

System.out.println(numList.indexOf(1.5f)); // 3
System.out.println(numList.lastIndexOf(1.5f)); // 7
```

### `set`
특정 인덱스에 위치한 요소를 원하는 요소로 교체한다.
* return type: E - 수정되기 전의 요소
* parameter:
    1. int - 인덱스
    2. E - 교체할 요소
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.set(1, "Collection")); // Data
strList.set(2, "Framework");

strList.forEach(System.out::println); // Java Collection Framework

```

### `clear`
리스트를 비운다.
* return type: void
* parameter: 없음
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.size()); // 3

strList.clear();

System.out.println(strList.size()); // 0

```

### `remove`
특정한 위치에 있는 요소를 제거한다.
1. E remove(int index)
    * return type: E - 제거될 요소
    * parameter:
        1. int - 제거할 요소가 있는 위치의 인덱스
2. boolean remove(Object o)
    * return type: boolean - 리스트에 제거할 요소가 존재하면 `true`, 아니면 `false`를 반환
    * parameter:
        1. Object - 제거할 객체
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

strList.remove(0);
strList.remove("Structure");

strList.forEach(System.out::println); // Data

```
### `removeIf` (java 8)
리스트를 순회하여 특정 조건에 맞는 요소를 만날 때마다 제거한다.
* return type: boolean - 1개 이상 제거되면 `true`, 아니면 `false`를 반환
* parameter: Predicate<? super E> - filter 역할을 할 함수
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");
strList.add("Data");
strList.add("C");
strList.add("C#");
strList.add("C++");

strList.removeIf(str -> str.equals("Data"));

strList.forEach(System.out::println); // Java Structure C C# C++

strList.removeIf(Predicate.isEqual("C").or(Predicate.isEqual("Structure"))); // Predicate의 default 메서드인 or 이용

strList.forEach(System.out::println); // Java C# C++

strList.removeIf(new Predicate<String>() {
    @Override
    public boolean test(String s) {
        return s.contains("C"); // 'C'가 포함된 요소 제거
    }
});

strList.forEach(System.out::println); // Java
```

### `removeAll`
파라미터로 받은 Collection에 포함된 요소가 리스트에 존재할 경우 모두 제거한다. (차집합의 개념)
* return type: boolean - 연산 결과로 리스트에 변화가 생기면 `true`, 아니면 `false` 반환
* parameter: Collection<?> - 리스트에서 제거하고 싶은 요소를 포함한 Collection
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> langList = new ArrayList<>();
langList.add("Java");
langList.add("C");
langList.add("C++");

strList.removeAll(langList);

strList.forEach(System.out::println); // Data Structure

```

### `retainAll`
파라미터로 받은 Collection에 포함된 요소가 리스트에 있으면 남기고 나머지는 모두 제거한다. (교집합의 개념)
* return type: boolean - 연산 결과로 리스트에 변화가 생기면 `true`, 아니면 `false` 반환
* parameter: Collection<?> - 리스트에서 남기고 싶은 요소를 포함한 Collection
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> langList = new ArrayList<>();
langList.add("Java");
langList.add("C");
langList.add("C++");

strList.retainAll(langList);

strList.forEach(System.out::println); // Java

```
### `isEmpty`
리스트가 비어있는지 아닌지 판별한다.
* return type: boolean - 리스트가 비어있으면 `true`, 아니면 `false` 반환
* parameter: 없음
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.isEmpty() ? "true" : "false"); // false

strList.clear();

System.out.println(strList.isEmpty() ? "true" : "false"); // true

```
### `contains`
어떤 객체가 리스트에 포함되어있는지 판단한다.
* return type: boolean - 파라미터로 받은 객체가 리스트에 존재하면 `true`, 아니면 `false` 반환
* parameter: Object
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

System.out.println(strList.contains("Java") ? "true" : "false"); // true

```

### `containsAll`
Collection의 요소들이 모두 리스트에 포함되어있는지 판단한다.
* return type: boolean - 파라미터로 받은 Collection의 요소 모두가 리스트에 존재하면 `true`, 아니면 `false` 반환
* parameter: Collection<?>
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> dataStructure = new ArrayList<>();
dataStructure.add("Data");
dataStructure.add("Structure");

System.out.println(strList1.containsAll(dataStructure) ? "true" : "false"); // true

```

### `subList`
첫 번째 파라미터의 인덱스부터 두 번째 파라미터의 인덱스 직전까지의 리스트를 잘라내어 반환한다.  
원본은 유지되며, 잘라낸 리스트도 리스트의 연산을 모두 할 수 있다. 
* return type: List<E> - 잘라낸 리스트
* parameter:
    1. int - 잘라낼 리스트의 시작 인덱스(연산에 포함됨(inclusive))
    2. int - 잘라낼 리스트의 마지막 인덱스(제외하고 이전 인덱스까지만 포함 한 후 연산됨(exclusive))
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

strList.subList(0, 2).forEach(System.out::println); // Java Data
strList.forEach(System.out::println); // Java Data Structure

```

### `toArray`

1. Object[] toArray()
    * return type: Object[] - Object를 요소로 하는 배열을 반환한다.
    * parameter: 없음
2. T[] toArray(T[] a)
    * return type: T[] - 파라미터로 받은 배열의 요소 타입과 같은 요소 타입을 가진 배열을 반환한다.
    * parameter: T[]

```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

Object[] objArray = strList.toArray();
for (Object obj: objArray) {
    System.out.println(obj); // Java Data Structure
}

String[] strArray = strList.toArray(new String[0]);
for (String str: strArray) {
    System.out.println(str); // Java Data Structure
}

```
### `replaceAll`
리스트의 각 요소에 대하여 연산 결과로 교체한다.
* return type: void
* parameter:
    1. UnaryOperator<E> operator - 연산 식(람다식)
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

strList.replaceAll(str -> str += "(replaced)");

strList.forEach(System.out::println); // Java(replaced) Data(replaced) Structure(replaced)

```

### `spliterator` (java 8)
객체가 배치된 구역을 나누어 순회할 수 있게 설계된 `Spliterator` 인터페이스에 의에 구현된다. 순차적으로 하나씩 순회하는
`tryAdvance()` 메서드와 모두 순회하는 `forEachRemaining()`메서드로 접근할 수 있다.  
Collection의 특징을 정의하고 있어, 특징에 따라 사용할 수 있는 메서드가 다르다. 어떤 특징이 있는지 알아보려면 `characteristics()`
메서드를 이용한다.  
`trySplit()`에 의해 나누어진 각 `Spliterator`는 병렬 연산을 적용할 수 있다.
* return type: Spliterator<E>
* parameter: 없음
```$xslt
List<Integer> intList = new ArrayList<>();

intList.add(10);
intList.add(20);
intList.add(30);
intList.add(40);
intList.add(50);
intList.add(60);
intList.add(70);
intList.add(80);
intList.add(90);

Spliterator<Integer> intSpliterator = intList.spliterator();
Spliterator<Integer> splitted = intSpliterator.trySplit();

intSpliterator.forEachRemaining(System.out::println); // 50 60 70 80 90
// splitted.forEachRemaining(System.out::println); // 10 20 30 40

splitted.tryAdvance(System.out::println); // 10
splitted.tryAdvance(System.out::println); // 20

```

### sort


### copy
#### 얕은 복사(Shallow copy)
원본이 손상되는걸 막기 위해, 복사본을 이용하여 각종 연산을 수행하는 것이 안전하다. Collection은 복사를 위한
여러가지 메서드를 지원한다. 하지만 Mutable 요소를 가지는 Collection에 대해서는 복사본 객체의 내용 변경에 따라
원본 내용도 변경되어 버리며, 이것을 얕은 복사라고 한다.

##### 참조
얕은 복사의 대표적인 방법.  
> List의 레퍼런스 변수(strList)는 인스턴스(실제 리스트)의 메모리 주소를 가지고 있을 뿐이고,
`=`으로 새 레퍼런스 변수(newStrList)에 넘겨주는 값도 결국 기존 인스턴스의 메모리 주소이다.
결국, strList로 기존 인스턴스에 접근하는 경우와 newStrList로 기존 인스턴스에 접근하는 경우의
2가지 방법이 생기는 것이다. 그래서 어떤 레퍼런스 변수를 이용해도 원본이 바뀌어 버리게 되며,
 기대했던 것과 다른 동작을 하게 된다.
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> newStrList = strList; // 기존 인스턴스의 메모리 주소를 할당한다.

newStrList.remove(2);

strList.forEach(System.out::println); // Java Data
newStrList.forEach(System.out::println); // Java Data

```
##### 복사 생성자
`ArrayList`의 생성 시 파라미터로 레퍼런스 변수를 전달하는 방법이다.  
리스트의 요소가 Immutable일 시 깊은 복사처럼 사용할 수 있는 방법이다.
Mutable 객체를 요소로 가지게 되면, 요소의 값 변화시 원본도 바뀌는 얕은 복사가 되므로
주의해서 사용해야 한다.  
그리고 이후에 테스트 할 copy방법은 direct copy를 제외하면 Mutable List에 대해 모두 얕은 복사가 적용되므로
테스트 코드는 생략한다.
* case 1 - 불변 객체를 요소로 하는 경우
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> newStrList = new ArrayList<>(strList);

newStrList.remove(2);

strList.forEach(System.out::println); // Java Data Structure
newStrList.forEach(System.out::println); // Java Data

```
* case 2 - 가변 객체를 요소로 하는 경우
```$xslt
List<MutableObject> mutableObjectList = new ArrayList<>();

mutableObjectList.add(new MutableObject(0));
mutableObjectList.add(new MutableObject(1));
mutableObjectList.add(new MutableObject(2));
mutableObjectList.add(new MutableObject(3));
mutableObjectList.add(new MutableObject(4));

List<MutableObject> newMutableObjectList = new ArrayList<>(mutableObjectList);
newMutableObjectList.get(0).setX(10); // 사본 객체의 필드 변경

mutableObjectList.forEach(obj -> System.out.println(obj.x)); // 10 1 2 3 4 (원본 변경됨)
newMutableObjectList.forEach(obj -> System.out.println(obj.x)); // 10 1 2 3 4
```

##### Collections.copy()
`Collections`에서 제공하는 복사 메서드이다. 단, 복사될 리스트의 용량(Size가 아닌 Capacity)이 확보되어 있어야 하기 때문에
새로운 클론을 만드는 용도로는 사용하기 힘들다.  
첫 번째 파라미터로 target list를, 두 번째 파라미터로 source list를 넘겨준다.  
Collection.copy()를 이용한 복사는 Mutable 요소의 List의 경우 얕은 복사가 적용된다.
> Java에서는 객체가 차지하는 메모리를 알아내기가 쉽지 않으므로 복사할 리스트의 객체 수만큼 더미 객체를 만들어서
>추가 해 두는 것이 좋다.
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

List<String> newStrList = new ArrayList<>();

newStrList.add("");
newStrList.add("");
newStrList.add("");

Collections.copy(newStrList, strList);

newStrList.remove(2);

strList.forEach(System.out::println); // Java Data Structure
newStrList.forEach(System.out::println); // Java Data

```
##### Collections.addAll()

```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");
```
##### Stream 이용

```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");
```
#### 깊은 복사(Deep copy)
Collection을 완벽하게 복사하는 메서드는 구현되어있지 않으므로
객체 참조의 참조까지 완전히 복사하려면 하나하나 똑같이 생성하는 수밖에 없다.

#####직접 복사
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");
```