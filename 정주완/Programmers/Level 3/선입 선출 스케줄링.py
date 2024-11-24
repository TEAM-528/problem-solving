# 주어진 시간 동안 모든 코어가 완료한 작업 수 계산
def cal_finished_task(time, cores):
    finished_task = len(cores)
    for core in cores:
        finished_task += (time // core)
    return finished_task


def solution(n, cores):
    answer = 0
    finished_time = -1

    # 파라메트릭 서치로 모든 작업이 끝나는 시간 (finished_time) 계산
    left = 0
    right = 25000 * 10000
    while left <= right:
        mid = (left + right) // 2
        finished_task = cal_finished_task(mid, cores)
        if finished_task >= n:
            finished_time = mid
            right = mid - 1
        else:
            left = mid + 1

    # 작업이 끝나는 시간에 처리해야 하는 일의 개수 (remaining_tasks) 계산
    remaining_tasks = n - cal_finished_task(finished_time - 1, cores)

    # cores를 앞에서 부터 확인하면서, 마지막 작업을 처리하는 core 확인
    for i, core in enumerate(cores):
        # finished_time에 core가 작업이 끝난 상태인 경우
        if finished_time % core == 0:
            remaining_tasks -= 1
        # 남은 작업이 없으면 현재 core의 순서가 정답
        if remaining_tasks == 0:
            answer = i + 1
            break

    return answer