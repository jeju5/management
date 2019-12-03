package ch2_data_structures;

import java.util.Arrays;

public class CircularLinkedList {

    // Node class
    private class Node {
        // class variable
        private String data;
        private Node prev;
        private Node next;

        // arg constructor
        public Node(String string) {
            this.data = string;
        }
    }

    // class variable
    private Node head;
    private Node tail;
    private int size;

    // no-arg constructor
    public CircularLinkedList() {
        head = null;
        size = 0;
    }

    // add in the front
    public void addFirst(String string) {
        Node node = new Node(string);
        node.next = head;
        node.next.prev = node;
        head = node;
        size++;
    }

    // add in the end
    public void addLast(String string) {
        Node node = new Node(string);

        if (head == null) {
            head = node;
            size++;
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
            node.prev = tail;
            size++;
        }
    }

    // add in the index
    public void addAt(int index, String string) {
        if (index == 0) {
            addFirst(string);
        } else {
            Node nodeAtIndexMinusOne = getNode(index-1);
            Node nodeAtIndex = nodeAtIndexMinusOne.next;
            Node nodeToInsert = new Node(string);

            nodeAtIndexMinusOne.next = nodeToInsert;
            nodeToInsert.prev = nodeAtIndexMinusOne;

            nodeToInsert.next = nodeAtIndex;
            nodeAtIndex.prev = nodeToInsert;
        }
        size++;
    }

    // get String at index
    public String get(int index) {
        return getNode(index).data;
    }

    // get Node at index
    public Node getNode(int index) {
        Node node = head;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        return node;
    }

    // get index that has the string
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

    // set string at index
    public void set(int index, String string) {
        Node node = getNode(index);
        node.data = string;
    }

    // remove node at index
    public void remove(int index) {
        if (-1 < index) {
            if (index == 0) {
                // remove head
                head = head.next;
                head.prev = null;
            } else {
                // remove non-head
                Node nodeBeforeNodeToDelete = getNode(index-1);
                nodeBeforeNodeToDelete.next = nodeBeforeNodeToDelete.next.next;
                nodeBeforeNodeToDelete.next.prev = nodeBeforeNodeToDelete;

            }
            size--;
        }
    }

    // remove node with the string
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

    public void printForward() {
        Node currentNode = head;
        System.out.print("PrintForward: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
    }

    public void printBackward() {
        Node currentNode = getNode(size-1);
        System.out.print("PrintBackward: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.prev;
        }
    }

    public static void main(String[] args) {
        CircularLinkedList linkedList = new CircularLinkedList();
        linkedList.addLast("Tail1");
        linkedList.addLast("Tail2");
        linkedList.addLast("Tail3");
        linkedList.addFirst("Head1");
        linkedList.addLast("Tail4");
        linkedList.addFirst("Head2");
        System.out.println(Arrays.toString(linkedList.toArray()));

        System.out.println(linkedList.getIndex("1231"));
        System.out.println(linkedList.getIndex("Head1"));
        System.out.println(linkedList.getIndex("Head1123"));
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        linkedList.set(2,"0UPDATED");
        linkedList.addLast("3KKK");
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.remove(5);
        linkedList.remove(4);
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.remove("0A");
        linkedList.remove("0B");
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.printForward();
        System.out.println();
        linkedList.printBackward();
    }
}
