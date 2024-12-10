package com.loco.lcr.day02_linked_list;

import com.loco.entity.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 */
class LC06_ReversePrintLinkedListTest {

    LC06_ReversePrintLinkedList main = new LC06_ReversePrintLinkedList();

    private ListNode temp = new ListNode();
    private final ListNode node = temp;

    @BeforeEach
    void setUp() {
        int[] values = {1, 2, 3, 4};
        for (int i = 0; i < values.length; i++) {
            temp.next = new ListNode(values[i]);
            temp = temp.next;
        }

    }

    @Test
    void reversePrint1() throws InterruptedException {
        main.reversePrint1(node.next);
    }

    @Test
    void reversePrint2() {
        main.reversePrint2(node.next);
    }
}