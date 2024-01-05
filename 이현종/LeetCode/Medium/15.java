/**
 * [LeetCode] 15. 3Sum
 * https://leetcode.com/problems/3sum/description/
 *
 * 접근 방식
 * 1) 3개의 임의 숫자 합을 0으로 만들어야 하는 문제에서 완탐으로 풀게되면 O(N^3)인데, nums 배열 최대 길이가 3000이므로 1초를 가뿐히 넘는다.
 * 2) 입력 값으로 주어진 배열을 정렬하고, 3개 중 한 점을 고정시킨다.
 * 3) 이후 나머지 숫자들의 양쪽을 포인터로 위치시키고 합이 0이 될 때까지 두 포인터를 움직인다.
 * 4) 이때 문제에서 결과를 반환할 때 중복된 배열을 제외하라고 했으므로, 중복된 원소가 있을 때는 제외하고 진행한다.
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                }
                if (sum < 0) {
                    j++;
                }
                if (sum > 0) {
                    k--;
                }
            }
        }
        return result;
    }
}