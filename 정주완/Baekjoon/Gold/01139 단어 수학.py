def str_to_int(s):
    res = 0
    for idx, char in enumerate(s):
        res += sorted_dict[char]['value'] * (10 ** (len(s) - idx - 1))
    return res

N = int(input())
word_list = []
input_dict = {}

for _ in range(N):
    word = input()
    word_list.append(word)
    for j, char in enumerate(word):
        if char in input_dict:
            input_dict[char]['primitive_value'] += 10 ** (len(word) - j - 1)
        else:
            input_dict[char] = {'primitive_value': 10 ** (len(word) - j - 1)}

sorted_dict = dict(sorted(input_dict.items(), key=lambda x: x[1]['primitive_value'], reverse=True))

num = 9
for char, values in sorted_dict.items():
    values['value'] = num
    num -= 1

result = sum(str_to_int(word) for word in word_list)

print(result)
