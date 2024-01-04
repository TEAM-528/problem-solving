// 실패 답안
/*
function solution(phone_book) {
    let result = true;
    let array = phone_book.sort((a, b) => a.length - b.length);
    let prefix = [];
    array.forEach((current) => {
        prefix.forEach((value) => {
            if(current.indexOf(value) === 0) {
                result = false;
                return;
            }
        });
        if(!result) return;
        prefix.push(current);
    })
    return result;
}
*/

// 성공 답안
function solution(phone_book) {
    let array = phone_book.sort();
    return !array.some((value, index, array) => array[index + 1]?.substr(0, value.length) === value);
}
