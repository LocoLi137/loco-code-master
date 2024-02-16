package com.loco.entity;

public record TreeNode(int val, TreeNode left, TreeNode right) {
    // 构造器，接收所有字段
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    // 构造器，仅接收val字段
    public TreeNode(int val) {
        this(val, null, null);
    }
}
