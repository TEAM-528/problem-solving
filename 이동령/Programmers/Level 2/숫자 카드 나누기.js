const solution = (arrayA, arrayB) => {
    return Math.max(checkCard(arrayA, arrayB), checkCard(arrayB, arrayA));
}

const checkCard = (arrayA, arrayB) => {
    let card = arrayA.reduce((res, cur) => {
        res = gcd(res, cur);
        return res;
    });
    let result = arrayA.every((v) => v % card === 0) && arrayB.every((v) => v % card !== 0);
    return result ? card : 0;
}

const gcd = (a, b) => {
    return b === 0 ? a : gcd(b, a % b);
}
