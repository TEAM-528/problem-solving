function solution(cookie) {
    let result = 0;
    for(let i = 0; i < cookie.length - 1; i++) {
        let [left, lSum, right, rSum] = [i, cookie[i], i + 1, cookie[i + 1]];
        while(true) {
            if(lSum === rSum) result = Math.max(lSum, result);
            if(lSum <= rSum && 0 < left) lSum += cookie[--left];
            else if(lSum >= rSum && right < cookie.length - 1) rSum += cookie[++right];
            else break;
        }
    }
    return result;
}
