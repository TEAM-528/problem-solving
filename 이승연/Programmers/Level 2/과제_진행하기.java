import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        
        for(int i=0; i<plans.length; i++) {
            String[] parsedTime = plans[i][1].split(":");
            int hour = Integer.parseInt(parsedTime[0]);
            int minute = Integer.parseInt(parsedTime[1]);
            
            plans[i][1] = String.valueOf(60*hour+minute);
            plans[i][2] = String.valueOf(60*hour+minute+Integer.parseInt(plans[i][2]));
        }
        Arrays.sort(plans, (String[] o1, String[] o2) -> {
            if (Integer.parseInt(o1[1]) < Integer.parseInt(o2[1])) {
                return -1;
            }
            
            return 1;
        });
        
        ArrayList<Assign> left = new ArrayList<>();
        int now = Integer.parseInt(plans[0][1]);
        left.add(new Assign(plans[0][0], Integer.parseInt(plans[0][2])-now));
        for(int i=1; i<plans.length; i++) {
            int time = Integer.parseInt(plans[i][1]);
            if (left.size() == 0) {
                now = Integer.parseInt(plans[i][1]);
                left.add(new Assign(plans[i][0], Integer.parseInt(plans[i][2])-now));
                continue;
            }
            Assign hw = left.get(left.size()-1); 
            if (now+hw.getLeftTime() <= time) {
                answer[idx++] = hw.getSubject();
                left.remove(left.size()-1);
                now = now+hw.getLeftTime();
                i--;
                continue;
            }
            
            left.remove(left.size()-1);
            left.add(new Assign(hw.getSubject(), hw.getLeftTime()-(time-now)));
            now = Integer.parseInt(plans[i][1]);
            left.add(new Assign(plans[i][0], Integer.parseInt(plans[i][2])-Integer.parseInt(plans[i][1])));
        
        }
        
    
        for(int i=left.size()-1; i>=0; i--) {
            if (idx < answer.length) {
                answer[idx++] = left.get(i).getSubject();   
            }
        }
        return answer;
    }
    
    class Assign {
        String subject;
        int leftTime;
        
        public Assign(String subject, int leftTime) {
            this.subject = subject;
            this.leftTime = leftTime;
        }
        
        public void print(){
            System.out.println(subject+" "+leftTime);
        }
        
        public String getSubject() {
            return subject;
        }
        
        public int getLeftTime() {
            return leftTime;
        }
    }
}
