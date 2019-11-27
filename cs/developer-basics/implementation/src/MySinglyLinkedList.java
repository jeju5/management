public class MySinglyLinkedList<T> {

    private class Node<T>{
        Node next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private int size;
    private Node head;
}