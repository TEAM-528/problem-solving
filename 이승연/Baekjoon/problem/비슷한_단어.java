package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class 비슷한_단어 {
	public static int N;
	public static ArrayList<Word> words = new ArrayList<>();
	public static HashMap<Integer, String> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for(int i=0; i<N; i++) {
			String w = br.readLine();
			words.add(new Word(w, i));
			map.put(i, w);
		}


		int s = 0;
		int t = 0;
		int length = Integer.MIN_VALUE;

		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int l = getStartLength(words.get(i).word, words.get(j).word);
				if (length < l) {
					s = words.get(i).index;
					t = words.get(j).index;
					length = l;
				}
				if (length == l) {
					int idx1 = words.get(i).index;
					int idx2 = words.get(j).index;
					if (idx1 > idx2) {
						int temp = idx1;
						idx1 = idx2;
						idx2 = temp;
					}
					if (idx1 < s) {
						s = idx1;
						t = idx2;
					}
					if (idx1 == s) {
						if (idx2 < t) {
							s = idx1;
							t = idx2;
						}
					}
				}
			}
		}

		System.out.println(map.get(s));
		System.out.println(map.get(t));
	}

	public static int getStartLength(String word1, String word2) {
		int length = 0;

		if (word1.equals(word2)) return length;

		int size = Math.min(word1.length(), word2.length());
		for(int i=0; i<size; i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				break;
			}
			length++;
		}
		return length;
	}
}

class Word {
	String word;
	int index;

	public Word(String word, int index) {
		this.word = word;
		this.index = index;
	}
}
