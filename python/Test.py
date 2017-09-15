squares = []
val = 1
while val * val <= 20000:
    squares.append(val * val)
    val += 1

smallestCounts = [0]
for x in range(1, 20000):
    if x in squares:
        smallestCounts.append(1)
    else:
        possibles = [val for val in range(1, x) if val in squares]
        minCount = 20000000
        for y in possibles:
            count = smallestCounts[y] + smallestCounts[x - y]
            if count < minCount:
                minCount = count
        smallestCounts.append(minCount)

print smallestCounts

# testCases = int(raw_input())
# answers = []
# for x in range(0, testCases):
#     answers.append(smallestCounts[int(raw_input())])
#
# for x in answers:
#     print x
