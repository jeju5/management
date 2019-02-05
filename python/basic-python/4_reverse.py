def reverseI(array, start, end):
    for i in range( start, ( (end - start) // 2) + 1 ):
        revIndex = start + end - i
        array[i], array[revIndex] = array[revIndex], array[i]

def reverseR(array, start, end):
    if (start < end):
        array[start], array[end] = array[end], array[start]
        reverseR(array, start+1, end-1)
        

# test
if __name__ == '__main__':
    array = [2, 4, 5, 6, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37]
    print(str(array))
    reverseI(array, 0, 6)
    print(str(array))
    reverseR(array, 0, 6)
    print(str(array))