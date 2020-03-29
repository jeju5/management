//package Tree;
////http://progressivecoder.com/tree-implementation-using-java/
//
//import java.util.Stack;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class BinarySearchTree implements Tree {
//
//    // Binary Node
//    private class Node {
//        private Integer key;
//        private Node leftChild;
//        private Node rightChild;
//
//        public Node(Integer key) {
//            this.key = key;
//            leftChild = rightChild = null;
//        }
//    }
//
//    private Node root;
//    private int size;
//
//    public BinarySearchTree() {
//        root = null;
//        size = 0;
//    }
//
//    public Node insertRecursive(Integer key) {
//        return insertNodeToNodeRecursive(root, key);
//    }
//
//    public Node insertNodeToNodeRecursive(Node node, Integer newKey) {
//
//        if (node == null) {
//            // insert a newNode to a leaf
//            size++;
//            return new Node(newKey);
//
//        } else if (newKey.compareTo(node.key) < 0) {
//            // newKey < node.key -> insert to left tree
//            if (node.leftChild == null) {
//                // new node becomes a left child
//                node.leftChild = insertNodeToNodeRecursive(node.leftChild, newKey);
//                return node.leftChild;
//            } else {
//                // new node becomes a left descendant
//                return insertNodeToNodeRecursive(node.leftChild, newKey);
//            }
//
//        } else if (newKey.compareTo(node.key) > 0) {
//            // node.key < newKey -> insert to right tree
//            if (node.rightChild == null) {
//                // new node becomes a right child
//                node.rightChild = insertNodeToNodeRecursive(node.rightChild, newKey);
//                return node.rightChild;
//            } else {
//                // new node becomes a right descendant
//                return insertNodeToNodeRecursive(node.rightChild, newKey);
//            }
//
//        } else {
//            // newKey = node.key -> insertion not required
//            return node;
//        }
//    }
//
//    public Node insertIterative(Integer newKey) {
//        Node currentNode = root;
//
//        // iterate such that currentNode becomes a direct parent and nextNode a newNode;
//        while (currentNode != null) {
//            if (newKey.compareTo(currentNode.key) > 0) {
//                // newKey < currentKey -> traverse to left-tree
//                currentNode = currentNode.leftChild;
//
//            } else if (newKey.compareTo(currentNode.key) < 0) {
//                // currentKey < newKey -> traverse to right-tree
//                currentNode = currentNode.rightChild;
//            } else {
//                // currentKey = newKey -> no insertion required
//                return currentNode;
//            }
//        }
//
//        // insert a newNode
//        size++;
//        currentNode = new Node(newKey);
//        return currentNode;
//    }
//
//    // getMin - Tree
//    public Node getMin() {
//        return getMin(root);
//    }
//
//    // getMin - SubTree
//    public Node getMin(Node node) {
//        if (node.leftChild == null) {
//            return node
//        } else {
//            getMin(node.leftChild)
//        }
//    }
//
//    // getMax - Tree
//    public Node getMin() {
//        return getMax(root);
//    }
//
//    // getMax - SubTree
//    public Node getMax(Node node) {
//        if (node.rightChild == null) {
//            return node
//        } else {
//            getMin(node.rightChild)
//        }
//    }
//
//    public void delete(Integer integer) {
//        delete(root, integer);
//    }
//
//
//    // return what is going to be at thisNode's place after deletion.
//    // (If deleted, return its replacement. Otherwise, return itself.)
//    public Node delete(Node thisNode, Integer integer) {
//        if (thisNode == null) {
//            // 1. thisNode is null -> thisNode remains the same
//            return null;
//
//        } else if (integer < thisNode.key) {
//            // 2. deletion happens in the left subtree -> thisNode remains the same
//            thisNode.leftChild = delete(thisNode.leftChild, integer)
//            return thisNode;
//
//        } else if (thisNode.key < integer) {
//            // 3. deletion happens in the right subtree -> thisNode remains the same
//            thisNode.rightChild = delete(thisNode.rightChild, integer)
//            return thisNode;
//
//        } else {
//            // 4. this node gets deleted
//            if (thisNode.leftChild == null && thisNode.rightChild == null) {
//                // Scenario1: Node to delete has 0 child. -> replace it with null
//                return null;
//            }
//
//            if (thisNode.leftChild == null || thisNode.rightChild == null) {
//                // Scenario2: Node to delete has 1 child. -> replace it with its child
//                return (thisNode.leftChild != null) ? thisNode.leftChild : thisNode.rightChild;
//            }
//
//            if (thisNode.leftChild != null && thisNode.rightChild != null) {
//                // Scenario3: Node to delete has 2 children. -> replace it with the maximum of the leftTree
//                Node leftTreeMaximum = thisNode.leftChild;
//
//                // find left tree maximum
//                while (leftTreeMaximum.rightChild != null) {
//                    if (leftTreeMaximum.rightChild.rightChild == null) {
//                        // we found the maximum (take out left-tree maximum node from its place)
//                        Node parentOfLeftTreeMaximum = leftTreeMaximum;
//                        leftTreeMaximum = parentOfLeftTreeMaximum.rightChild;
//                        parentOfLeftTreeMaximum.rightChild = null;
//                    } else {
//                        // keep traversing
//                        leftTreeMaximum = leftTreeMaximum.rightChild;
//                    }
//                }
//                return leftTreeMaximum;
//            }
//        }
//    }
//
//
//    public String[] toArrayLevelOrder() {
//        return new String[0];
//    }
//
//    public String[] toArrayInorder() {
//        return new String[0];
//    }
//
//    public String[] toArrayPreorder() {
//        return new String[0];
//    }
//
//    public String[] toArrayPostOrder() {
//        return new String[0];
//    }
//
//    public String toArrayString() {
//        return null;
//    }
//
//    public String toOverviewString() {
//        return null;
//    }
//
//    public static void main(String[] args) {
//        Tree_BinarySearchTree testTree = new Tree_BinarySearchTree();
//    }
//}
