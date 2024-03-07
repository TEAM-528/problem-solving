const current = new Set();
const userSet = new Set();

function solution(user_id, banned_id, index = 0) {
    if(index === banned_id.length) {
        userSet.add([...current].sort().join("."));
        return;
    }
    let regexp = new RegExp(`^${banned_id[index].replaceAll("*", ".")}\$`);
    let matched = user_id.filter((id) => banned_id[index].length === id.length && id.match(regexp));
    matched.forEach((id) => {
        if(!current.has(id)) {
            current.add(id);
            solution(user_id, banned_id, index + 1);
            current.delete(id);
        }
    });
    
    return userSet.size;
}
