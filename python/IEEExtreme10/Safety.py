password = raw_input()
test_number = input()
original = password
temp = original


def func_1(temp, i, j, k):
    result_1 = (temp[i:j + 1] == temp[k:k + j - i + 1])
    # print temp[k:k+j-i+1]

    if (result_1 == True):
        print 'Y'
    else:
        print 'N'
    return


def func_2(temp, i, j, k):
    p_wrd = temp[:i] + original[k:k + j - i + 1] + temp[j + 1:]
    # print 'f_2';
    # print temp[:i] +original[k:k+j-i+1];
    # print temp;
    # print p_wrd;
    return p_wrd


def func_3(temp, i, j):
    v = ""
    for l in range(i, j + 1):
        if (temp[l] == 'z'):
            v = v + 'a'
        else:
            v = v + chr(ord(temp[l]) + 1)
    p_wrd = temp[:i].join(v).join(temp[j + 1:])

    return p_wrd


for y in range(0, test_number):
    f_line = raw_input();
    line_vals = f_line.split();
    if line_vals[0] == '1':
        func_1(temp, int(line_vals[1]) - 1, int(line_vals[2]) - 1, int(line_vals[3]) - 1);
    elif line_vals[0] == '2':
        temp = func_2(temp, int(line_vals[1]) - 1, int(line_vals[2]) - 1, int(line_vals[3]) - 1);
    elif line_vals[0] == '3':
        temp = func_3(temp, int(line_vals[1]) - 1, int(line_vals[2]) - 1);
        # print temp;
