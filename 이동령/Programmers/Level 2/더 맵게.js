function solution(scoville, K) {
    let counter = 0;
    let pq = new MinHeap();
    scoville.forEach((value) => pq.push(value));
    while(pq.size() > 1 && pq.getMin() < K) {
        const a = pq.pop();
        const b = pq.pop();
        pq.push(a + (b * 2));
        counter++;
    }
    return pq.getMin() >= K ? counter : -1;
}

class MinHeap {
    constructor() {
        this.heap = [null];
    }
    
    size() {
        return this.heap.length - 1;
    }
    
    swap(index1, index2) {
        [this.heap[index1], this.heap[index2]] = [this.heap[index2], this.heap[index1]];
    }
    
    getMin() {
        return this.heap[1];
    }
    
    push(value) {
        this.heap.push(value);
        
        let current = this.heap.length - 1;
        let parent= Math.floor(current / 2);
        
        while(current > 1 && this.heap[current] < this.heap[parent]) {
            this.swap(current, parent);
            current = parent;
            parent = Math.floor(current / 2);
        }
        
    }
    
    pop() {
        if(this.heap.length === 1) return undefined;
        if(this.heap.length === 2) return this.heap.pop();
        let min = this.heap[1];
        this.heap[1] = this.heap.pop();
        
        let current = 1;
        
        while(true) {
            let smallest = current;
            let left = current * 2;
            let right = current * 2 + 1;
            
            if(left < this.heap.length && this.heap[smallest] > this.heap[left]) smallest = left;
            if(right < this.heap.length && this.heap[smallest] > this.heap[right]) smallest = right;
            if(smallest !== current) {
                 this.swap(current, smallest);
                 current = smallest;
            } else break;
        }
        return min;
    }
}
