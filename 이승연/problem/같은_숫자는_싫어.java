package problem;

import java.util.*;

public class 같은_숫자는_싫어 {
	public static void main(String[] args) {
		class Solution {
			public int[] solution(int []arr) {
				int[] answer = {};

				Stack<Integer> stack = new Stack<>();
				for(int i=0; i<arr.length; i++) {
					if (!stack.isEmpty() && stack.peek() == arr[i]) {
						continue;
					}
					stack.add(arr[i]);
				}

				answer = new int[stack.size()];
				for(int i=0; i<answer.length; i++) {
					answer[answer.length-1-i] = stack.pop();
				}

				return answer;
			}
		}
	}
}
