function solution(n) {
    let dp = [1, 1];
    
    for(let i = 2; i <= n; i++) {
        let current = 0;
        for(let j = 0; j < i; j++) current += dp[j] * dp[i - j - 1];
        dp[i] = current;
    }
    return dp[n];
}
