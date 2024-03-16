public class 불량_사용자 {
    import java.util.*;

    class Solution {
        public int n;
        public String[] USER_ID;
        public String[] BANNED_ID;
        HashSet<HashSet<String>> result = new HashSet<>();
        public int solution(String[] user_id, String[] banned_id) {
            n = banned_id.length;
            USER_ID = user_id;
            BANNED_ID = banned_id;

            dfs(0, new HashSet<>());

            return result.size();
        }

        public boolean match(String s1, String s2) {
            if (s1.length() != s2.length()) return false;

            for(int i=0; i<s1.length(); i++) {
                if (s2.charAt(i) == '*') continue;
                if (s1.charAt(i) != s2.charAt(i)) return false;
            }

            return true;
        }

        public void dfs(int idx, HashSet<String> set) {
            if (set.size() == n) {
                result.add(set);
                return;
            }

            for(int i=0; i<USER_ID.length; i++) {
                if (match(USER_ID[i], BANNED_ID[idx]) && !set.contains(USER_ID[i])) {
                    set.add(USER_ID[i]);
                    dfs(idx+1, new HashSet<>(set));
                    set.remove(USER_ID[i]);
                }
            }
        }
    }
}