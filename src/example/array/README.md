# Array
## basic branch
### void main()
### method
#### print
* return type: `void`
* parameter
    1. `Boolean` 두 번째 파라미터로 들어온 객체가 Original인지 Copy인지 묻는 파라미터 (Optional)
    2. `Array` 콘솔에 출력할 배열
        * `int[]`
        * `int[][]`
        * `String[]`
        * `String[][]`
        * `Object[]` (MutableObject.class)
* exception: `IllegalArgumentException`

배열을 보기 좋은 형태로 콘솔에 출력한다. 객체(배열)의 타입에 따라 출력하는 모양이 바뀐다.
첫 번째 파라미터를 이용하여 출력할 객체가 원본인지 복사본인지 구별하는 레이블을 추가할 수 있다.

#### toCustomString
* return type: `String`
* parameter
    1. `Array`
        * `int[]`
        * `int[][]`
        * `String[]`
        * `String[][]`
        * `Object[]` (MutableObject.class)

1차원 `int`, `String` 배열이면 수평으로 나열된 모습으로 출력한다.  
2차원 `int`, `String` 배열이면 행마다 다른 줄에 출력한다.  
1차원 `MutableObject` 배열이면 *item* label을 추가하여 다른 줄에 출력한다.

#### add
* return type: void
* parameter
    1. int[]
    2. int
    
배열의 마지막 요소 다음에 값을 추가한다.
첫 번째 파라미터로 추가 대상이 되는 배열을 받고, 두 번째 파라미터로 추가할 `int` 값을 받는다.

#### insert
* return type: void
* parameter
    1. int[]
    2. int
    3. int

배열의 원하는 인덱스에 값을 삽입한다.
첫 번째 파라미터로 추가 대상이 되는 배열을 받고, 두 번째 파라미터로 추가할 위치의 인덱스를 받는다.
세 번째 파라미터로 추가할 `int` 값을 받는다.

#### remove(int[], int)
* return type: void
* parameter
    1. int[]
    2. int
    
해당 인덱스에 있는 요소의 값을 삭제한다.
첫 번째 파라미터로 배열을 받고, 두 번째 파라미터로 인덱스를 받는다.

#### equals(int[], int[])
* return type: boolean
* parameter
    1. int[]
    2. int[]
    
파라미터로 받은 두 개의 1차원 `int` 배열이 같은지 비교한다. 저장된 순서(인덱스)도 같아야 한다.
같으면 `true`, 다르면 `false`를 반환한다.

#### deepcopy(int[])
* return type: int[]
* parameter
    1. int[]

1차원 배열을 복사하여 반환한다.

#### deepcopy2d(int[][])
* return type: int[][]
* parameter
    1. int[][]

2차원 배열을 복사하여 반환한다.