package com.loco.leetcode.dfs;

import com.loco.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DFS001LC094InorderTraversal {

    public static void main(String[] args) {

        DFS001LC094InorderTraversal main = new DFS001LC094InorderTraversal();

        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)));

        List<Integer> integers = main.inorderTraversal1(treeNode);

    }

    //迭代
    public List<Integer> inorderTraversal2(TreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();

        return res;
    }

    //递归
    public List<Integer> inorderTraversal1(TreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        dfs(res, root);
        return res;
    }

    private void dfs(ArrayList<Integer> res, TreeNode root) {
        if (root == null){
            return ;
        }

        dfs(res, root.left);
        res.add(root.val);
        dfs(res, root.right);
    }


}
