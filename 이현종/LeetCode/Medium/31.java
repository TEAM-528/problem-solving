/**
 * [LeetCode] 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/description/
 *
 * 접근 방식
 * 1) 특정 index의 원소 값이 (index+1)을 index로 가지는 원소 값보다 작다면 해당 index에서 swap을 통해 더 큰 수로 바꿀 수 있다는 뜻이다.
 * 2) nums로 주어진 값보다 크면서 가장 작은 값을 찾아야 하므로, 큰 index부터 작은 index로 1번 조건을 만족하는 원소 하나를 찾는다.
 * 3) 만약 못찾았다면 주어진 nums가 가장 큰 값이라는 뜻이므로, nums 배열을 오름차순으로 정렬 후 반환한다.
 * 4) 만약 찾았다면, 해당 index에서 swap이 이루어저야 한다는 뜻이다.
 *  4-1) 해당 index의 원소 값보다 크지만 가장 작은 원소의 인덱스(otherIndex)를 index + 1부터 nums 배열의 마지막 index에서 찾는다.
 *  4-2) index의 원소와 otherIndex의 원소를 swap한다.
 *  4-3) index + 1부터 nums 배열의 마지막 index 원소까지만 오름차순으로 정렬한다.
 *  4-4) nums 배열을 반환한다.
 */

class Solution {

    private void swap(int index1, int index2, int[] nums) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
        Arrays.sort(nums, index1 + 1, nums.length);
    }

    private void findSwapIndex(int index, int[] nums) {
        int otherIndex = index;
        int otherValue = 101;
        for (int i=index + 1; i<nums.length; i++) {
            if (nums[i] > nums[index] && otherValue > nums[i]) {
                otherValue = nums[i];
                otherIndex = i;
            }
        }
        swap(index, otherIndex, nums);
    }

    public void nextPermutation(int[] nums) {
        for (int i=nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                findSwapIndex(i, nums);
                return;
            }
        }
        Arrays.sort(nums);
    }
}