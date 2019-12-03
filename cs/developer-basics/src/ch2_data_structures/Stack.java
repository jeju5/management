package ch2_data_structures;

public interface Stack {
    public void push(String string);
    public String pop();
    public String peek();

    // validation
    public String[] toArray();
    public String toArrayString();
    public String toOverviewString();
}
