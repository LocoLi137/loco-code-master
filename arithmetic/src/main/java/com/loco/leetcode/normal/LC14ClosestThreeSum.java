package com.loco.leetcode.normal;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @description:
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/3/18 9:34
 */
public class LC14ClosestThreeSum {
    public static void main(String[] args) {
        LC14ClosestThreeSum main = new LC14ClosestThreeSum();
        int i = main.threeSumClosest01(new int[]{-1, 2, 1, -4}, 1);
        System.out.println(i);

    }

    /**
     * 排序 + 双指针
     */
    public int threeSumClosest01(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length, res = 0, tempSum = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            for (int left = i + 1, right = length - 1; left < right; ) {
                int sum = nums[i] + nums[left] + nums[right];
                if (tempSum > Math.abs(target - sum)) {
                    tempSum = Math.abs(target - sum);
                    res = sum;
                }
                if (sum > target) {
                    right --;
                } else if (sum < target) {
                    left ++;
                } else {
                    break;
                }
            }
        }
        return res;
    }




}
