function solution(order) {
    let conveyor = 1, truck = 0, index = 0;
    let subConveyor = [];
    while(conveyor <= order.length || subConveyor.length) {
        if(conveyor === order[index]) {
            truck++;
            conveyor++;
            index++;
        }
        else if(subConveyor.at(-1) === order[index]) {
            subConveyor.pop();
            truck++;
            index++;
        }
        else {
            if(conveyor > order.length) break;
            subConveyor.push(conveyor++);
        }
    }
    return truck;
}
