/**
 * [LeetCode] 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * 접근 방식
 * 1) 키패드 숫자에 매핑되는 문자열을 map에 저장해두고, 입력 값으로 주어진 digits이 빈 문자열일 경우 빈 List를 반환한다.
 * 2) 백트래킹을 활용하여 digits의 문자들을 순차적으로 순회하고, 조합된 문자열을 모두 반환한다.
 */

class Solution {

    static List<String> result;
    static Map<Integer, String> mapper;
    static String[] digitList = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "xwyz"};

    private void init() {
        mapper = new HashMap<>();
        for (int i=2; i<=9; i++) {
            mapper.put(i, digitList[i]);
        }
    }

    private static void combination(int depth, StringBuilder letter, String digits) {
        if (depth == digits.length()) {
            result.add(letter.toString());
            return;
        }
        String digit = mapper.get(digits.charAt(depth) - '0');
        for (int i=0; i<digit.length(); i++) {
            letter.append(digit.charAt(i));
            combination(depth + 1, letter, digits);
            letter.deleteCharAt(letter.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        init();
        combination(0, new StringBuilder(), digits);
        return result;
    }
}