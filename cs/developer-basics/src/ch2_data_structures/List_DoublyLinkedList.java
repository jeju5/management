package ch2_data_structures;

import static org.junit.Assert.assertEquals;

public class List_DoublyLinkedList implements List{

    // Node class
    private class Node {
        // class variable
        private String data;
        private Node prev;
        private Node next;

        // arg constructor
        public Node(String string) {
            this.data = string;
            prev = next = null;
        }
    }

    // class variable
    private Node head;
    private Node tail;
    private int size;

    // no-arg constructor
    public List_DoublyLinkedList() {
        head = tail = null;
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
            size++;
        }
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

    public boolean contains(String string) {
        return getIndex(string) != -1;
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
                Node nodeToRemove = getNode(index);
                nodeToRemove.prev.next = nodeToRemove.next;
                nodeToRemove.next.prev = nodeToRemove.prev;
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

    public String toArrayString() {
        String[] array = toArray();
        String string = "[";

        for (int i=0; i<size-1; i++) {
            string += array[i] + ", ";
        }
        string += array[size-1] + "]";
        return string;
    }

    public String toOverviewString() {
        String[] array = toArray();
        return toArrayString() + " size=" + size +  " length=" + array.length;
    }

    public String toStringForward() {
        String string = "TO ARRAY-STRING FORWARD: [";

        Node currentNode = head;
        while (currentNode.next != null) {
            string += currentNode.data + ">>";
            currentNode = currentNode.next;
        }
        string += currentNode.data + "]";
        return string;
    }

    public String toStringBackward() {
        String string = "TO ARRAY-STRING BACKWARD: [";

        Node currentNode = getNode(size-1);
        while (!currentNode.equals(head)) {
            string += currentNode.data + "<<";
            currentNode = currentNode.prev;
        }
        string += currentNode.data + "]";
        return string;
    }

    public static void main(String[] args) {
        List_DoublyLinkedList testList = new List_DoublyLinkedList();

        testList.addLast("0");
        testList.addLast("1");
        testList.addLast("2");
        testList.addFirst("addFirst");
        System.out.println(testList.toOverviewString());
        assertEquals("[addFirst, 0, 1, 2] size=4 length=4", testList.toOverviewString());

        testList.remove(2);
        testList.addLast("ASDJ");
        testList.addLast("DFJE");
        System.out.println(testList.toOverviewString());
        assertEquals("[addFirst, 0, 2, ASDJ, DFJE] size=5 length=5", testList.toOverviewString());

        testList.remove("ASDJ");
        testList.addAt(1,"addAtIndex1");
        testList.set(2, "setIndex1");
        System.out.println(testList.toOverviewString());
        assertEquals(testList.getIndex("addIndex1"), -1);
        assertEquals(testList.getIndex("setIndex1"), 2);
        assertEquals(testList.contains("addIndex"), false);
        assertEquals(testList.contains("setIndex1"), true);
        assertEquals("[addFirst, addAtIndex1, setIndex1, 2, DFJE] size=5 length=5", testList.toOverviewString());

        System.out.println(testList.toStringForward());
        assertEquals("TO ARRAY-STRING FORWARD: [addFirst>>addAtIndex1>>setIndex1>>2>>DFJE]", testList.toStringForward());
        System.out.println(testList.toStringBackward());
        assertEquals("TO ARRAY-STRING BACKWARD: [DFJE<<2<<setIndex1<<addAtIndex1<<addFirst]", testList.toStringBackward());

        System.out.println("\nall tests are passed!");
    }
}
