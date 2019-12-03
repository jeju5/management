package ch2_data_structures;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class List_ArrayList implements List {
    // class variable
    private int size = 0;   // size is the actual number of elements
    private String array[]; // array.length is the number of memory locations possible

    // no-arg constructor
    public List_ArrayList() {
        array = new String[0];
    }

    // add in the first
    public void addFirst(String string) {
        ensureCapacity();
        for (int i=size; 0<i; i--) {
            array[i] = array[i-1];
        }
        array[0] = string;
        size++;
    }

    // add in the end
    public void addLast(String string) {
        ensureCapacity();
        array[size++] = string;
    }

    // add in the index and shift existing elements
    public void addAt(int index, String string) {
        if (index < 0 && size-1 < index) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity();
        for (int i=size; index<i; i--) {
            array[i] = array[i-1];
        }
        array[index] = string;
        size++;
    }

    // get element in the index.
    public String get(int index) {
        if (index < 0 && size < index) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    // get index of the element in array
    public int getIndex(String string) {
        for (int i = 0; i< size; i++) {
            if (string == null && array[i] == null) {
                return i;
            } else if (string.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    // return if object is contained.
    public boolean contains(String string) {
        return getIndex(string) != -1;
    }



    // remove element
    public void remove(String string) {
        for (int i=0; i<size; i++) {
            if (array[i].equals(string)) {
                array[i] = null;
                for (int j=i; j<size; j++) {
                    array[j]=array[j+1];
                }
                size--;
                return;
            }
        }
    }

    // remove element in the index
    public void remove(int index) {
        String deletedElement = get(index);
        array[index] = null;

        for (int i=index; i<size-1; i++) {
            array[i] = array[i+1];
        }
        array[size-1] = null;

        size--;
    }

    // set element in the index and return the old element.
    public void set(int index, String string) {
        array[index] = string;
    }


    public String[] toArray() {
        return Arrays.copyOf(array, size);
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
        return toArrayString() + " size=" + size +  " length=" + array.length;
    }

    // double the size of an array when its full
    public void ensureCapacity() {
        if (array.length <= size) {
            if (array.length == 0) {
                String[] newArray = new String[1];
                array = newArray;
            } else {
                String[] newArray = new String[array.length * 2];
                System.arraycopy(array, 0, newArray, 0, array.length); // System.arraycopy copies elements from srcArray to desArray
                array = newArray;
            }
        }
    }

    public static void main(String[] args) {
        List_ArrayList testList = new List_ArrayList();

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
        assertEquals("[addFirst, 0, 2, ASDJ, DFJE] size=5 length=8", testList.toOverviewString());

        testList.remove("ASDJ");
        testList.addAt(1,"addAtIndex1");
        testList.set(2, "setIndex1");
        System.out.println(testList.toOverviewString());
        assertEquals(testList.getIndex("addIndex1"), -1);
        assertEquals(testList.getIndex("setIndex1"), 2);
        assertEquals(testList.contains("addIndex"), false);
        assertEquals(testList.contains("setIndex1"), true);
        assertEquals("[addFirst, addAtIndex1, setIndex1, 2, DFJE] size=5 length=8", testList.toOverviewString());

        System.out.println("\nall tests are passed!");
    }
}