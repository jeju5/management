package ch2_data_structures.Tree;
//http://progressivecoder.com/tree-implementation-using-java/

public class BinarySearchTree implements Tree {

    // Binary Node
    private class Node {
        private Integer key;
        private Node leftChild;
        private Node rightChild;

        public Node(Integer key) {
            this.key = key;
            leftChild = rightChild = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node addNodeToTree(Integer key) {
        return addNodeToNode(root, key);
    }

    public Node addNodeToNode(Node node, Integer key) {
        if (node == null) {
            // 1. create a tree-root
            root = new Node(key);
            return root;
        } else {
            // 2. insert under the tree-root.
            Integer nodeKey = node.key;

            if (key.compareTo(nodeKey) <= 0) {
                if (node.leftChild == null) {
                    // 2-1. insert as left child
                    Node newNode = new Node(key);
                    node.leftChild = newNode;
                    return newNode;
                } else {
                    // 2-2. insert to left tree
                    return addNodeToNode(node.leftChild, key);
                }
            } else {
                if (node.rightChild == null) {
                    // 2-3. insert as right child
                    Node newNode = new Node(key);
                    node.rightChild = newNode;
                    return newNode;
                } else {
                    // 2-4. insert to right tree
                    return addNodeToNode(node.rightChild, key);
                }
            }
        }
    }

    public boolean isLeaf(Node node) {


        return node.leftChild == null && node.rightChild == null;
    }


    public String[] toArrayLevelOrder() {
        return new String[0];
    }

    public String[] toArrayInorder() {
        return new String[0];
    }

    public String[] toArrayPreorder() {
        return new String[0];
    }

    public String[] toArrayPostOrder() {
        return new String[0];
    }

    public String toArrayString() {
        return null;
    }

    public String toOverviewString() {
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree testTree = new BinarySearchTree();
        testTree.addNodeToTree(5);
        testTree.addNodeToTree(3);
        testTree.addNodeToTree(8);
        testTree.addNodeToTree(8124);
        testTree.addNodeToTree(3248);
        testTree.addNodeToTree(24);
        testTree.addNodeToTree(824);
        testTree.addNodeToTree(439);

    }
}
