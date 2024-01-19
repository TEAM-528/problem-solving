/**
 * [LeetCode] 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/description/
 *
 * 접근 방식
 * 1) 총 합을 나타내는 sum과 마지막으로 더해진 원소의 index를 인자로 넘겨 백트래킹을 활용하여 문제를 해결한다.
 *  1-1) 더해진 원소는 같지만 순서가 다른 중복 결과를 제외하기 위해 이전에 더해진 index와 같거나 큰 경우만 순차적으로 더한다.
 * 2) 특정 원소를 sum에 더했을 때 target 값과 같거나 target이 더 클 때만 진행한다.
 * 2) 총 합이 target과 같다면 최종 결과 List에 추가한다.
 */

class Solution {

    List<List<Integer>> result;

    private void combination(List<Integer> list, int index, int sum, int[] candidates, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i=index; i<candidates.length; i++) {
            if (target >= sum + candidates[i]) {
                list.add(candidates[i]);
                combination(list, i, sum + candidates[i], candidates, target);
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        combination(new ArrayList<>(), 0, 0, candidates, target);
        return result;
    }
}