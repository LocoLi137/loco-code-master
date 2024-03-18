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
        ListNode partition = lc86SplitLinkedList.lc(listNode, 3);
        System.out.println(partition);
    }

    public ListNode lc(ListNode head, int x) {
        ListNode large = new ListNode();
        ListNode largeHead = large;
        ListNode small = new ListNode();
        ListNode smallHead = small;

        while (head != null) {
            if(head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }

        large.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }


    /**
     * 维护两个链表，将大于小于的分开放进链表，循环结束拼接链表
     * 时间复杂度：O(n) n是原链表长度
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;

        ListNode large = new ListNode();
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }
}
