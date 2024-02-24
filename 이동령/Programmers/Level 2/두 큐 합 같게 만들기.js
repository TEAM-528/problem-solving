function solution(q1, q2) {
    let q1Sum = q1.reduce((res, cur) => res + cur);
    let q2Sum = q2.reduce((res, cur) => res + cur);
    let average = (q1Sum + q2Sum) / 2;
    
    if(Math.floor(average) !== average) return -1;
    let counter = 0, idx1 = 0, idx2 = 0;
    let maximum = (q1.length + q2.length) * 2;
    while(counter <= maximum) {
        if(q1Sum < average) {
            q1Sum += q2[idx2];
            q2Sum -= q2[idx2];
            q1.push(q2[idx2]);
            counter++; idx2++;
        }
        if(q2Sum < average) {
            q2Sum += q1[idx1];
            q1Sum -= q1[idx1];
            q2.push(q1[idx1]);
            counter++; idx1++;
        }
        if(q1Sum === average && q2Sum === average) break;
    }
    
    return counter > maximum ? -1 : counter;
}
