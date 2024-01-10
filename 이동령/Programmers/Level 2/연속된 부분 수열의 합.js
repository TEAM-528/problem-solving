function solution(sequence, k) {
    let result = [0, 100000000];
    let start = 0, end = -1, prefixSum = 0;
    while(end < sequence.length) {
        if(prefixSum >= k) {
            if(prefixSum === k && result[1] - result[0] > end - start) result = [start, end];
            prefixSum -= sequence[start];
            start++;
        }
        else {
            prefixSum += sequence[++end];
        }
    }
    return result;
}
