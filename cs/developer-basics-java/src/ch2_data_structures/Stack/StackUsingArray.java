package Stack;

public class StackUsingArray implements Stack {
    private String[] array;
    private int size = 0;

    // "add to the last"
    public void push(String string) {
        ensureSize();
        array[size++] = string;
    }

    // "get from the last"
    public String pop() {
        String lastIn = array[size-1];
        size--;
        return lastIn;
    }

    public String peek() {
        return array[size-1];
    }

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
        return toArrayString() + "<->IN/OUT size=" + size +  " length=" + array.length;
    }

    public void ensureSize() {
        if (size == 0) {
            array = new String[1];
        } else if (array.length <= size) {
            String[] newArray = new String[size*2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public static void main(String[] args) {
        Stack testStack = new StackUsingArray();

        testStack.push("enqueue0");
        testStack.push("enqueue1");
        testStack.push("enqueue2");
        System.out.println(testStack.toOverviewString());
        assert ("[enqueue0, enqueue1, enqueue2]<->IN/OUT size=3 length=4").equals(testStack.toOverviewString());

//        System.out.println("PEEK: " + testStack.peek());
//        assertEquals("enqueue2", testStack.pop());
//
//        System.out.println("PEEK: " + testStack.peek());
//        assertEquals("enqueue1", testStack.pop());
//
//        testStack.push("enqueueAgain");
//        System.out.println(testStack.toOverviewString());
//        assertEquals("[enqueue0, enqueueAgain]<->IN/OUT size=2 length=4", testStack.toOverviewString());
//
//        System.out.println("\nall tests are passed!");
    }
}
