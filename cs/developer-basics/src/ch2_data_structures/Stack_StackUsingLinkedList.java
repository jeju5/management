package ch2_data_structures;

import static org.junit.Assert.assertEquals;

public class Stack_StackUsingLinkedList implements Stack {

    private class Node {
        public String data;
        public Node next;

        public Node(String string) {
            this.data = string;
        }
    }

    private Node head;
    private int size=0;

    // add to the last
    public void push(String string) {
        Node lastInNode = new Node(string);

        if (head == null) {
            head = lastInNode;
        } else {
            Node tailNode = head;

            while (tailNode.next != null) {
                tailNode = tailNode.next;
            }
            tailNode.next = lastInNode;
        }
        size++;
    }

    // get from the last
    public String pop() {

        if (size == 1) {
            String lastInString = head.data;
            head = null;
            size--;
            return lastInString;
        } else if (size > 1) {
            Node nodeBeforeTail = head;
            while (nodeBeforeTail.next.next != null) {
                nodeBeforeTail = nodeBeforeTail.next;
            }
            String lastInString = nodeBeforeTail.next.data;
            nodeBeforeTail.next = null;
            size--;
            return lastInString;
        }
        // return null if size<=0
        return null;
    }

    public String peek() {

        if (size == 1) {
            return head.data;
        } else if (size > 1) {
            Node tailNode = head;
            while (tailNode.next != null) {
                tailNode = tailNode.next;
            }
            return tailNode.data;
        }
        // return null if size<=0
        return null;
    }

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
        return toArrayString() + "<->IN/OUT size=" + size +  " length=" + array.length;
    }

    public static void main(String[] args) {
        Stack testStack = new Stack_StackUsingLinkedList();

        testStack.push("enqueue0");
        testStack.push("enqueue1");
        testStack.push("enqueue2");
        System.out.println(testStack.toOverviewString());
        assertEquals("[enqueue0, enqueue1, enqueue2]<->IN/OUT size=3 length=3", testStack.toOverviewString());

        System.out.println("PEEK: " + testStack.peek());
        assertEquals("enqueue2", testStack.pop());

        System.out.println("PEEK: " + testStack.peek());
        assertEquals("enqueue1", testStack.pop());

        testStack.push("enqueueAgain");
        System.out.println(testStack.toOverviewString());
        assertEquals("[enqueue0, enqueueAgain]<->IN/OUT size=2 length=2", testStack.toOverviewString());

        System.out.println("\nall tests are passed!");
    }
}
