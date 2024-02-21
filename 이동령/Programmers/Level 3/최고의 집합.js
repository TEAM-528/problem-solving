function solution(n, s) {
    let arithmeticMean = Math.floor(s / n);
    let remainder = s % n;
    if(arithmeticMean === 0) return [-1];
    else return Array.from({length: n}, (_, i) => {
        return i < n - remainder ? arithmeticMean : arithmeticMean + 1
    });
}
