'''
iterative
'''
def iter_fibonacci():
  a = 0
  b = 1
  while True:
    # yield is only used in generator function 
    # yield means "return and continue"
    yield a
    future = a + b
    a = b
    b = future

'''
inefficient recursive
becuase you are calling two recursive calls
which will exponentially call more recursive calls
'''
def bad_fib(n):
    if n <= 1:
        return n
    else:
        return bad_fib(n-2) + bad_fib(n-1)

'''
efficient recursive
becuase you are calling single recursive call
'''
def good_fib(n):
    # helper returns the pair (F(n-2), F(n-1))
    def helper(k):
        if k == 0:
            return (0, 0)
        elif k == 1:
            return (0, 1)
        else:
            (a, b) = helper(k-1)
            return (b, a+b)
    return helper(n)[1]


# test
from time import time
if __name__ == '__main__':    
    i = 0
    size = 30

    start_time = time();
    while i < size:
        print(bad_fib(i))
        i = i+1
    end_time = time();
    print("bad elapsed: " + str(end_time - start_time));

    i = 0
    start_time = time();
    while i < size:
        print(good_fib(i))
        i = i+1
    end_time = time();
    print("good elapsed: " + str(end_time - start_time));