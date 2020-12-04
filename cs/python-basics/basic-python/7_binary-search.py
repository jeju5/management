import time
# assume sorted
# pick center
# search left section or right section (recursive)

# iterative
def binarySearchI(item, array):
    start = 0
    end = len(array) - 1

    while (start <= end):
        midIndex = (start + end) // 2

        if  item == array[midIndex]:
            # found
            return midIndex
        elif item < array[midIndex]:
            # keep searching left
            end = midIndex -1
        else:
            # keep searching right
            start = midIndex + 1

    # not found
    return None

# recursive (return boolean)
def binarySearchR1(item, array):
    if (len(array) <= 0):
        # base: not found
        return None
    else:
        # step: set mid
        midIndex = len(array) // 2

        if (item == array[midIndex]):
            # found
            return True
        elif (item < array[midIndex]):
            # keep searching left
            return binarySearchR1(item, array[0:midIndex])
        else:
            # keep searching right
            return binarySearchR1(item, array[midIndex+1:])

# recursive (return index)
def binarySearchR2(item, array):
    def helper(item, array, start, end):
        if (start > end):
            # base: not found
            return None
        else:
            # step: set mid
            midIndex = (start + end) // 2

            if (item == array[midIndex]):
                # found
                return midIndex
            elif (item < array[midIndex]):
                # keep searching left
                return helper(item, array, start, midIndex - 1)
            else:
                # keep searching right
                return helper(item, array, midIndex + 1, end)

    return helper(item, array, 0, len(array) -1)
            
# test
if __name__ == '__main__':
    array = [2,4,5,6,7,8,9,12,14,17,19,22,25,27,28,33,37]
    
    print("I")
    print(binarySearchI(1, array))  # F
    print(binarySearchI(2, array))  # T 0 
    print(binarySearchI(3, array))  # F
    print(binarySearchI(6, array))  # T 3
    print(binarySearchI(25, array)) # T 12
    print(binarySearchI(37, array)) # T 16
    print(binarySearchI(38, array)) # F

    print("\nR1")
    print(binarySearchR1(1, array))  # F
    print(binarySearchR1(2, array))  # T
    print(binarySearchR1(3, array))  # F
    print(binarySearchR1(6, array))  # T 
    print(binarySearchR1(25, array)) # T
    print(binarySearchR1(37, array)) # T
    print(binarySearchR1(38, array)) # F

    print("\nR2")
    print(binarySearchR2(1, array))  # F
    print(binarySearchR2(2, array))  # T 0
    print(binarySearchR2(3, array))  # F
    print(binarySearchR2(6, array))  # T 3
    print(binarySearchR2(25, array)) # T 12
    print(binarySearchR2(37, array)) # T 16
    print(binarySearchR2(38, array)) # F