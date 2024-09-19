package com.loco.lcr;

import java.util.Stack;

/**
 * description: 两个栈实现队列
 * author: Loco.Li
 */
public class CLR09TwoStack2Queue<T> {
    private Stack<T> data = new Stack<>();
    private Stack<T> cur = new Stack<>();

    public void push(T value) {
        data.push(value);
    }

    public T pop(int value) {
        if (cur.isEmpty()) {
            while (!data.isEmpty()) {
                cur.push(data.pop());
            }
        }
        return cur.pop();
    }

}
