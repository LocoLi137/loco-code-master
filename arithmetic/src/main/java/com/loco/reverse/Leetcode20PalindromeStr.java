package com.loco.reverse;

public class Leetcode20PalindromeStr {
    public static void main(String[] args) {
        Leetcode20PalindromeStr str = new Leetcode20PalindromeStr();
        int heeh = str.countSubstrings("heeh");

    }

    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
