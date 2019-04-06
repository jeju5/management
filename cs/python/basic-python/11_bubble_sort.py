'''
"bubble-up maximum to the right at each iteration"

best : O(n)
avg  : O(n^2)
worst: O(n^2)
'''

def bubble_sort(inputList):
    for i in range(len(inputList)-1):
        # at the end of j=1
        # the goal is to place the 2nd maximum to last-1
        for j in range((len(inputList)-1)-i):
            if inputList[j] > inputList[j+1]:
                inputList[j], inputList[j+1] = inputList[j+1], inputList[j]
            
        
                
def bubble_sort_optimized(inputList):
    swapped = True
    # at each while-loop we do a inspection from 0 ~ Last
    # if nothing swapped then we know that it is already sorted
    # thus we opt out
    while swapped:
        swapped = False
        for i in range(len(inputList)-1):
            if inputList[i] > inputList[i+1]:
                inputList[i+1], inputList[i] = inputList[i], inputList[i+1]
                swapped = True
        

if __name__ == '__main__':
    myList = [55,6,27,21,-11,999,2,4]
    myList2 = [20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2]
    
    print(myList2)
    bubble_sort(myList2)
    print(myList2)
    
    print(myList2)
    bubble_sort_optimized(myList2)
    print(myList2)