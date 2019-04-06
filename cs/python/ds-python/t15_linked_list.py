class Node:
    __slots__ = '_element', '_next'
    def __init__(self, element, next):
        self._element = element
        self._next = next

    def __str__(self):
        # format specifier in python
        return 'Node %s' % self._element

class LinkedList:
    def __init__(self):
        self._head = None
        self._tail = None

    # __repr__ vs __str__
    # when str(A) is called, str is called, if __str__ is not implemented, __repr__is called
    # but when repr(A) is called, __str__ is never called
    def __str__(self):
        resultStr = ''
        current = self._head
        while (current):
            resultStr += str(current._element) + ', '
            current = current._next
        resultStr = '[' + resultStr[0:(len(resultStr)-2)] + ']' # remove tailing ', ' and close with ]

        return resultStr

    def addFirst(self, node):
        if not self._head:
            self._head = node
            self._tail = node
        else:
            oldHead = self._head
            self._head = node
            self._head._next = oldHead

    def addLast(self, node):
        if not self._head:
            self._head = node
            self._tail = node
        else:
            self._tail._next = node
            self._tail = node

    def removeFirst(self):
        if not self._head:
            raise Empty('LinkedList is empty')
        else:
            self._head = self._head._next

    def removeLast(self):
        if not self._tail:
            raise Empty('LinkedList is empty')
        else:
            if (self._head == self._tail):
                # only one element
                self._head = None
                self._tail = None
            else:
                # more than one element
                current = self._head
                while (current._next._next):
                    current = current._next
                current._next = None
                self._tail = current
    
    # @TODO
    # def insert(self, obj, index)
    # def addAt(self, node, index):
    # def removeAt(self, node, index):

if __name__ == '__main__':
    a1 = LinkedList()
    a1.addFirst(Node(1,None))
    a1.addFirst(Node(2,None))
    a1.addFirst(Node(3,None))
    a1.addFirst(Node(4,None))
    a1.addLast(Node(1,None))
    a1.addLast(Node(2,None))
    a1.addLast(Node(3,None))
    a1.addLast(Node(4,None))
    print(a1)
    a1.removeFirst()
    a1.removeFirst()
    print(a1)
    a1.removeLast()
    a1.removeLast()
    a1.removeLast()
    a1.removeLast()
    a1.removeLast()
    a1.removeLast()
    # a1.removeFirst() # expected error
    # a1.removeLast()  # expected error
    print(a1)
