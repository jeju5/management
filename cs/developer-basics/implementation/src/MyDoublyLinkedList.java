public class MyDoublyLinkedList<T> {

    private class Node<T>{
        Node prev;
        Node next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private int size;
    private Node head;
}