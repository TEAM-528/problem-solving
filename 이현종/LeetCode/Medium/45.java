/**
 * [LeetCode] 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * 접근 방식
 * 1) 현재 카운트(count)로 갈 수 있는 인덱스(end)와 최대로 갈 수 있는 인덱스(max)를 저장한다.
 * 2) 순차적으로 순회하면서, end와 max를 적절하게 갱신해준다.
 * 3) 최소의 카운트로 마지막 원소에 도달하면 해당 카운트를 반환한다.
 */

class Solution {
    public int jump(int[] nums) {
        int end = 0;
        int max = 0;
        int count = 0;
        for (int i=0; i<nums.length - 1; i++) {
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) {
                count++;
                break;
            }
            if (i == end) {
                end = max;
                count++;
            }
        }
        return count;
    }
}