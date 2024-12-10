package com.loco.lcr.day02_linked_list;

import com.loco.entity.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * <b>题目：</b> 从尾到头打印链表
 * <p>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p/>
 * <b>思路：</b>
 * <p>
 * 见方法注释
 * <p/>
 */
public class LC06_ReversePrintLinkedList {

    /**
     * 入栈反序->入队列
     */
    public void reversePrint1(ListNode node){
        Stack<Integer> stack = new Stack<>();
        ListNode dummy = node;
        while (dummy != null) {
            stack.push(dummy.val);
            dummy = dummy.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (! stack.isEmpty()) {
            list.add(stack.pop());
        }

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 递归，先找到尾结点，返回时打印
     * 缺点：数值过大时内存占用高
     */
    public void reversePrint2(ListNode node){
        if (node == null) {
            return;
        }
        reversePrint2(node.next);
        System.out.println(node.val);
    }



}
