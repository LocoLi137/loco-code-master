package com.loco.lcr;

import java.util.Stack;

/**
 * description: 两个栈实现队列
 * author: Loco.Li
 */
public class CLR09TwoStack2Queue {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> cur = new Stack<>();

    public void push(int value) {
        data.push(value);
    }

}
