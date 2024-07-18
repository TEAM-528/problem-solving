import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열_폭발 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exam = br.readLine();
    String exp = br.readLine();

    Stack<Character> stack = new Stack<>();
    for(int i=0; i<exam.length(); i++) {
      stack.push(exam.charAt(i));
      if (stack.size() >= exp.length()) {
        boolean hasExp = true;
        for(int j=0; j<exp.length(); j++) {
          if (stack.get(stack.size()-exp.length()+j) != exp.charAt(j)) {
            hasExp = false;
            break;
          }
        }

        if (hasExp) {
          for(int j=0; j<exp.length(); j++) {
            stack.pop();
          }
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for(Character c : stack) {
      sb.append(c);
    }
    System.out.println(sb.length()==0? "FRULA" : sb.toString());
  }
}
