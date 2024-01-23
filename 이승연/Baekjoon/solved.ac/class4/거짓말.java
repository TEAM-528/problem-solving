import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 거짓말 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NandM = br.readLine().split(" ");
        int N = Integer.parseInt(NandM[0]);
        int M = Integer.parseInt(NandM[1]);

        String[] peoples = br.readLine().split(" ");
        ArrayList<Integer> knowingPeople = new ArrayList<>();
        for(int i=0; i<Integer.parseInt(peoples[0]); i++) {
            knowingPeople.add(Integer.parseInt(peoples[i+1]));
        }

        HashMap<Integer, ArrayList<Integer>> parties = new HashMap<>(); // 사람마다 참석한 파티들
        HashMap<Integer, ArrayList<Integer>> people = new HashMap<>(); // 파티마다 참석한 사람들
        for(int i=0; i<M; i++) {
            String[] party = br.readLine().split(" ");
            ArrayList<Integer> peopleList = new ArrayList<>();
            for(int j=0; j<Integer.parseInt(party[0]); j++) {
                int person = Integer.parseInt(party[j+1]);
                ArrayList<Integer> set = parties.getOrDefault(person, new ArrayList<>());
                peopleList.add(person);
                set.add(i+1);
                parties.put(person, set);
            }
            people.put(i+1, peopleList);
        }

        System.out.println(bfs(N, M, parties, people, knowingPeople));
        
        
    }

    public static int bfs(int N, int M, HashMap<Integer, ArrayList<Integer>> parties, HashMap<Integer, ArrayList<Integer>> people, ArrayList<Integer> knowingPeople) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        boolean[] visitedParty = new boolean[M+1];

        for(int i=0; i<knowingPeople.size(); i++) {
            Integer p = knowingPeople.get(i);
            queue.add(p);
            visited[p] = true;
        }

        while(!queue.isEmpty()) {
            Integer person = queue.poll();
            ArrayList<Integer> partyList = parties.get(person); // 이 사람이 간 파티 리스트

            if (partyList == null) {
                continue;
            }

            for(int i=0; i<partyList.size(); i++) {
                Integer party = partyList.get(i); 
                visitedParty[party] = true;
                ArrayList<Integer> peopleList = people.get(party); // 파티에 있었던 사람들 리스트 -> 소문 알고 있음
        
                for(int j=0; j<peopleList.size(); j++) {
                    if (!visited[peopleList.get(j)]) {
                        queue.add(peopleList.get(j));
                        visited[peopleList.get(j)] =  true;
                    }
                }
            }
        }

        int count = 0;
        for(int i=1; i<visitedParty.length; i++) {
            if (!visitedParty[i]) {
                count++;
            }
        }

        return count;
    }
}