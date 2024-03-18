package com.loco.leetcode.normal;

/**
 * @description:
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/3/6 20:23
 */

import com.loco.entity.ListNode;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 */

public class LC86SplitLinkedList {
    public static void main(String[] args) {
        LC86SplitLinkedList lc86SplitLinkedList = new LC86SplitLinkedList();
        ListNode listNode = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        lc86SplitLinkedList.partition(listNode, 3);
    }
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode large = new ListNode();
        while (head.next != null) {
            if (head.val >= x) {
                large.next = new ListNode(head.val);
            } else {
                small.next = new ListNode(head.val);
            }
            head = head.next;
        }
        System.out.println(small);
        System.out.println(large);
        return null;
    }
}
