# List - ArrayList
## ArrayList의 특징
* 배열처럼 관리한다.
    * 순서에 변화가 일어나면 배열의 복사 연산이 일어난다.
        * 변화가 잦으면 성능 저하의 원인이 된다.
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

#### `add`, `addAll`
리스트의 끝에 요소를 추가한다.
Primitive type을 파라미터로 전달하면 Wrapper Class로 자동 변환되어 추가되며, 해당 작업이 많아질 경우 성능 저하의 원인이 된다. (Boxing, Unboxing)
* return type: boolean  
연산의 성공 여부를 반환한다.
* parameter:
    1. E e (요소로 올 수 있는 모든 객체)  

```$xslt
List<Number> numList = new ArrayList<>();
numList.add(1); // int 1이 Number 1로 자동 변환됨(Boxing) - 성능저하의 원인
numList.add(3);
numList.add(5);
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
* return type: 해당 인덱스에 위치한 요소의 타입
* parameter: int(인덱스)
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
시퀀스 형태의 Collection의 요소를 순회할 때 사용하는 인터페이스 **iterator** 를 반환한다.
Iterator 인터페이스의 `hasNext()`메서드로 다음 값이 있는지 판단하며 `next()` 메서드로 요소의 내용을 얻는다.  
* return type: Iterator<E>
* parameter: 없음
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");



```
#### `listIterator`
ListIterator 인터페이스는 Iterator 인터페이스를 확장한 후 `hasPrevious()`와 `previous()`도 구현하여 역순회도 가능하도록 한 인터페이스이다.

##### ListIterator 인덱스 계산할 때 주의할 점
> index가 2로 초기화된 ListIterator의 `previous()`가 가리키는 값은 List의 1번 인덱스의 요소이다.
그러나 `next()`가 가리키는 값은 List의 3번 인덱스의 요소가 아니라 2번 인덱스의 요소이다.
> 정의를 보면 ListIterator의 index 파라미터가 가리키는 요소는 **next()로 불러올 요소** 라고 명시되어 있다.
> 주관이지만, Iterator의 위치는 현재 인덱스와 이전 인덱스의 요소 사이에 위치한다고 생각하면 편할 것 같다.

* return type: Iterator<E>
* parameter: int (인덱스, Optional)
```$xslt
List<String> strList = new ArrayList<>();

strList.add("Java");
strList.add("Data");
strList.add("Structure");

ListIterator<String> listIterator = strList.listIterator(2); // 1번째 요소와 2번째 요소 [사이]로 초기화

while (listIterator.hasPrevious()) {
    System.out.print(listIterator.previousIndex() + ": " + listIterator.previous() + ", ");
    // 1: Data, 0: Java, 
}
System.out.println();

listIterator = strList.listIterator(2);

while (listIterator.hasNext()) {
    System.out.print(listIterator.nextIndex() + ": " + listIterator.next() + ", ");
    // 2: Structure, 
}
System.out.println();
```
#### `indexOf`, `lastIndexOf`

#### `set`
#### `clear`
#### `remove`, `removeAll`
#### `removeIf`
#### `retainAll`
#### `isEmpty`
#### `contains`, `containsAll`
#### `subList`
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