/**
 * [LeetCode] 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * 접근 방식
 * 1) target의 시작 index와 종료 index를 찾는 메서드를 각각 만든다.
 * 2) 시작 index를 찾는 메서드는 이진 탐색을 활용하여 구하고, 반환된 index의 원소가 target과 다를 경우 -1을 반환한다.
 * 3) 마지막 index를 찾는 메서드 또한 이진 탐색을 활용하여 구하고, 반환된 index의 원소가 target보다 클 경우 index - 1을 반환한다.
 * 4) 시작 index가 -1일 때는 target 원소가 nums 배열에 없다고 판단하고 [-1, -1]을 반환하고, 아닐 때는 [시작 index, 마지막 index]를 반환한다.
 */

class Solution {
    private int getStartIndex(int[] nums, int target) {
        int startIdx = 0;
        int endIdx = nums.length - 1;
        int mid = (startIdx + endIdx) / 2;
        while (startIdx < endIdx) {
            mid = (startIdx + endIdx) / 2;
            if (nums[mid] < target) {
                startIdx = mid + 1;
            } else {
                endIdx = mid;
            }
        }
        return (nums[startIdx] == target) ? startIdx : -1;
    }

    private int getEndIndex(int[] nums, int target) {
        int startIdx = 0;
        int endIdx = nums.length - 1;
        int mid = (startIdx + endIdx) / 2;
        while (startIdx < endIdx) {
            mid = (startIdx + endIdx) / 2;
            if (nums[mid] <= target) {
                startIdx = mid + 1;
            } else {
                endIdx = mid;
            }
        }
        return (target < nums[startIdx]) ? startIdx - 1 : startIdx;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int startIdx = getStartIndex(nums, target);
        int endIdx = getEndIndex(nums, target);
        if (startIdx == -1) {
            return new int[] {-1, -1};
        }
        return new int[] {startIdx, endIdx};
    }
}
