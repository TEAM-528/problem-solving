const directions = [
    [-1, 0],
    [1, 0],
    [0, -1],
    [0, 1],
];

const solution = (board) => {
    const boardX = board.length;
    const boardY = board[0].length;
    
    let robot = [0, 0];
    board = board.map((string, index) => {
        if(string.indexOf("R") !== -1) robot = [index, string.indexOf("R")];
        return string.split("");
    });
    
    const BFS = (start) => {
        let queue = [], result = -1;
        queue.push({curX: robot[0], curY: robot[1], curDist: 0});
        
        while(queue.length) {
            let {curX, curY, curDist} = queue.shift();
            if(board[curX][curY] === 'G') {
                result = curDist;
                break;
            }
            board[curX][curY] = 'V';
            directions.forEach(([x, y]) => {
                let nextX = curX, nextY = curY;
                while(0 <= nextX + x && nextX + x < boardX && 0 <= nextY + y && nextY + y < boardY && board[nextX + x]?.[nextY + y] !== 'D') {
                    nextX += x;
                    nextY += y;
                }
                if(board[nextX] && (board[nextX][nextY] === '.' || board[nextX][nextY] === 'G')) 
                   queue.push({curX: nextX, curY: nextY, curDist: curDist + 1});
            })
        }
        return result;
    }
    
    return BFS(robot);
}
