function solution(rows, columns, queries) {
    let result = [];
    let matrix = Array.from({length: rows}, (_, i) => Array.from({length: columns}, (_, j) => columns * i + j + 1));
    
    queries.forEach(([startRow, startCol, endRow, endCol]) => {
        startRow--; startCol--; endRow--; endCol--;
        let temp = min = matrix[startRow][startCol];
        let [curRow, curCol] = [startRow, startCol];
        while(curRow < endRow) {
            matrix[curRow][curCol] = matrix[++curRow][curCol]
            min = Math.min(min, matrix[curRow][curCol]);
        }
        while(curCol < endCol) {
            matrix[curRow][curCol] = matrix[curRow][++curCol]
            min = Math.min(min, matrix[curRow][curCol]);
        }
        while(curRow > startRow) {
            matrix[curRow][curCol] = matrix[--curRow][curCol]
            min = Math.min(min, matrix[curRow][curCol]);
        }
        while(curCol > startCol) {
            matrix[curRow][curCol] = matrix[curRow][--curCol]
            min = Math.min(min, matrix[curRow][curCol]);
        }
        matrix[startRow][startCol + 1] = temp;
        result.push(min);
    });
    return result;
}
