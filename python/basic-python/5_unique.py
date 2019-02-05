# True: if all elements are unique
# False: otherwise

# O(n^2) approach
def unique1(array):
    for i in range(len(array)):              # O(n)
        for j in range(i+1, len(array)):     # O(n)
            if array[i] == array[j]:
                return False
    return True

# O(nlogn) approach
def unique2(array):
    sortedArray = sorted(array)               # O(nlogn)
    for i in range(len(array) - 1)            # O(n)
        if sortedArray[i] == sortedArray[i+1]
            return False
    return True