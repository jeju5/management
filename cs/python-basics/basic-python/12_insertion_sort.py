'''
"insert elements into sorted section"

we have two section 'sorted' 'unsorted'
iterate through unsorted section and insert into sorted properly

best : O(n)
avg  : O(n^2)
worst: O(n^2)
'''
def insertion_sort(inputList):
    for i in range(1, len(inputList)):    # i: index of inserting element from unsorted section
        insertingElement = inputList[i]   # element you are trying to insert        
        insertingIndex = i                # index you may put insertingElement into

        while (0 < insertingIndex) and (insertingElement < inputList[insertingIndex-1]):
            inputList[insertingIndex] = inputList[insertingIndex-1] # shift to right
            insertingIndex -= 1                                     # iterate to left
        inputList[insertingIndex] = insertingElement                # insertion

if __name__ == '__main__':
    myList = [55,6,27,21,-11,999,2,4]
    print(myList)
    insertion_sort(myList)
    print(myList)

# Merge Sort					Yes
# Bucket Sort
# Quicksort				 best,  avg	Usually not*
# Heapsort					No
# Counting Sort