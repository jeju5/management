public class MyArrayList<T> {
    private int size = 0;
    private Object elements[];

    public MyArrayList() {
        elements = new Object[1];
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public Object get(int index) {
        return elements[index];
    }

    public boolean isFull() {
        return elements.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity() {
        if (isFull()) {
            Object[] newElements = new Object[elements.length * 2];

            for (int i=0; i<elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    public void print() {
        int i=0;
        for (Object element : elements) {
            String elementString = element == null ? "" : element.toString();
            System.out.println(i++ + ": " + elementString);
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<String>();

        myArrayList.print();
        myArrayList.add("0");
        myArrayList.print();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        myArrayList.add("5");
        myArrayList.print();
    }
}