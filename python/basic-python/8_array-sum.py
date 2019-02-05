# linear iterative
def sumLI(array, start, end):
    accum = 0
    for i in range(start, end+1):
        accum = accum + array[i]
    return accum

# linear recursive
def sumLR(array, start, end):
    if (start == end):
        return array[end]
    else:
        return array[start] + sumLR(array, start+1, end)
    return accum

# binary recursive
def sumBR(array, start, end):
    if (start == end):
        return array[end]
    else:
        midIndex = (start + end) // 2 # caution: parenthesis!
        return sumBR(array, start, midIndex) + sumBR(array, midIndex+1, end)


if __name__ == '__main__':
    array = [1,2,3,4,5,6,7,8,9,10,11,12,13,14]
    print(sumLI(array, 4, 9))
    print(sumLR(array, 4, 9))
    print(sumBR(array, 4, 9))