import java.util.*;

public class 길_찾기_게임 {
    class Solution {
        public boolean[][] tree;
        public ArrayList<Integer> pre = new ArrayList<>();
        public ArrayList<Integer> post = new ArrayList<>();
        public int[][] solution(int[][] nodeinfo) {
            int[][] answer = {};
            tree = new boolean[nodeinfo.length+1][nodeinfo.length+1];
            int[][] info = new int[nodeinfo.length][3];
            for(int i=0; i<nodeinfo.length; i++) {
                info[i][0] = nodeinfo[i][0];
                info[i][1] = nodeinfo[i][1];
                info[i][2] = i+1;
            }
            Arrays.sort(info, (o1, o2) -> {
                if (o1[1]==o2[1]) {
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            });

            Node root = new Node(info[0][2], info[0][0], info[0][1]);
            for(int i=1; i<info.length; i++) {
                insert(info[i][2], info[i][0], info[i][1], root);
            }

            preorder(root);
            postorder(root);

            answer = new int[2][nodeinfo.length];
            for(int i=0; i<pre.size(); i++) {
                answer[0][i] = pre.get(i);
            }
            for(int i=0; i<post.size(); i++) {
                answer[1][i] = post.get(i);
            }
            return answer;
        }
        public void insert(int num, int x, int y, Node node) {
            if (node.left == null && node.x>x) {
                node.left = new Node(num, x, y);
                return;
            } else if (node.right == null && node.x<x) {
                node.right = new Node(num, x, y);
                return;
            }

            if (node.x>x) {
                insert(num, x, y, node.left);
            } else if (node.x<x) {
                insert(num, x, y, node.right);
            }
        }

        public void preorder(Node node) {
            pre.add(node.index);
            if (node.left != null) {
                preorder(node.left);
            }
            if (node.right != null) {
                preorder(node.right);
            }
        }

        public void postorder(Node node) {
            if (node.left != null) {
                postorder(node.left);
            }
            if (node.right != null) {
                postorder(node.right);
            }
            post.add(node.index);
        }

        class Node {
            int x;
            int y;
            Node left;
            Node right;
            int index;

            public Node(int index, int x, int y) {
                this.x = x;
                this.y = y;
                this.left = null;
                this.right = null;
                this.index = index;
            }
        }
    }
}
