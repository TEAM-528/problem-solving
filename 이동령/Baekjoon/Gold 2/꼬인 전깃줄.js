const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

const [N, poles] = input.map((str) => str.split(" ").map((e) => +e));
const LIS = [0];

const binarySearch = (left, right, value) => {
  let mid = Math.floor((left + right) / 2);
  if (right < left) return false;
  else if (LIS[mid] < value && value <= LIS[mid + 1]) return mid + 1;
  else
    return LIS[mid] < value
      ? binarySearch(mid + 1, right, value)
      : binarySearch(left, mid - 1, value);
};

poles.forEach((current) => {
  let index = binarySearch(0, LIS.length - 1, current);
  index === false ? LIS.push(current) : (LIS[index] = current);
});

console.log(N - LIS.length + 1);
