function solution(n, stations, w) {
    const boundary = w * 2 + 1;
    let result = 0, current = 1;
    stations.forEach((v) => {
        result += Math.ceil((v - current - w) / boundary);
        current = v + w + 1;
    });
    if(stations.at(-1) + w < n) result += Math.ceil((n - (stations.at(-1) + w)) / boundary);
    return result;
}
