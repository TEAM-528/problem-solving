const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, capacity, school] = stdin[0];
const coordsAndNumArr = stdin.slice(1);
const leftApartmentArr = [];
const rightApartmentArr = [];

coordsAndNumArr.forEach(([coord, num]) => {
  const dist = coord - school;
  if (dist > 0) {
    rightApartmentArr.push([dist, num]);
  } else if (dist < 0) {
    leftApartmentArr.push([Math.abs(dist), num]);
  }
});

rightApartmentArr.sort((a, b) => a[0] - b[0]);
leftApartmentArr.sort((a, b) => a[0] - b[0]);

let cnt = 0;

const go = (apartmentArr) => {
  let tmpCapacity = capacity;
  while (apartmentArr.length) {
    const [dist, num] = apartmentArr[apartmentArr.length - 1];

    if (num > tmpCapacity) {
      // 단지에 사람이 수용 인원보다 많을 때
      // 가능한 인원만 데려간다.
      apartmentArr[apartmentArr.length - 1][1] -= tmpCapacity;
    } else if (num === tmpCapacity) {
      // 인원이 같을 때
      // 다 데려간다.
      apartmentArr[apartmentArr.length - 1][1] = 0;
    } else {
      // 수용인원보다 작을 때
      let tmpSum = 0;
      for (let i = apartmentArr.length - 1; i >= 0; i--) {
        tmpSum += apartmentArr[i][1];

        if (tmpSum <= capacity) {
          apartmentArr[i][1] = 0;
        } else {
          apartmentArr[i][1] = tmpSum - capacity;
          break;
        }
      }
    }

    while (apartmentArr.length) {
      if (apartmentArr[apartmentArr.length - 1][1] === 0) {
        apartmentArr.pop();
      } else {
        break;
      }
    }

    cnt += dist * 2;
  }
};

go(rightApartmentArr);
go(leftApartmentArr);

console.log(cnt);
