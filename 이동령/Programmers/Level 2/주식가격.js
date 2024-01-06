function solution(prices) {
    let answer = [];
    for(let i = 0; i < prices.length; i++) {
        let counter = 0;
        for(let j = i + 1; j < prices.length; j++) {
            counter++;
            if(prices[j] < prices[i]) break;
        }
        answer.push(counter);
    }
    return answer;
}
