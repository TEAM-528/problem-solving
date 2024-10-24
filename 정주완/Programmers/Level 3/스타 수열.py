# 40m

def solution(a):
    seq_info = {}

    for i in range(len(a)):
        if a[i] not in seq_info:
            # a[i]를 교집합으로 하는 스타수열 정보 [스타 수열 길이, 마지막으로 스타수열을 만드는 인덱스]
            seq_info[a[i]] = [0, -1]

        if i > 0 and seq_info[a[i]][1] < i - 1 and a[i - 1] != a[i]:
            # [ a[i-1], a[i] ]를 스타 수열에 추가할 수 있다면
            seq_info[a[i]][0] += 2
            seq_info[a[i]][1] = i
        elif i < len(a) - 1 and seq_info[a[i]][1] < i and a[i + 1] != a[i]:
            # [ a[i], a[i + 1] ]를 스타 수열에 추가할 수 있다면
            seq_info[a[i]][0] += 2
            seq_info[a[i]][1] = i + 1

    return max(star_sequence_length for star_sequence_length, _ in seq_info.values())