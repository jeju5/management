# abstract tree class
class Tree:
    # nested class representing a node
    class TreeNode:
        def __init__(self, element):
            self._element = element

        # standard operator == is implemented
        def __eq__(self, otherNode):
            is_same_type = type(self) is type(otherNode)
            is_same_elem = self.element() is otherNode.element()
            return  is_same_type and is_same_elem

        def __ne__(self, other):
            return not (self == other) # opposite of __eq__

        def element(self):
            return self._element

    # abstract methods
    def root(self):
        # An Error "indicates serious problems that a reasonable application should not try to catch."
        # An Exception "indicates conditions that a reasonable application might want to catch."
        raise NotImplementedError("must be implemented")

    def parent(self, node):
        raise NotImplementedError("must be implemented")
    
    def children(self, node):
        raise NotImplementedError("must be implemented")

    def number_of_children(self, node):
        raise NotImplementedError("must be implemented")

    def __len__(self):
        raise NotImplementedError("must be implemented")

    # concrete methods
    def is_root(self, node):
        return self.root() == node
    
    def is_leaf(self, node):
        # note that root can also be a node
        # leaf definition: no children
        return node.number_of_children() == 0

    def is_empty(self):
        return len(self) == 0

    def depth(self, node):
        # depth of a node = number of ancestors
        if self.is_root(node):
            return 0
        else:
            return 1 + self.depth(self.parent(node))

    def height(self, node=None):  
        if node is None:
            # height of a tree
            node = self.root()

        # height of a node = maximum of 'length of descendants chain'
        #
        # height of a node: (formal & recursive definition)
        #   if the node is a leaf then the height is 0
        #   otherwise, 1 + (the maximum height of the children)
        if self.is_leaf(node):
            return 0
        else:
            return 1 + max(self.height(node1) for node1 in self.children(node))

'''
<Traversal>
preorder traversal
    visit a node and visit children of the node (recursive)
    O(n)

postorder traversal
    visit a node after visiting all its children (recursive)
    O(n)

inorder traversal (binary)
'''