/**
 * [LeetCode] 16. 3Sum Closest
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * 접근 방식
 * 1) 임의의 3개 원소를 합한 값과 target의 차이가 가장 적은 값을 반환해야 하므로
 * 2) 입력으로 받은 nums 배열을 정렬 후, 3개 원소 중 하나를 고정시킨다.
 * 3) 투 포인터를 활용하여 배열의 나머지 값 들중 target과 차이가 적은 두 원소를 적절하게 찾는다.
 *  3-1) 총합이 target보다 작을 때는 투 포인터의 시작 포인터를 증가시킨다. 반대일 때는 투 포인터의 마지막 포인터를 감소시킨다.
 */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int closetSum = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int idx=0; idx<nums.length - 2; idx++) {
            int startIdx = idx + 1;
            int endIdx = nums.length - 1;
            while (startIdx < endIdx) {
                int sum = nums[idx] + nums[startIdx] + nums[endIdx];
                if (Math.abs(target - closetSum) > Math.abs(target - sum)) {
                    closetSum = sum;
                }
                if (target - sum == 0) {
                    break;
                }
                if (target - sum > 0) {
                    startIdx++;
                }
                if (target - sum < 0) {
                    endIdx--;
                }
            }
            if (closetSum == target) {
                return closetSum;
            }
        }
        return closetSum;
    }
}