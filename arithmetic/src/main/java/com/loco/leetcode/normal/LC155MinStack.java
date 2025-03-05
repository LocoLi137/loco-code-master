package com.loco.leetcode.normal;

import java.util.Deque;
import java.util.LinkedList;

public class LC155MinStack {

    public static void main(String[] args) {
        LC155MinStack minStack = new LC155MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        minStack.getMin();
        minStack.pop();
        System.out.println("min=" + minStack.getMin());
        minStack.pop();
        System.out.println("min=" + minStack.getMin());
        minStack.pop();
        System.out.println("min=" + minStack.getMin());

    }

    Deque<Integer> stack;
    Deque<Integer> minStack;


    public LC155MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    //将元素val推入堆栈。
    void push(int val){
        stack.push(val);
        // 维护 minStk 栈顶为全栈最小元素
        if (minStack.isEmpty() || val <= minStack.peek()) {
            // 新插入的这个元素就是全栈最小的
            minStack.push(val);
        } else {
            // 插入的这个元素比较大
            minStack.push(minStack.peek());
        }
    }

    //删除堆栈顶部的元素。
    void pop(){
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop(); // 确保每次pop都从minStack中移除顶部
        }
    }

    //获取堆栈顶部的元素。
    int top(){
        return stack.peek();
    }

    //获取堆栈中的最小元素。
    int getMin(){
        return minStack.peek();
    }

}
