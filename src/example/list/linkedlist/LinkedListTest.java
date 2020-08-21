package example.list.linkedlist;

import java.util.LinkedList;
import java.util.List;



public class LinkedListTest {
    public static void main(String[] args) {

        /** [rt.jar] java.util.LinkedList.class
         * ArrayList
         * public class LinkedList<E> extends AbstractSequentialList<E>
         *     implements List<E>, Deque<E>, Cloneable, java.io.Serializable
         * List와 Deque 인터페이스의 Doubly-linked list 구현체이다.
         * null을 포함한 모든 요소에 대하여 List의 모든 메서드를 사용할 수 있다.
         *
         * */

        List<String> strLinkedList = new LinkedList<>();
        strLinkedList.add("Java");
        strLinkedList.add("Data");
        strLinkedList.add("Structure");

        strLinkedList.forEach(System.out::println);

    }
}
