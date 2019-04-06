'''
queue is a collection of objects that follow FIFO (first in first out)
O --enqueue--> [OOOO]
               [OOOO] --dequeue--> O

key is to use '% size' to calculate the proper index when doing enqueue/dequeue
'front' = 'first-in element' = 'left-end element in the flow'
0-index is not necessarily the first in. because as we dequeue. the front moves to right
but when it reaches the right-end the front will be zero. (% length)

example)
[_]       enqueue
[F]       enqueue
[F0]      enqueue
[F00_]    enqueue
...
[F00000__] enqueue
[_F0000__] dequeue
[_F00000_] enqueue (note that enqueud in the right-side free space)
[_F000000] enqueue
[0F000000] enqueue

whatever count we have we will always have a continuous block of that length(count)
[000_000F__] <-- invalid scenario
'''

class MyQueue:
    def __init__(self):
        self._list = []
        self._count = 0                 # length of continuous elements
        self._firstIndex = 0            # FIFO

    def __len__(self):
        return self._count

    def isEmpty(self):
        return self._count == 0
    
    # peek the first element
    def first(self):
        if self.isEmpty():
            raise Empty('Queue is empty')
        else:
            return self._list[self._front]

    def enqueue(self, obj):
        if (self._count == len(self._list)):       # resize when full
            if (len(self._list) == 0):             # initial resize
                self._resize(1)
            else:                                  # non-initial resize
                self._resize(2 * len(self._list))
        # last index = we are filling to the right of first index, and
        # when it reaches the list-end we go to the index-0 (if not full)
        lastIndex = (self._firstIndex + self._count) % len(self._list)  
        self._list[lastIndex] = obj
        self._count += 1

    def dequeue(self):
        if (self._count < 1):
            raise Empty('Queue is empty')
        dequeued = self._list[self._firstIndex]
        self._list[self._firstIndex] = None        # don't forget to empty the space
        # first index starts from 0 and moves to the right direction
        self._firstIndex = (self._firstIndex + 1) % len(self._list)
        self._count -= 1
        return dequeued;

    def _resize(self, newCapacity):
        # invalid capacity
        if self._count > newCapacity:
            raise IndexError('capacity is too small')
        
        # store old data
        oldList = self._list
        oldFirstIndex = self._firstIndex

        # make new data
        self._list = [None] * newCapacity
        for i in range(self._count):
            # we make a new list and copying into from index-0
            # but we also update old firstIndex so that we keep FIFO policy
            self._list[i] = oldList[oldFirstIndex]            
            oldFirstIndex = (1 + oldFirstIndex) % self._count
        self._firstIndex = 0

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

if __name__ == '__main__':
    a1 = MyQueue()
    print(a1)
    a1.enqueue(1)
    print(a1)
    a1.enqueue(2)
    print(a1)
    a1.enqueue('A')
    print(a1)
    a1.enqueue(4)
    print(a1)
    a1.enqueue(5)
    print(a1)
    a1.dequeue()
    print(a1)
    a1.enqueue('A')
    a1.enqueue('A')
    a1.enqueue('A')
    a1.enqueue('A')
    a1.enqueue('A')
    print(a1)