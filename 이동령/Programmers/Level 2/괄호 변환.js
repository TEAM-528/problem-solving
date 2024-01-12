const BRACKET = {"(": 1, ")": -1};

function solution(p) {
    if(p === "") return "";
    
    let answer = "", u = "", v = "", stack = 0;
    p.split("").some((value, index) => {
        stack += BRACKET[value];
        if(stack === 0) {
            u = p.substr(0, index + 1);
            v = p.substr(index + 1);
            return true;
        }
    });
    
    if(checkOrder(u)) answer = u + solution(v);
    else answer = "(" + solution(v) + ")" + u.substr(1, u.length - 2).split("").reduce((result, current) => result + (current === "(" ? ")" : "("), "");
    return answer;
}

function checkOrder(p) {
    let stack = 0;
    return !p.split("").some((value) => {
        stack += BRACKET[value];
        if(stack < 0) return true;
    });
}
