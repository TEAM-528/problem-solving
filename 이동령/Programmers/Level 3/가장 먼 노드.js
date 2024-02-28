function solution(n, edge) {
    let graph = Array.from({length: n}, () => []);
    edge.forEach(([a, b]) => {
        graph[a - 1].push(b - 1);
        graph[b - 1].push(a - 1);
    });
    
    let visited = Array.from({length: n}, () => false);
    let queue = [0], counter = n;
    while(queue.length) {
        let current = [...queue], next = new Set();
        current.forEach((node) => visited[node] = true);
        current.forEach((node) => {
            graph[node].forEach((e) => {
                if(!visited[e]) next.add(e);
            });
            counter--;
        });
        queue = [...next];
        if(counter === queue.length) break;
    }
    return counter;
}
