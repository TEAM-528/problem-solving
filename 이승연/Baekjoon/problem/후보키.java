package Baekjoon.problem;
import java.util.*;

public class 후보키 {
	class Solution {
		public String[][] relationG;
		public ArrayList<Set<Integer>> keys = new ArrayList<>();
		public int answer = 0;
		public int solution(String[][] relation) {
			relationG = relation;

			for(int i=1; i<relation[0].length+1; i++) {
				combination(i, 0, new HashSet<>());
			}
			minimum();
			return answer;
		}

		public void minimum() {
			for(int i=0; i<keys.size(); i++) {
				boolean isMin = true;
				for(int j=0; j<keys.size(); j++) {
					if (i==j) continue;
					if (keys.get(i).containsAll(keys.get(j))) {
						isMin = false;
						break;
					}
				}

				if (isMin) answer++;
			}
		}

		public void combination(int n, int idx, Set<Integer> arr) {
			if (arr.size() == n) {
				if (isUnique(arr)) {
					keys.add(arr);
				}
				return;
			}
			if (idx == relationG[0].length) return;

			combination(n, idx+1, copy(arr));
			Set<Integer> copied = copy(arr);
			copied.add(idx);
			combination(n, idx+1, copied);
		}

		public Set<Integer> copy(Set<Integer> arr) {
			Set<Integer> result = new HashSet<>();
			for(int a : arr) {
				result.add(a);
			}

			return result;
		}

		public boolean isUnique(Set<Integer> arr) {
			HashSet<String> set = new HashSet<>();

			String[] values = new String[relationG.length];
			for(int i=0; i<relationG.length; i++) {
				String v = "";
				for(int a : arr) {
					v = v+relationG[i][a]+" ";
				}

				if (i!=0 && set.contains(v)) return false;
				set.add(v);
			}
			return true;
		}
	}
}
