function solution(A, B) {
    A.sort((a, b) => b - a);
    B.sort((a, b) => b - a);
    
    let result = 0, index = 0;
    A.forEach((e) => {
        if(e < B[index]) {
            index++;
            result++;   
        }
    });
    
    return result;
}
