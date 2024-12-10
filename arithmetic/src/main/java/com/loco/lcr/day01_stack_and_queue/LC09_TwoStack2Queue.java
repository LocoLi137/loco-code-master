package com.loco.lcr.day01_stack_and_queue;

import java.util.Stack;

/**
 * description: 两个栈实现队列
 * author: Loco.Li
 */
public class LC09_TwoStack2Queue<T> {
    private Stack<T> data = new Stack<>();
    private Stack<T> cur = new Stack<>();

    //出栈
    public void push(T value) {
        data.push(value);
    }

    //入栈
    public T pop(int value) {
        if (cur.isEmpty()) {
            while (!data.isEmpty()) {
                cur.push(data.pop());
            }
        }
        return cur.pop();
    }

}
