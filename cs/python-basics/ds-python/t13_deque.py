# double ended queue
# we assume [First <------> Last]

class MyDeque:
    def __init__(self):
        self._list = []

    def __len__(self):
        return len(self._list)

    def __str__(self):
        queueStr = '['
        for i in range(len(self._list)):
            if (i > 0):
                queueStr += ', '

            if (self._list[i] is None):
                queueStr += ''
            else:
                queueStr += str(self._list[i])
        queueStr += ']'
        return queueStr        

    def isEmpty(self):
        return len(self) == 0

    def first(self):
        if (len(self) < 1):
            raise Empty('Queue is empty')
        return self._list[0]

    def last(self):
        if (len(self) < 1):
            raise Empty('Queue is empty')
        return self._list[-1]

    def addFirst(self, obj):
        self._list.insert(0, obj)

    def addLast(self, obj):
        self._list.insert(len(self), obj) # alternatively use append

    def removeFirst(self):
        if self.isEmpty():
            raise Empty('Queue is empty')
        return self._list.pop(0)

    def removeLast(self):
        if self.isEmpty():
            raise Empty('Queue is empty')
        return self._list.pop()

if __name__ == '__main__':
    a1 = MyDeque()
    print(a1)
    a1.addFirst(1)
    print(a1)
    a1.addFirst(2)
    print(a1)
    a1.addLast('A')
    print(a1)
    a1.addLast(4)
    print(a1)
    a1.removeFirst()
    print(a1)
    a1.removeLast()
    print(a1)