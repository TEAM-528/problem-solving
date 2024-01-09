r, c, t = map(int, input().split())

grid = []

for _ in range(r):
    grid.append(list(map(int, input().split())))

purifier_locate = []
for i in range(r):
    for j in range(c):
        if grid[i][j] == -1:
            purifier_locate.append([i, j])


def 확산():
    확산_grid = [[0] * c for _ in range(r)]

    # 확산값 계산
    for i in range(r):
        for j in range(c):
            if grid[i][j] > 0:
                확산량 = grid[i][j] // 5

                if i > 0 and grid[i - 1][j] > -1:
                    확산_grid[i - 1][j] += 확산량
                    grid[i][j] -= 확산량
                if i + 1 < r and grid[i + 1][j] > -1:
                    확산_grid[i + 1][j] += 확산량
                    grid[i][j] -= 확산량
                if j > 0 and grid[i][j - 1] > -1:
                    확산_grid[i][j - 1] += 확산량
                    grid[i][j] -= 확산량
                if j + 1 < c and grid[i][j + 1] > -1:
                    확산_grid[i][j + 1] += 확산량
                    grid[i][j] -= 확산량
    # 확산 결과 합산
    for i in range(r):
        for j in range(c):
            grid[i][j] += 확산_grid[i][j]


def 순환(num):
    y = purifier_locate[num][0]
    x = purifier_locate[num][1]

    before = 0
    while x + 1 < c:
        x += 1
        tmp = grid[y][x]
        grid[y][x] = before
        before = tmp

    if num == 0:
        while y - 1 >= 0:
            y -= 1
            tmp = grid[y][x]
            grid[y][x] = before
            before = tmp
    else:
        while y + 1 < r:
            y += 1
            tmp = grid[y][x]
            grid[y][x] = before
            before = tmp

    while x - 1 >= 0:
        x -= 1
        tmp = grid[y][x]
        grid[y][x] = before
        before = tmp

    if num == 0:
        while y + 1 < purifier_locate[num][0]:
            y += 1
            tmp = grid[y][x]
            grid[y][x] = before
            before = tmp
    else:
        while y - 1 > purifier_locate[num][0]:
            y -= 1
            tmp = grid[y][x]
            grid[y][x] = before
            before = tmp


for _ in range(t):
    확산()
    순환(0)
    순환(1)

res = 0
for i in range(r):
    for j in range(c):
        res += grid[i][j]

print(res + 2)