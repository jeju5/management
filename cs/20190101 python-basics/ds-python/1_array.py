import ctypes

class MyArray:
    def __init__(self, name):
        self._name = name
        self._count = 0     # count of actual elements
        self._capacity  = 1 # count of spaces for elements
        self._array = self._make_array(self._capacity)

    # this helper method is used in __init__
    # makes a storage for this class using ctypes module
    def _make_array(self, capacity):
        return (capacity * ctypes.py_object)()

    def __len__(self):
        return self._count

    def __getitem__(self, index):
        if (0 <= index and index < self._count):
            return self._array[index]
        else:
            raise IndexError('index is invalid')

    def __str__(self):
        arrayStr = '['
        for i in range(self._count):
            if (i > 0):
                arrayStr += ', '
            arrayStr += str(self._array[i])
        arrayStr += ']'

        return ('<DynamicArray>' + 
                '\nname: ' + self._name + 
                '\ncount: ' + str(self._count) +
                '\ncapacity: ' + str(self._capacity) +
                '\ncontents: ' + arrayStr)

    def append(self, obj):
        if (self._count >= self._capacity):
            self._resize(2 * self._capacity) # 2 is resizing factor
        self._array[self._count] = obj    # assign in the last space
        self._count += 1

    # @TODO
    # def insert(self, obj, index)

    def _resize(self, newSize):
        newArray = self._make_array(newSize) # make new array
        for i in range(self._count):
            newArray[i] = self._array[i]     # copy items
        self._array = newArray
        self._capacity = newSize

if __name__ == '__main__':
    a1 = MyArray('a1')
    print(a1)
    a1.append('a')
    print(a1)
    a1.append('b')
    print(a1)
    a1.append(1)
    a1.append(2)
    a1.append(333)
    a1.append('C')
    print(a1)
    print()
    print(a1[0])




'''
List
concept of storing indexed elements
ex) linked list, array list

Array
implementation of storing data in continuous memory where each element is identical in size & type

Python has six types of sequence. (sequential data type)
List (Python): collection which is ordered and changeable. Allows duplicate members.
Tuple (Python): collection which is ordered and unchangeable. Allows duplicate members.
Set (Python): collection which is unordered and unindexed. No duplicate members.
Dictionary (Python): collection which is unordered, changeable and indexed. No duplicate members.

Python List shows dynamic array behavior
'''