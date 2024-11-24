def solution(commands):
    answer = []
    table = [[['EMPTY', (i, j)] for j in range(51)] for i in range(51)]

    for command in commands:
        cmd = command.split()
        if cmd[0] == 'UPDATE':
            if len(cmd) == 4:
                r, c, value = int(cmd[1]), int(cmd[2]), cmd[3]
                pr, pc = table[r][c][1]
                table[pr][pc][0] = value
            else:
                value1, value2 = cmd[1], cmd[2]
                for j in range(1, 51):
                    for k in range(1, 51):
                        if table[j][k][0] == value1:
                            table[j][k][0] = value2
        elif cmd[0] == 'MERGE':
            r1, c1, r2, c2 = map(int, cmd[1:])
            pr1, pc1 = table[r1][c1][1]
            pr2, pc2 = table[r2][c2][1]
            if table[pr1][pc1][0] == 'EMPTY':
                table[pr1][pc1][0] = table[pr2][pc2][0]
            for i in range(1, 51):
                for j in range(1, 51):
                    if table[i][j][1] == (pr2, pc2):
                        table[i][j][1] = (pr1, pc1)
                        
        elif cmd[0] == 'UNMERGE':
            r, c = int(cmd[1]), int(cmd[2])
            pr, pc = table[r][c][1]
            value = table[pr][pc][0]
            for i in range(1, 51):
                for j in range(1, 51):
                    if table[i][j][1] == (pr, pc):
                        table[i][j][1] = (i, j)
                        table[i][j][0] = 'EMPTY'
            table[r][c][0] = value

        elif cmd[0] == 'PRINT':
            r, c = int(cmd[1]), int(cmd[2])
            pr, pc = table[r][c][1]
            value = table[pr][pc][0]
            answer.append(value)

    return answer