# stack is a collection of objects that follow LIFO (last in first out)
#  O --push--> [OOOO<---]
#  O <--pop--- [OOO<----]

# python list works a kin to Stack where pushed in to the right-most space.
# Stack         List
# S.push(a)     L.append(a)
# S.pop()       L.pop()
# S.top(a)      L[-1]
# S.is_empty()  len(L) == 0

class MyStack:
    def __init__(self):
        self._list = []

    def __len__(self):
        return len(self._list)

    def isEmpty(self):
        return len(self._list) == 0

    def push(self, obj):
        self._list.append(obj)

    def top(self):
        return (self._list)[-1]

    def pop(self):
        popped = self._list[-1]
        self._list = self._list[0:-1]
        return popped
        # return self._list.pop()

if __name__ == '__main__':
    a1 = MyStack()
    print(a1.isEmpty())
    a1.push('a')
    print(a1.isEmpty())
    a1.push('b')
    a1.push(11)
    a1.push(2)
    a1.push(3)
    a1.push(4)
    print(a1.top())
    print(a1.pop())
    print(a1.top())
    print(a1.pop())
    print(a1.top())
    print(a1.pop())
    print(a1.top())
    print(a1.pop())
    print(a1.top())
    print(a1.pop())
    print(a1.top())
    print(a1.pop())
    # print(a1.top()) # no item left (error will be thrown)
    print(a1.isEmpty())