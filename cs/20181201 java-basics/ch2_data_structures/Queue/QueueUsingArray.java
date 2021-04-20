package ch2_data_structures.Queue;

import static org.junit.Assert.assertEquals;

public class QueueUsingArray implements Queue {

    private String[] array;
    private int size;

    public QueueUsingArray() {
        array = null;
        size = 0;
    }

    // "add to the last"
    public void enqueue(String string) {
        ensureSize();
        array[size++] = string;
    }

    // "get from the first"
    public String dequeue() {
        String firstInString = array[0];

        for (int i=0; i<size-1; i++) {
            array[i] = array[i+1];
        }

        size--;
        return firstInString;
    }

    public String peek() {
        return array[0];
    }

    public void ensureSize() {
        if (size == 0) {
            array = new String[1];
        } else if (array.length <= size) {
            String[] newArray = new String[size*2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    // validation
    public String[] toArray() {
        return array;
    }

    public String toArrayString() {
        String string = "[";

        for (int i=0; i<size-1; i++) {
            string += array[i] + ", ";
        }
        string += array[size-1] + "]";
        return string;
    }

    public String toOverviewString() {
        return "OUT<-" + toArrayString() + "<-IN size=" + size +  " length=" + array.length;
    }

    public static void main(String[] args) {
        Queue testQueue = new QueueUsingArray();

        testQueue.enqueue("enqueue0");
        testQueue.enqueue("enqueue1");
        testQueue.enqueue("enqueue2");
        System.out.println(testQueue.toOverviewString());
        assertEquals("OUT<-[enqueue0, enqueue1, enqueue2]<-IN size=3 length=4", testQueue.toOverviewString());

        System.out.println("PEEK: " + testQueue.peek());
        assertEquals("enqueue0", testQueue.dequeue());

        System.out.println("PEEK: " + testQueue.peek());
        assertEquals("enqueue1", testQueue.dequeue());


        testQueue.enqueue("enqueueAgain");
        System.out.println(testQueue.toOverviewString());
        assertEquals("OUT<-[enqueue2, enqueueAgain]<-IN size=2 length=4", testQueue.toOverviewString());

        System.out.println("\nall tests are passed!");
    }
}
