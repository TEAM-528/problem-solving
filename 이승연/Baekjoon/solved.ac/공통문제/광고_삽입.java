import java.util.*;
public class 광고_삽입 {

  public static void main(String[] args) {
    class Solution {
      public String solution(String play_time, String adv_time, String[] logs) {
        int[] time = new int[360000];
        long pt = stringToLong(play_time);
        long at = stringToLong(adv_time);

        for(int i=0; i<logs.length; i++) {
          String[] parsed = logs[i].split("-");
          int start = (int)stringToLong(parsed[0]);
          int end = (int)stringToLong(parsed[1]);

          for(int j=start; j<end; j++) {
            time[j]++;
          }
        }

        long maxTime = 0;
        long answer = 0;
        long startTime = 0;
        for(int i=0; i<at; i++) {
          maxTime += time[i];
        }
        answer = maxTime;
        for(int i=(int)at; i<=pt; i++) {
          maxTime = maxTime-time[i-(int)at]+time[i];
          if (maxTime > answer) {
            answer = maxTime;
            startTime = i-at+1;
          }
        }
        return longToString(startTime);
      }

      public long stringToLong(String time) {
        String[] parsed = time.split(":");
        long hour = Long.parseLong(parsed[0]);
        long minute = Long.parseLong(parsed[1]);
        long second = Long.parseLong(parsed[2]);

        return hour*3600 + minute*60 + second;
      }

      public String longToString(long time) {
        String hour = String.valueOf(time/3600);
        time %= 3600;
        String minute = String.valueOf(time/60);
        time %= 60;
        String second = String.valueOf(time);

        if (hour.length() == 1) {
          hour = "0"+hour;
        }
        if (minute.length() == 1) {
          minute = "0"+minute;
        }
        if (second.length() == 1) {
          second = "0"+second;
        }

        return hour+":"+minute+":"+second;
      }
    }
  }
}
