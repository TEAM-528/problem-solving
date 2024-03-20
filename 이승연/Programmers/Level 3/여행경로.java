public class 여행경로 {
    import java.util.*;

    class Solution {
        int n;
        ArrayList<String> ans = new ArrayList<>();
        boolean[] visited;
        String[][] TICKETS;
        public String[] solution(String[][] tickets) {
            n = tickets.length;
            visited = new boolean[n];
            TICKETS = tickets;
            dfs("ICN", 0, "ICN");

            Collections.sort(ans);
            return ans.get(0).split(" ");
        }

        public void dfs(String city, int ticket, String cities) {
            if (n == ticket) {
                ans.add(cities);
                return;
            };

            for(int i=0; i<TICKETS.length; i++) {
                if (TICKETS[i][0].equals(city) && !visited[i]) {
                    visited[i] = true;
                    dfs(TICKETS[i][1], ticket+1, cities+" "+TICKETS[i][1]);
                    visited[i] = false;
                }
            }
        }
    }
}