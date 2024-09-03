package problem;

import java.util.*;

public class 올바른_괄호 {
	class Solution {
		boolean solution(String s) {
			Stack<Character> stack = new Stack<>();

			for(int i=0; i<s.length(); i++) {
				if (stack.isEmpty() && s.charAt(i)==')') {
					return false;
				}
				if (stack.isEmpty() || stack.peek() == s.charAt(i)) {
					stack.push(s.charAt(i));
					continue;
				}
				stack.pop();
			}

			if (!stack.isEmpty()) {
				return false;
			}

			return true;
		}
	}
}
