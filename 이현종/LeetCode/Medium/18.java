/**
 * [LeetCode] 18. 4Sum
 * https://leetcode.com/problems/4sum/description/
 *
 * 접근 방식
 * 1) 4개 원소의 합을 2개 원소 합 + 2개 원소 합으로 나누고, 각각의 합을 투 포인터를 활용하여 구한다.
 * 2) 문제에서 각 원소가 가질 수 있는 최대 값이 1,000,000,000 이므로 4개의 원소가 모두 최대 값이라면 int 범위를 넘어설 수 있다.
 *  2-1) 따라서 결과 List를 반환할 때 총 합을 int 타입과 long 타입으로 구한다음 합이 같은 지 판단하여 오버플로우가 됐는지 확인한다.
 */

class Solution {

    private List<List<Integer>> twoSum(int len, int index, int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int startIdx = index;
        int endIdx = nums.length - 1;
        while (startIdx < endIdx) {
            if (nums[startIdx] + nums[endIdx] < target) {
                startIdx++;
                continue;
            }
            if (nums[startIdx] + nums[endIdx] > target) {
                endIdx--;
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[startIdx]);
            temp.add(nums[endIdx]);
            result.add(temp);
            while (startIdx < endIdx && nums[startIdx] == nums[startIdx + 1]) {
                startIdx++;
            }
            while (startIdx < endIdx && nums[endIdx] == nums[endIdx - 1]) {
                endIdx--;
            }
            startIdx++;
            endIdx--;
        }
        return result;
    }

    private List<List<Integer>> divideList(int count, int index, int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (index >= nums.length) {
            return null;
        }
        if (count == 2) {
            List<List<Integer>> temp = twoSum(count, index, nums, target);
            return temp;
        }
        for (int i=index; i<nums.length - count + 1; i++) {
            List<List<Integer>> temp = divideList(count - 1, i + 1, nums, target - nums[i]);
            if (temp != null) {
                for (List<Integer> tmp : temp) {
                    tmp.add(0, nums[i]);
                    if (!isOverflow(tmp)) {
                        result.add(tmp);
                    }
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return result;
    }

    private boolean isOverflow(List<Integer> temp) {
        int value1 = 0;
        long value2 = 0;
        for (int value : temp) {
            value1 += value;
            value2 += value;
        }
        return (long)value1 != value2;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return divideList(4, 0, nums, target);
    }
}
