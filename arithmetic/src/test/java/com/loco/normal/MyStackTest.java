package com.loco.normal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    private MyStack<Integer> myStack = null;
    @BeforeEach
    void setUp() {
        myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void push() {
    }

    @Test
    void pop() {
        System.out.println(myStack.pop());
    }

    @Test
    void peek() {
    }

    @Test
    void isEmpty() {
    }
}