import java.util.*;
public class 다단계_칫솔_판매 {
    class Solution {
        public HashMap<String, Integer> enrolls = new HashMap<>();
        public HashMap<String, String> graph = new HashMap<>();
        public int n;
        public int[] benefit;
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int[] answer = {};

            for(int i=0; i<enroll.length; i++) {
                enrolls.put(enroll[i], i);
            }


            for(int i=0; i<referral.length; i++) {
                if (!referral[i].equals("-")) {
                    graph.put(enroll[i], referral[i]);
                }
            }

            benefit = new int[enroll.length];
            n = enroll.length;
            for(int i=0; i<seller.length; i++) {
                dfs(seller[i], amount[i]*100);
            }

            return benefit;
        }

        public void dfs(String seller, int amount) {
            if (amount == 0) return;

            String next = graph.getOrDefault(seller, "");
            int position = enrolls.get(seller);
            if (next.equals("")) {
                int division = amount/10;
                benefit[position] += (amount-division);
                return;
            }

            int division = amount/10;
            benefit[position] += (amount-division);
            dfs(next, division);


        }
    }
}