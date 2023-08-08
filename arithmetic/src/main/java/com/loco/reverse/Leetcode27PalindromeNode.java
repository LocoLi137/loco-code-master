package com.loco.reverse;

import com.loco.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode27PalindromeNode {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(2,
                                new ListNode(1))));
        Leetcode27PalindromeNode node = new Leetcode27PalindromeNode();
        System.out.println(node.isPalindrome(listNode));
    }

    /**
     * 将node放入数组 以左右指针方式对比镜面node值是否相等
     * @param head Node节点
     * @return 是否为回文链表
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode dummy = head;

        while (dummy != null) {
            list.add(dummy.val);
            dummy = dummy.next;
        }

        int left = 0, right = list.size() - 1;
        while(left < right) {
            if (! list.get(left).equals(list.get(right))) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
