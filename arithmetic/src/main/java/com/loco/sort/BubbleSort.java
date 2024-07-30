package com.loco.sort;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/4/2 11:45
 */
public class BubbleSort  {
    public static void main(String[] args) {
        int i;
        int[] a = {20,40,30,10,60,50};

        System.out.printf("before sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

//        bubbleSort1(a, a.length);
        bubbleSort2(a, a.length);

        System.out.printf("after  sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }

    /*
     * 冒泡排序
     * 参数说明:
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void bubbleSort1(int[] a, int n) {
        int left, right;

        for (right = 0; right <= n - 1; right ++) {
            for (left = 0; left < right; left ++) {
                if (a[left] < a[right]) {
                    int temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                }
            }
        }
    }


    /*
     * 冒泡排序(改进版)
     *
     * 参数说明:
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void bubbleSort2(int[] a, int n) {
        int i,j;
        int flag;                 // 标记

        for (i=n-1; i>0; i--) {

            flag = 0;            // 初始化标记为0
            // 将a[0...i]中最大的数据放在末尾
            for (j=0; j<i; j++) {
                if (a[j] > a[j+1]) {
                    // 交换a[j]和a[j+1]
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    flag = 1;    // 若发生交换，则设标记为1
                }
            }

            if (flag==0)
                break;            // 若没发生交换，则说明数列已有序。
        }
    }

}
