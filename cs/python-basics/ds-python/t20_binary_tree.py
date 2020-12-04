from t19_tree import Tree

# python supports multiple inheritance
# you could have done class BinaryTree(Tree, TreeNode) assuming TreeNode is imported
class BinaryTree(Tree):
    # abstract methods
    def left(self, node):
        raise NotImplementedError("must be implemented")
   
    def right(self, node):
        raise NotImplementedError("must be implemented")

    # concrete methods
    def make_node(self, element):
        # one line if/else statement:
        # (if-statement) if (condition) else (else-statement)
        return Tree.TreeNode(element) if element is not None else None

    def sibling(self, node):
        parent = self.parent(node)
        
        if parent is None:
            return None
        else:
            if node == self.left(parent):
                # node is left child
                return self.right(parent)
            else:
                return self.left(parent)

    def children(self, node):
        # generate node's children nodes
        if self.left(node) is not None:
            yield self.left(node)
        if self.right(node) is not None:
            yield self.right(node)
