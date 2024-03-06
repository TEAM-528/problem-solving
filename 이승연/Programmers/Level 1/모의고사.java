class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] person1 = new int[]{1,2,3,4,5};
        int[] person2 = new int[]{2,1,2,3,2,4,2,5};
        int[] person3 = new int[]{3,3,1,1,2,2,4,4,5,5};

        int ans1 = 0;
        int ans2 = 0;
        int ans3 = 0;

        for(int i=0; i<answers.length; i++) {
            if (person1[i%person1.length] == answers[i]) ans1++;
            if (person2[i%person2.length] == answers[i]) ans2++;
            if (person3[i%person3.length] == answers[i]) ans3++;
        }

        int maxValue = Math.max(ans1, ans2);
        maxValue = Math.max(maxValue, ans3);
        int count = 0;
        if (maxValue == ans1) {
            count++;
        }
        if (maxValue == ans2) {
            count++;
        }
        if (maxValue == ans3) {
            count++;
        }

        answer = new int[count];
        int idx = 0;
        if (maxValue == ans1) {
            answer[idx++] = 1;
        }
        if (maxValue == ans2) {
            answer[idx++] = 2;
        }
        if (maxValue == ans3) {
            answer[idx++] = 3;
        }
        return answer;
    }
}