/*
const AREA = [[0, 0], [0, 1], [1, 0], [1, 1]];

function solution(arr) {
    const quadTree = (x, y, n) => {
        if(n < 2) return arr[x][y] ? [0, 1] : [1, 0];
        let [zero, one] = [0, 0];
        n >>= 1;
        AREA.forEach(([i, j]) => {
           let [curZero, curOne] = quadTree(x + i * n, y + j * n, n);
            zero += curZero; one += curOne;
        });
        if(zero === 0) return [0, 1];
        if(one === 0) return [1, 0];
        return [zero, one];
    }
    
    return quadTree(0, 0, arr.length);
}
*/

function solution(arr) {
    const DFS = (startX, startY, endX, endY) => {
        if(startX === endX - 1) return arr[startX][startY] ? [0, 1] : [1, 0];
        let result = [0, 0], sum = 0;
        for(let i = startX; i < endX; i++) {
            for(let j = startY; j < endY; j++) sum += arr[i][j];
        }
        if(sum === 0) return [result[0] + 1, result[1]];
        else if(sum === Math.pow(endX - startX, 2)) return [result[0], result[1] + 1];
        else return [result 
            ,DFS(startX, startY, (startX + endX) / 2, (startY + endY) / 2)
            ,DFS((startX + endX) / 2, startY, endX, (startY + endY) / 2)
            ,DFS(startX, (startY + endY) / 2, (startX + endX) / 2, endY)
            ,DFS((startX + endX) / 2, (startY + endY) / 2, endX, endY)].reduce((res, cur) => {
            res[0] += cur[0];
            res[1] += cur[1];
            return res;
        });
    }
    
    return DFS(0, 0, arr.length, arr.length);
}
