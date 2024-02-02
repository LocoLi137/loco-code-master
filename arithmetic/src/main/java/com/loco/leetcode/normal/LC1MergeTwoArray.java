package com.loco.leetcode.normal;

/**
 * 合并两个有序数组
 */
public class LC1MergeTwoArray {
    public static void main(String[] args) {

    }

    /**
     * 双指针
     *
     * 时间复杂度：O(m+n): 指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)
     * 空间复杂度：O(m+n): 需要建立长度为 m+n 的中间数组 sorted
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int cur = 0;
        int[] sorted = new int[m + n];
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }

    /**
     * 逆向双指针
     *
     * 时间复杂度：O(m+n): 指针移动单调递减，最多移动 m+n 次，因此时间复杂度为 O(m+n)
     * 空间复杂度：O(1): 直接对数组 nums1原地修改，不需要额外空间。
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }


}
