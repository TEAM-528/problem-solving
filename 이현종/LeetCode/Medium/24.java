/**
 * [LeetCode] 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 *
 * 접근 방식
 * 1) 먼저 주어진 ListNode에 원소가 아무 것도 없거나 한개만 존재할 때는 주어진 head를 반환한다.
 * 2) 결과 값으로 반환 할 headNode를 선언하고, 해당 변수에 주어진 head 레퍼런스 값을 저장해둔다.
 * 3) 두 원소의 위치를 바꿀 때, 필요한 총 원소는 3개이므로, 이전 노드를 저장할 prev 변수를 선언한다.
 *  3-1) prev, node, next 세 개의 원소가 있을 때, prev.next는 next를 바라보도록 하고 node.next는 next.next를 바라보고 next.next는 node를 바라보도록 한다.
 *  3-2) 첫 번째와 두 번째 원소를 바꿀 때는 prev가 null이 될 수 있다. 따라서 prev가 null이 될 때는 headNode를 node.next로 변경한다.
 * 4) swap을 완료하고 prev 변수에는 기존 head의 레퍼런스 값을 저장하고, head 변수에는 prev.next 레퍼런스 값을 저장한다.
 * 5) head.next 값이 null이 될 때까지 반복한다.
 * 6) headNode를 반환한다.
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

    static ListNode headNode;

    private ListNode swapNode(ListNode prev, ListNode node) {
        ListNode next = node.next;
        if (prev == null) {
            headNode = node.next;
        } else {
            prev.next = next;
        }
        node.next = next.next;
        next.next = node;
        return node;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        headNode = head;
        ListNode prev = null;
        while (head.next != null) {
            prev = swapNode(prev, head);
            head = prev.next;
            if (head == null) {
                break;
            }
        }
        return headNode;
    }
}