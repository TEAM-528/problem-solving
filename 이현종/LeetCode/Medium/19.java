/**
 * [LeetCode] 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * 접근 방식
 * 1) 전체 node 개수를 카운팅하고(total 변수에 저장) 몇 번째 노드가 제거되어야 하는지 구한다.
 * 2) singly linked list 이므로 prev 변수에 이전 node 주소를 저장한다.
 * 3) 제거되어야 하는 node를 찾았을 때, prev.next에 node.next 값을 저장한다.
 *  3-1) total과 주어진 n의 값이 같을 경우에는 prev가 null이 될 수 있으므로, 주어진 head.next를 반환한다.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private ListNode removeNode(ListNode node, int total, int n) {
        if (total == n) {
            return node.next;
        }
        int index = 1;
        ListNode head = node;
        ListNode prev = null;
        while (node.next != null && index < (total - n + 1)) {
            prev = node;
            node = node.next;
            index++;
        }
        prev.next = node.next;
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int total = 1;
        ListNode headNode = head;
        while (head.next != null) {
            head = head.next;
            total++;
        }
        return removeNode(headNode, total, n);
    }
}