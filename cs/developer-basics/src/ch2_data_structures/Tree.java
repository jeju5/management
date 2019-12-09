package ch2_data_structures;

import java.util.ArrayList;

public interface Tree {


    // Breadth First Traversal
    public String[] toArrayLevelOrder();

    // Depth First Traversal
    public String[] toArrayInorder();
    public String[] toArrayPreorder();
    public String[] toArrayPostOrder();

    // Validation
    public String toOverviewString();
}
