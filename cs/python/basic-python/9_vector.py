class Vector:
    # constructor
    # self is a caller of this method
    def __init__(self, size):
        # class variable _coords
        self._coords = [0] * size
        self._coords[size-1] = 'Vector'

    # public method
    def myPublicPrint(self):
        print("this is public")

    # protected method is denoted by _
    # (same rule to class variables. '_' in front of variable name)
    def _myProtectedPrint(self):
        print("this is protected")

    # protected method is denoted by _
    # (same rule to class variables. '__' in front of variable name)
    def __myPrivatePrint(self):
        print("this is private")

    # implementation of standard operator len()
    def __len__(self):
        return len(self._coords)
    
    def myToString(self):
        return (self._coords[-1] + " <" + str(self._coords[0:-1]) + ">")

# inheritance
class MyVector(Vector):  
    def __init__(self, size, value):
        # note that you are not passing self in super __init__
        super().__init__(size)
        self._coords = [value] * size
        self._coords[size-1] = 'MyVector'

    # tip
    # overriding : r"i"de ----- "i"nherited is re-written
    # overloading: lo"a"d ----- p"a"rameter(s) are different

    # overriding
    def myToString(self):
        return (self._coords[-1] + " <" + str(self._coords[0:-1]) + ">")

    # python does not support overloading. 
    # overloading
    # def myToString(self, text):
    #     return (text + self._coords[-1] + " <" + str(self._coords[0:-1]) + ">")

# test (shouldn't be indented)
if __name__ == '__main__':
    print('executing Vector class from __main__')
    v1 = Vector(3)
    print(v1.myToString())

    v2 = MyVector(33, 51)
    print(v2.myToString())

    v1.myPublicPrint()
    # v1.myPrivatePrint()  # throws error
    v2.myPublicPrint()
    # v2.myPrivatePrint()  # throws error