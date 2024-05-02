import sys

input = sys.stdin.readline

t = int(input())
for i in range(t):

    n = int(input())
    data = list(map(int, input().split()))
    data.insert(0, 0)

    visited = [False] * (n + 1)
    result = n

    for j in range(1, len(data)):
        if not visited[j]:
            current_idx = j
            # 시작 학생을 임시 팀에 추가, 몇 번째로 추가된 학생인지 저장
            tmp_team = {j: 0}
            while True:
                # 현재 학생을 방문처리하고, 현재 학생이 선택한 학생을 다음 학생으로 저장
                visited[current_idx] = True
                next_idx = data[current_idx]

                # 다음 학생이 임시 팀에 들어간 학생이면 (=사이클) 팀이 생성된다.
                if next_idx in tmp_team:
                    # 해당 학생부터 시작되는 사이클의 학생만큼만 팀이 되므로, 그 길이를 답에서 뺀다.
                    result -= (len(tmp_team) - tmp_team[next_idx])
                    break

                # 다음 학생이 이미 탐색한 학생이면, 다시 탐색할 필요가 없으므로 종료
                if visited[next_idx]:
                    break

                # 다음 학생을 tmp_team에 추가하고 탐색
                current_idx = next_idx
                tmp_team[current_idx] = len(tmp_team)

    print(result)