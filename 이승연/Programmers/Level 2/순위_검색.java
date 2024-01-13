import java.util.*;

class Solution {
    HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i=0; i<info.length; i++) {
            String[] splited = info[i].split(" ");
            String language = splited[0];
            String part = splited[1];
            String career = splited[2];
            String food = splited[3];
            Integer score = Integer.parseInt(splited[4]);
            
            getPossibleCase(new String[]{language,part,career,food}, 0, "", score);
        }
        
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        for(int i=0; i<query.length; i++) {
            String value = "";
            
            String[] splited = query[i].split(" ");
            for(int j=0; j<splited.length-1; j++) {
                if (splited[j].equals("and")) {
                    continue;
                }
                value += splited[j];
            }
        
            ArrayList<Integer> possibleCase = map.getOrDefault(value, null);
            if (possibleCase == null) {
                answer[i] = 0;
                continue;
            }
            Integer score = Integer.parseInt(splited[splited.length-1]);

            int start = 0;
            int end = possibleCase.size()-1;
        
            while(start <= end) {
                int middle = (start+end)/2;
                if (possibleCase.get(middle) < score) {
                    start = middle+1;
                } else {
                    end = middle-1;
                }
                answer[i] = possibleCase.size()-start;
            }
        }
        
        return answer;
    }
    
    public void getPossibleCase(String[] infos, int idx, String info, int score) {
        if (idx == 4) {
            if (map.getOrDefault(info, null) == null) {
                ArrayList<Integer> newAL = new ArrayList<>();
                newAL.add(score);
                map.put(info, newAL);
            } else {
                map.get(info).add(score);   
            }
            return;
        }
        getPossibleCase(infos, idx+1, info+infos[idx], score);
        getPossibleCase(infos, idx+1, info+"-", score);
    }
}
