package com.loco.reverse;

public class Leetcode9reverseNumber {
    public static void main(String[] args) {
        Leetcode9reverseNumber number = new Leetcode9reverseNumber();
        number.isPalindrome(121);
    }
    public boolean isPalindrome(int x) {
        //以符号和零结尾的一定不是回文数
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverseNumber = 0;
        while(x > reverseNumber) {
            reverseNumber = reverseNumber * 10 + x %10;
            x /= 10;
        }
        System.out.println(reverseNumber);
        System.out.println(x);
        return x == reverseNumber;
    }
}
