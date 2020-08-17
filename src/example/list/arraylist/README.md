# List - ArrayList
## ArrayList의 특징
* 배열처럼 관리한다.
    * 순서에 변화가 일어나면 배열의 복사 연산이 일어난다.
        * 변화가 잦으면 성능 저하의 원인이 되므로 다른 Collection을 알아보는게 좋다.
* 인덱스를 가진다.
    * 인덱스로 요소에 접근할 수 있어서 편리하다.
    
## basic branch
### void main()
#### ArrayList 생성
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

#### `add`
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

#### `addAll`
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

#### `size`
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
#### `get`
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
#### `hashCode`
Collection 공통 메서드로, 객체끼리 비교할 때 사용되는 값이다.
오버라이드 하지 않으면 객체의 메모리 주소를 반환한다. 
* return type: int
* parameter: 없음

#### `iterator`
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
#### `listIterator`
ListIterator 인터페이스는 Iterator 인터페이스를 확장한 후 `hasPrevious()`와 `previous()`도 구현하여 역순회도 가능하도록 한 인터페이스이다.

##### ListIterator 인덱스 계산할 때 주의할 점
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
#### `indexOf`, `lastIndexOf`
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

#### `set`
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

#### `clear`
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

#### `remove`
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
#### `removeIf` (java 8)
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

#### `removeAll`
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

#### `retainAll`
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
#### `isEmpty`
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
#### `contains`
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

#### `containsAll`
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

#### `subList`
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

#### `toArray`
#### `replaceAll`
#### `spliterator` (java 8)

#### sort


### custom method
#### copyTest
##### 얕은 복사(Shallow copy)
* 참조
* 복사 생성자
* Collections.copy()
* Collections.addAll();
* Stream 이용
##### 깊은 복사(Deep copy)
* 직접 복사