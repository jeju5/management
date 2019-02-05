class Node:
    # __dict__ manages python class members -> additional memeory usage
    # __slots__ allows you to create declare class member without
    # the use of __dict__ (streamline memeory usage)
    __slots__ = '_element', '_next'

    def __init__(self, element, next):
        self._element = element
        self._next = next