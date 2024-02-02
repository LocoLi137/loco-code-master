package com.loco.normal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class Queue2Stack {

}


class MyStack<T> {
    private Queue<T> queue;
    private Queue<T> tempQueue;

    public MyStack() {
        queue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }

    public void push(T element) {
        queue.offer(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        while (queue.size() > 1) {
            tempQueue.offer(queue.poll());
        }
        T element = queue.poll();
        Queue<T> temp = queue;
        queue = tempQueue;
        tempQueue = temp;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        while (queue.size() > 1) {
            tempQueue.offer(queue.poll());
        }
        T element = queue.poll();
        tempQueue.offer(element);
        Queue<T> temp = queue;
        queue = tempQueue;
        tempQueue = temp;
        return element;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}