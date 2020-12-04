'''
"select minimum from right and place it into the left"

best : O(n^2)
avg  : O(n^2)
worst: O(n^2)
'''
def selection_sort(inputList):
    # select the minimum in the right and put it into the placeholder (at each iteration)
    for placeholderIndex in range(0, len(inputList)-1):
        for selectingIndex in range(placeholderIndex+1, len(inputList)):
            if inputList[selectingIndex] < inputList[placeholderIndex]:
                # swap (put smaller in the placeholder)
                inputList[selectingIndex], inputList[placeholderIndex] = inputList[placeholderIndex], inputList[selectingIndex]

if __name__ == '__main__':
    myList = [55,6,27,21,-11,999,2,4]
    print(myList)
    selection_sort(myList)
    print(myList)