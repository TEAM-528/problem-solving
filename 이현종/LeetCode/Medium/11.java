/**
 * [LeetCode] 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * 접근 방식
 * 1) n이 최대 100,000이기 때문에 완탐으로 풀게되면 O(n^2)로 1초를 넘게된다.
 * 2) 넓이를 최대로 가져가기 위해서는 가로 또는 세로를 늘려야한다.
 * 3) 세로 길이는 탐색해야 알 수 있으므로, 최대 가로길이 설정을 위해 양쪽 끝 원소들을 포인터로 설정한다. (투 포인터)
 * 4) 세로 길이가 짧은 포인터 부터 이동 시키면서 최대 넓이를 구한다.
 */

class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int startIdx = 0;
        int endIdx = height.length - 1;
        while (startIdx < endIdx) {
            max = Math.max(Math.min(height[startIdx], height[endIdx]) * (endIdx - startIdx), max);
            if (height[startIdx] <= height[endIdx]) {
                startIdx++;
            } else {
                endIdx--;
            }
        }
        return max;
    }
}