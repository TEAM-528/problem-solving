public class 베스트앨범 {
    import java.util.*;

    class Solution {
        public HashMap<String, Integer> total = new HashMap<>();
        public HashMap<String, ArrayList<int[]>> numbers = new HashMap<>();
        public int[] solution(String[] genres, int[] plays) {
            int[] answer = {};
            for(int i=0; i<genres.length; i++) {
                ArrayList<int[]> arr = numbers.getOrDefault(genres[i], new ArrayList<>());
                arr.add(new int[]{plays[i], i});
                numbers.put(genres[i], arr);

                int t = total.getOrDefault(genres[i], 0);
                total.put(genres[i], t+plays[i]);
            }

            List<String> totalKeys = new ArrayList<>(total.keySet());
            Collections.sort(totalKeys, (String o1, String o2) -> {
                return total.get(o2)-total.get(o1);
            });

            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0; i<totalKeys.size(); i++) {
                ArrayList<int[]> num = numbers.get(totalKeys.get(i));
                Collections.sort(num, (int[] o1, int[] o2) -> {
                    if (o1 == o2) {
                        return o1[1]-o2[1];
                    }
                    return o2[0]-o1[0];
                });
                for(int j=0; j<2; j++) {
                    if (j<num.size()) {
                        arr.add(num.get(j)[1]);
                    }
                }
            }

            answer = new int[arr.size()];
            for(int i=0; i<arr.size(); i++) {
                answer[i] = arr.get(i);
            }
            return answer;
        }
    }


}