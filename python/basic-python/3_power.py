def powerI(base, exponent):
    accum = 1

    for i in range(0, exponent):
        accum = accum * base

    return accum

def powerR(base, exponent):
    if (exponent == 0):
        return 1
    else:
        return base * powerR(base, exponent - 1)

if __name__ == '__main__':
    print(powerI(2, 0))
    print(powerI(2, 1))
    print(powerI(2, 2))
    print(powerI(2, 10))

    print(powerR(3, 0))
    print(powerR(3, 1))
    print(powerR(3, 2))
    print(powerR(3, 10))