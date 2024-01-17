const solution = (book_time) => {
    let rooms = [];
    book_time.sort();
    book_time.forEach((book) => {
        let [start, end] = [...book];
        let result = rooms.findIndex((value) => value <= getTime(start));
        if(result !== -1) rooms[result] = getTime(end) + 10;
        else rooms.push(getTime(end) + 10);
    });
    return rooms.length;
}

const getTime = (timeStr) => {
    let [h, m] = timeStr.split(":");
    return +h * 60 + +m;
}
