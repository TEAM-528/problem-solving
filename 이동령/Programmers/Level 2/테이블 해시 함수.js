function solution(data, col, row_begin, row_end) {
    data = data.sort((a, b) => a[col - 1] - b[col - 1] || b[0] - a[0]);
    let result = [];
    for(let i = row_begin - 1; i < row_end; i++) {
        result.push(data[i].reduce((res, cur) => res + (cur % (i + 1)), 0));
    }
    return result.reduce((res, cur) => res ^ cur, 0);
}
