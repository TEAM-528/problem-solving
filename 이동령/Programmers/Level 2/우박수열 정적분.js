function solution(k, ranges) {
    let result = [], collatz = [k], area = [];
    while(k !== 1) {
        k = k % 2 === 1 ? k * 3 + 1 : k / 2;
        area.push((collatz.at(-1) + k) / 2);
        collatz.push(k);
    }
    
    ranges.forEach(([x, y]) => {
        let [start, end] = [x, y + collatz.length - 1];
        if(end < start) result.push(-1);
        else if(start === end) result.push(0);
        else result.push(area.slice(start, end).reduce((res, cur) => res + cur));
    });
    return result;
}
