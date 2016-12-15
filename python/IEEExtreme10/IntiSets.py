tests = int(raw_input())


def totient(N, primes):
    if len(primes) == 0:
        return N - 1
    result = N
    for x in primes:
        result *= (1 - 1 / x)
    return result


for x in range(0, tests):
    line = raw_input().split()
    N = long(line[0])
    A = long(line[1])
    B = long(line[2])

    primes = []
    factor = 2
    left = N
    while factor * factor <= N:
        if left % factor == 0:
            left = left / factor
            primes.append(factor)
        else:
            factor += factor

    sum = 0
    int()
    number = A
    while number<=B:
        if (number ** totient(N, primes)) % N == 1:
            sum += number
        number += 1

    print sum%1000000007
