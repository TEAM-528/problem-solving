function solution(k, d) {
    let result = 1 + Math.floor(d / k) * 2;
    for(let a = 1; a < d / k; a++) {
        result += Math.floor(Math.sqrt(Math.pow(d / k, 2) - Math.pow(a, 2))) || 0;
    }
    return result;
}
