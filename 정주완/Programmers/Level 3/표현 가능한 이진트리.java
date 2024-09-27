import java.util.Arrays;
import java.util.List;

class Solution {

    // 이진트리로 표현 가능한지 재귀적으로 확인하는 함수
    public static boolean isPossible(String binaryString) {
        int length = binaryString.length();

        // 길이가 1, 3, 7, 15, 31, 63이 아니면 앞에 '0' 추가
        List<Integer> validLengths = Arrays.asList(1, 3, 7, 15, 31, 63);
        if (!validLengths.contains(length)) {
            return isPossible("0" + binaryString);
        }

        // 루트 노드 확인 (문자열의 중간 값)
        int rootNode = length / 2;

        // 루트 노드가 더미 노드(0)인 경우
        if (binaryString.charAt(rootNode) == '0') {
            // 자식들도 모두 더미 노드(0)일 경우
            if (binaryString.chars().allMatch(ch -> ch == '0')) {
                return true;
            }
            // 자식 중 더미가 아닌 노드(1)가 있을 경우
            return false;
        }

        // 더 이상 나눌 수 없는 경우
        if (length == 1 || length == 3) {
            return true;
        }

        // 좌우 서브트리를 재귀적으로 검사
        boolean left = isPossible(binaryString.substring(0, rootNode));
        boolean right = isPossible(binaryString.substring(rootNode + 1));

        return left && right;
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);

            if (isPossible(binaryString)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
}
