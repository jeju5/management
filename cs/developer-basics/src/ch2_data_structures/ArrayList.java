package data_structures;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<T> {
    // class variable
    private int size = 0;
    private T elements[];

    // no-arg constructor
    public ArrayList() {
        // instantiation with T is invalid. You can use unchecked cast in this case
        elements = (T[]) new Object[1];
    }

    // tell if it is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // get size
    public int size() {
        return size;
    }

    // set element in the index and return old element
    public T set(int index, T element) {
        T oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    // get element in the index. throw exception if index is invalid.
    public T get(int index) {
        if (index < 0 && size < index) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    // get index of the element in elements
    public int indexOf(Object element) {
        for (int i = 0; i< size; i++) {
            if (element == null && elements[i] == null) {
                return i;
            } else if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    // remove element of index in elements and shrink & shift it
    public T remove(int index) {
        T deletedElement = get(index);
        elements[index] = null;

        for (int i = index; i< size -1; i++) {
            elements[i] = elements[i+1];
        }
        elements[size -1] = null;

        size--;
        return deletedElement;
    }

    // add element in the index
    public void add(int index, T element) {
        if (index < 0 && size < index) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();

        // shifting
        for (int i = size -1; index<i; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    // add element in the end
    public boolean add(T element) {
        ensureCapacity();
        elements[size++] = element;
        size++;
        return true;
    }

    // return if object is contained. use indexOf.
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    // return if objects are all contained. use contains.
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    // return true if it is full
    public boolean isFull() {
        return elements.length >= size;
    }

    // double the capacity of elements when its full
    public void ensureCapacity() {
        if (isFull()) {
            T[] newElements = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length); // System.arraycopy copies elements from srcArray to desArray
            elements = newElements;
        }
    }

    // print method
    public void print() {
        int i=0;
        for (Object element : elements) {
            String elementString = element == null ? "" : element.toString();
            System.out.println(i++ + ": " + elementString);
        }
    }

    // return a copy of an array
    public T[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    // test
    public static void main(String[] args) {
        // run a few simple tests
        ArrayList<Integer> mal = new ArrayList<Integer>();
        mal.add(1);
        mal.add(2);
        mal.add(3);
        System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);

        mal.remove(2);
        System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);
    }
}