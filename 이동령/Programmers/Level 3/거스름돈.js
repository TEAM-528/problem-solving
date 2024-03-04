function solution(n, money) {
    let result = Array.from({length: n + 1}, (_, i) => i === 0 ? 1 : 0);
    money.sort((a, b) => a - b);
    money.forEach((m) => {
        for(let i = m; i <= n; i++) result[i] = (result[i] + result[i - m]) % 1000000007;
    });
    return result[n];
}
