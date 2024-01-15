/**
 * [LeetCode] 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * 접근 방식
 * 1) 시간복잡도가 O(log n)이 나와야 하므로, 이진 탐색으로 주어진 target 값의 index를 찾는다.
 * 2) 양 끝 인덱스를 두 개의 포인터로 잡고, nums[mid] 값과 target의 대소 관계를 바탕으로 두 포인터를 적절히 움직인다.
 *  2-1) 이때, 주어진 nums 배열은 일부분만 정렬됐으므로 포인터를 움직이기 전에 정렬된 배열인지 검사 후 포인터를 움직인다.
 */

class Solution {
    public int search(int[] nums, int target) {
        int startIdx = 0;
        int endIdx = nums.length - 1;
        int mid = (startIdx + endIdx) / 2;
        while (startIdx < endIdx) {
            if (nums[startIdx] == target) {
                return startIdx;
            }
            mid = (startIdx + endIdx) / 2;
            if (nums[startIdx] <= nums[mid]) {
                if (nums[startIdx] < target && target <= nums[mid]) {
                    endIdx = mid;
                } else {
                    startIdx = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[endIdx]) {
                    startIdx = mid + 1;
                } else {
                    endIdx = mid;
                }
            }
        }
        return (nums[startIdx] == target) ? startIdx : -1;
    }
}