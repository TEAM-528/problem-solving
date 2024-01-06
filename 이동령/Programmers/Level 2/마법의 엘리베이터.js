function solution(storey) {
    let result = 0;
    while(storey) {
        let current = storey % 10;
        storey = (storey - current) / 10;
        let next = storey % 10;
        if(current < 5 || (current === 5 && next < 5)) result += current;
        else {
            result += (10 - current);
            storey++;
        }
    }
    return result;
}
