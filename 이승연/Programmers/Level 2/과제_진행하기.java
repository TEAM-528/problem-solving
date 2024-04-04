import java.util.*;

class Solution1 {
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

class Solution2 {
    public Stack<String[]> stack = new Stack<>();
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Arrays.sort(plans, (String[] o1, String[] o2) -> {
            int[] time1 = parseTime(o1[1]);
            int[] time2 = parseTime(o2[1]);

            if (time1[0]==time2[0]) return time1[1]-time2[1];
            return time1[0]-time2[0];
        });

        int idx = 0;
        for(int i=0; i<plans.length; i++) {
            if (i==plans.length-1) {
                answer[idx++] = plans[i][0];
                continue;
            }

            int[] time = parseTime(plans[i][1]);
            int[] endTime = addTime(time, plans[i][2]);

            int[] next = parseTime(plans[i+1][1]);
            if ((endTime[0]<next[0]) || (endTime[0]==next[0] && endTime[1]<=next[1])) {
                answer[idx++] = plans[i][0];

                int leftTime = (next[0]-endTime[0])*60+(next[1]-endTime[1]);
                while(leftTime > 0 && !stack.isEmpty()) {
                    String[] task = stack.pop();
                    int taskTime = Integer.parseInt(task[1]);
                    if (leftTime >= taskTime) {
                        leftTime -= taskTime;
                        answer[idx++] = task[0];
                    } else {
                        stack.push(new String[]{task[0], String.valueOf(taskTime-leftTime)});
                        leftTime = 0;
                    }
                }
                continue;
            }

            int leftTime = endTime[0]*60+endTime[1] - (next[0]*60+next[1]);
            stack.push(new String[]{plans[i][0], String.valueOf(leftTime)});

        }


        while(!stack.isEmpty()) {
            String[] task = stack.pop();
            answer[idx++] = task[0];
        }

        return answer;
    }

    public int[] parseTime(String time) {
        String[] parsed = time.split(":");

        Integer h = Integer.parseInt(parsed[0]);
        Integer m = Integer.parseInt(parsed[1]);

        return new int[]{h, m};
    }

    public int[] addTime(int[] now, String time) {
        Integer t = Integer.parseInt(time);

        int h = now[0]+(now[1]+t)/60;
        int m = (now[1]+t)%60;

        return new int[]{h ,m};
    }
}