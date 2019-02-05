def factorial(n):
    if 0 <= n < 2:
        return 1
    else:
        return n * factorial(n-1)

# test
if __name__ == '__main__':
    print(factorial(0))
    print(factorial(1))
    print(factorial(2))

'''
- tail recursion
    - recursion that executes recursive call as a last operation.
    - ex) return factiorial(n-1)
    
    - however this is not a tail recursion
    - ex) return n * factiorial(n-1)
    - because 'n *' is performed in the end

    - tail recursion is cost efficient than normal recursion
      because it doesn't need extra space for holding intermediate values.
      tail recursion passes the result to next recursive call
'''