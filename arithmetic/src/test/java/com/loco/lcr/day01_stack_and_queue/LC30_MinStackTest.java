package com.loco.lcr.day01_stack_and_queue;

import org.junit.jupiter.api.Test;


/**
 * @description:
 */
class LC30_MinStackTest {
    @Test
    void MinStack() {
        LC30_MinStack minStack = new LC30_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();//--> 返回 -3.
        minStack.pop();
        minStack.top(); //--> 返回 0.
        minStack.min(); //--> 返回 -2.
    }
}