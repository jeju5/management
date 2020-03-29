//package Queue;
//
//import static org.junit.Assert.assertEquals;
//
//public class QueueUsingLinkedList implements Queue {
//
//    private class Node {
//        public String data;
//        public Node next;
//
//        public Node(String string) {
//            this.data = string;
//        }
//    }
//
//    private int size;
//    private Node head;
//
//    public Queue_QueueUsingLinkedList() {
//        size = 0;
//        head = null;
//    }
//
//    // "add to the last"
//    public void enqueue(String string) {
//        Node node = new Node(string);
//
//        if (head == null) {
//            head = node;
//        } else {
//            Node tail = head;
//            while (tail.next != null) {
//                tail = tail.next;
//            }
//            tail.next = node;
//            tail = node;
//        }
//        size++;
//    }
//
//    // "get from the first"
//    public String dequeue() {
//        String firstInData;
//
//        if (head == null) {
//            firstInData = null;
//        } else {
//            Node firstInNode = head;
//            head = head.next;
//            firstInData = firstInNode.data;
//            size--;
//        }
//        return firstInData;
//    }
//
//    public String peek() {
//        return head.data;
//    }
//
//    public String[] toArray() {
//        String[] array = new String[size];
//        Node node = head;
//
//        for (int i=0; i<size; i++) {
//            array[i] = node.data;
//            node = node.next;
//        }
//        return array;
//    }
//
//    public String toArrayString() {
//        String[] array = toArray();
//        String string = "[";
//
//        for (int i=0; i<size-1; i++) {
//            string += array[i] + ", ";
//        }
//        string += array[size-1] + "]";
//        return string;
//    }
//
//    public String toOverviewString() {
//        String[] array = toArray();
//        return "OUT<-" + toArrayString() + "<-IN size=" + size +  " length=" + array.length;
//    }
//
//    public static void main(String[] args) {
//        Queue testQueue = new Queue_QueueUsingArray();
//
//        testQueue.enqueue("enqueue0");
//        testQueue.enqueue("enqueue1");
//        testQueue.enqueue("enqueue2");
//        System.out.println(testQueue.toOverviewString());
//        assertEquals("OUT<-[enqueue0, enqueue1, enqueue2]<-IN size=3 length=4", testQueue.toOverviewString());
//
//        System.out.println("PEEK: " + testQueue.peek());
//        assertEquals("enqueue0", testQueue.dequeue());
//
//        System.out.println("PEEK: " + testQueue.peek());
//        assertEquals("enqueue1", testQueue.dequeue());
//
//
//        testQueue.enqueue("enqueueAgain");
//        System.out.println(testQueue.toOverviewString());
//        assertEquals("OUT<-[enqueue2, enqueueAgain]<-IN size=2 length=4", testQueue.toOverviewString());
//
//        System.out.println("\nall tests are passed!");
//    }
//}
