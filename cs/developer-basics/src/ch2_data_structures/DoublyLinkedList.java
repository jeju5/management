package ch2_data_structures;

import java.util.Arrays;

public class DoublyLinkedList {

    // Node class
    private class Node {
        // class variable
        private String data;
        private Node next;

        // arg constructor
        public Node(String string) {
            this.data = string;
            this.next = null;
        }

        // arg constructor
        public Node(String string, Node nextNode) {
            this.data = string;
            this.next = nextNode;
        }
    }

    // class variable
    private Node head;
    private int size;

    // no-arg constructor
    public DoublyLinkedList() {
        head = null;
        size = 0;
    }

    // add in the end
    public void add(String string) {
        Node node = new Node(string);

        if (head == null) {
            head = node;
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
    }

    // add in the index
    public void add(int index, String string) {
        Node nodeAtIndexMinusOne;
        Node nodeAtIndex;
        Node nodeToInsert = new Node(string);

        if (index == 0) {
            nodeAtIndex = head;
            nodeToInsert.next = nodeAtIndex;
            head = nodeToInsert;
        } else {
            nodeAtIndexMinusOne = getNode(index-1);
            nodeAtIndex = nodeAtIndexMinusOne.next;
            nodeAtIndexMinusOne.next = nodeToInsert;
            nodeToInsert.next = nodeAtIndex;
        }

        size++;
    }

    public String get(int index) {
        return getNode(index).data;
    }

    public Node getNode(int index) {
        Node node = head;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        return node;
    }

    // get index of the element
    public int getIndex(String string) {
        Node node = head;
        for (int i=0; node != null; i++) {

            if (string.equals(node.data)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    public void set(int index, String string) {
        Node node = getNode(index);
        node.data = string;
    }

    public void remove(int index) {

        if (-1 < index) {
            if (index == 0) {
                // remove head
                head = head.next;
            } else {
                // remove non-head
                Node nodeBeforeNodeToDelete = getNode(index-1);
                nodeBeforeNodeToDelete.next = nodeBeforeNodeToDelete.next.next;
            }
            size--;
        }

    }

    public void remove(String string) {
        int indexOfNodeToDelete = getIndex(string);
        remove(indexOfNodeToDelete);
    }

    // return as an array
    public String[] toArray() {
        String[] array = new String[size];
        Node node = head;

        for (int i=0; i<size; i++) {
            array[i] = node.data;
            node = node.next;
        }

        return array;
    }

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.add("0A");
        linkedList.add("1B");
        linkedList.add("2C");
        System.out.println(Arrays.toString(linkedList.toArray()));

        System.out.println(linkedList.getIndex("1231"));
        System.out.println(linkedList.getIndex("2C"));
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        linkedList.add(1, "1ADDED");
        System.out.println(Arrays.toString(linkedList.toArray()));
        linkedList.add(3, "3ADDED");
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.set(2,"0UPDATED");
        linkedList.add("3KKK");
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.remove(5);
        linkedList.remove(4);
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.remove("0A");
        linkedList.remove("0B");
        System.out.println(Arrays.toString(linkedList.toArray()));
    }
}
