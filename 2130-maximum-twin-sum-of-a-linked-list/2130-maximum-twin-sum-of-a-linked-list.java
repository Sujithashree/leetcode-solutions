class Solution {
public int pairSum(ListNode head) {

    // Step 1: Find the middle of the linked list
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // Step 2: Reverse the second half
    ListNode prev = null;
    ListNode curr = slow;
    while (curr != null) {
        ListNode nextNode = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextNode;
    }
    // Step 3: Calculate maximum twin sum
    int maxSum = 0;
    ListNode first = head;
    ListNode second = prev;
    while (second != null) {
        int sum = first.val + second.val;
        maxSum = Math.max(maxSum, sum);
        first = first.next;
        second = second.next;
    }
    return maxSum;
}

}