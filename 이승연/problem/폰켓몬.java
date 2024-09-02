package problem;

import java.util.*;

public class 폰켓몬 {
	public static void main(String[] args) {
		class Solution {
			public int solution(int[] nums) {
				int answer = 0;
				int n = nums.length/2;

				Set<Integer> set = new HashSet<>();
				for(int i=0; i<nums.length; i++) {
					set.add(nums[i]);
				}

				int size = set.size();
				if (size > n) {
					return n;
				}
				return size;
			}
		}
	}
}
