package com.loco.lcr.day01_stack_and_queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <b>题目：</b>
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1) 。
 * <p/>
 * <b>思路：</b>
 * <p>
 * 辅助栈，辅助栈存储当前栈的最小值，每次入栈时，判断当前栈的最小值是否小于等于当前入栈的值，若小于等于，则将当前值入栈，否则将当前栈的最小值入栈。
 * <p/>
 * <b>应用场景：</b>
 * <p>
 * 需频繁获取最小值，如数据分析场景、DP
 * </p>
 * <b>举一反三：</b>
 * <p>
 * TODO:最大栈、技术栈、优先队列
 * </p>
 */
public class LC30_MinStack {

    private final Deque<Integer> xStack, minStack;

    public LC30_MinStack() {
        xStack  = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }
    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }
    public void pop() {
        xStack.pop();
        minStack.pop();
    }
    public int top() {
        return xStack.peek();
    }
    public int min() {
        return minStack.peek();
    }
}



