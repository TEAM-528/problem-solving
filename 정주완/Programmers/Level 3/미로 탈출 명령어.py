def solution(n, m, startX, startY, r, c, k):
    answer = ''

    stack = [(startX, startY, '')]
    while stack:
        x, y, route = stack.pop()

        if len(route) == k:
            if x == r and y == c:
                return route
        else:
            dx = [1, 0, 0, -1]
            dy = [0, -1, 1, 0]
            char = ['d', 'l', 'r', 'u']
            for i in range(3, -1, -1):
                nx = x + dx[i]
                ny = y + dy[i]
                
                distance = abs(nx - r) + abs(ny - c)
                remain = k - len(route) - 1
                
                is_reachable = distance <= remain and (remain - distance) % 2 == 0
                if 0 < nx <= n and 0 < ny <= m and is_reachable:
                    stack.append([nx, ny, route + char[i]])

    return "impossible"