/**
 * [LeetCode] 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/description/
 *
 * 접근 방식
 * 1) 주어진 candidates 배열을 정렬하고, 백트래킹을 활용하여 문제를 해결한다.
 * 2) 문제에서 중복된 결과가 나오면 안된다고 했으므로, 백트래킹 시 index를 인자로 넘겨도 중복될 수 있는 경우는
 *    동일한 숫자가 동일한 인덱스에 배치된 경우가 이미 세어졌을 때이다.
 *  2-1) 따라서 combination 메서드의 for문에서, i가 시작 index보다 크면서 i-1 인덱스의 원소와 i 인덱스 원소가 같다면
 *       중복 결과가 발생할 수 있기 때문에 continue 해준다.
 * 3) 백트래킹 sum 인자 값과 target 값이 같다면 result 리스트에 추가해준다.
 */

class Solution {

    static List<List<Integer>> result;

    private void combination(List<Integer> list, int depth, int sum, int[] candidates, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i=depth; i<candidates.length; i++) {
            if (i > depth && candidates[i-1] == candidates[i]) {
                continue;
            }
            if (sum + candidates[i] > target) {
                break;
            }
            list.add(candidates[i]);
            combination(list, i+1, sum + candidates[i], candidates, target);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        Arrays.sort(candidates);
        combination(new ArrayList<>(), 0, 0, candidates, target);
        return result;
    }
}