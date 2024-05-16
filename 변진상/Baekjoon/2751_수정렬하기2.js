// const qSort = (list) => {
//   const lessList = [];
//   let pivotValue = list[list.length - 1];
//   const greaterList = [];

//   if (list.length <= 1) {
//     return list;
//   }

//   for (let i = 0; i < list.length - 1; i++) {
//     if (list[i] > pivotValue) {
//       greaterList.push(list[i]);
//     } else {
//       lessList.push(list[i]);
//     }
//   }

//   return [...qSort(lessList), pivotValue, ...qSort(greaterList)];
// };

// // console.log(qSort([1, 3, 2, 4, 5, 7, 6]))

const fs = require("fs");
const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);
const N = stdin.splice(0, 1);

const qSort = (arr, left, right) => {
  if (left < right) {
    let i = position(arr, left, right);
    qSort(arr, left, i - 1);
    qSort(arr, i + 1, right);
  }

  return arr;
};

const position = (arr, left, right) => {
  let i = left;
  let j = right;
  let pivot = arr[left];

  while (i < j) {
    while (arr[j] > pivot) j--;
    while (i < j && arr[i] <= pivot) i++;

    tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  arr[left] = arr[j];
  arr[j] = pivot;

  return j;
};

console.log(qSort(stdin, 0, stdin.length - 1));
