const solution = (triangle) => {
    let result = [];
    triangle.forEach((array) => {
        let current = [];
        array.forEach((value, index) => {
            if(index === 0) current.push(value + (result[0] || 0));
            else if(index === array.length - 1) current.push(value + (result.at(-1) || 0));
            else current.push(value + Math.max(result[index - 1], result[index]));
        });
        result = [...current];
    });
    return Math.max(...result);
}
