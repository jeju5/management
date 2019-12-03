package ch2_data_structures;

public interface Queue {
    public void enqueue(String string);
    public String dequeue();
    public String peek();

    // validation
    public String[] toArray();
    public String toArrayString();
    public String toOverviewString();
}
