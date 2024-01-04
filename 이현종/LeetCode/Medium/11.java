/**
 * LeetCode
 * 11. Container With Most Water
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