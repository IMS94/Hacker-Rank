testCases = int(raw_input())
answers = []

for x in range(0, testCases):
    randomNumberAsOriginalString = raw_input()
    birthdayData = raw_input().split()
    base = int(birthdayData[0])
    randomNumber = randomNumberAsOriginalString.count("1")
    if base != 1:
        randomNumber = int(randomNumberAsOriginalString, base)

    randomNumberAsString = str(randomNumber)
    birthday = int(birthdayData[1])

    carry = 0
    for y in range(0, len(randomNumberAsString), 20):
        limit = y + 20
        if limit > len(randomNumberAsString):
            limit = len(randomNumberAsString)
        numString = str(carry) + randomNumberAsString[y:limit]
        carry = int(numString) % birthday
    answers.append(carry)

for x in answers:
    print x
