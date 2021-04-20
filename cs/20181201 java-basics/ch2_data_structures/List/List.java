package ch2_data_structures.List;

public interface List {
    // Create
    public void addFirst(String string);
    public void addLast(String string);
    public void addAt(int index, String string);

    // Read
    public String get(int index);
    public int getIndex(String string);
    public boolean contains(String string);

    // Update
    public void set(int index, String string);

    // Delete
    public void remove(String string);
    public void remove(int index);

    // validation
    public String[] toArray();
    public String toArrayString();
    public String toOverviewString();
}
