const COMMAND_REGEXP = /(?<method>[D|I])\s+(?<number>[-]?[\d]+)/;

const solution = (operations) => {
    let queue = new MinHeap();
    operations.forEach((command) => {
        let {method, number} = command.match(COMMAND_REGEXP).groups;
        if(method === "I") queue.push(+number);
        else +number === 1 ? queue.deleteMax() : queue.deleteMin();
        console.log(queue.heap);
    })
    return [queue.getMax(), queue.getMin()];
}

class MinHeap {
    constructor() {
        this.heap = [null];
    }

    swap(index1, index2) {
        [this.heap[index1], this.heap[index2]] = [this.heap[index2], this.heap[index1]];
    }

    push(value) {
        this.heap.push(value);
        let current = this.heap.length - 1;
        let parent = Math.floor(current / 2);
        
        while(current > 1 && this.heap[parent] > this.heap[current]) {
           this.swap(current, parent);
            current = parent;
            parent = Math.floor(current / 2);
        }
    }

    deleteMin() {
        if (this.heap.length === 1) return undefined;
        if (this.heap.length === 2) return this.heap.pop();
        const min = this.heap[1];
        this.heap[1] = this.heap.pop();

        let current = 1;

        while (true) {
            let left = 2 * current;
            let right = 2 * current + 1;
            let smallest = current;

            if (left < this.heap.length && this.heap[left] < this.heap[smallest]) smallest = left;
            if (right < this.heap.length && this.heap[right] < this.heap[smallest]) smallest = right;
            if (smallest !== current) {
                this.swap(current, smallest);
                current = smallest;
            } else break;
        }

        return min;
    }

    deleteMax() {
        if(this.heap.length === 1) return -1;
        else if(this.heap.length === 2) return this.heap.pop();
        let maxIdx = this.heap.indexOf(this.getMax());
        if(maxIdx === 1) return -1;
        this.swap(maxIdx, this.heap.length - 1);
        return this.heap.pop();
    }
    
    getMin() {
        return this.heap[1] || 0;
    }

    getMax() {
        if(this.heap.length === 1) return 0;
        let lowDepth = this.heap.slice(Math.floor((this.heap.length - 1) / 2) + 1);
        return Math.max(...lowDepth) || 0;
    }
}
