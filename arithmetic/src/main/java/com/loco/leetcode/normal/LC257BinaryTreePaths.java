package com.loco.leetcode.normal;

import com.loco.entity.ListNode;
import com.loco.entity.TreeNode;

import java.util.*;

/**
 * @description:
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/3/18 10:50
 */
public class LC257BinaryTreePaths {
    public static void main(String[] args) {
        LC257BinaryTreePaths main = new LC257BinaryTreePaths();

        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2,
                        null,
                        new TreeNode(4)),
                new TreeNode(3));

        List<String> strings = main.binaryTreePaths(treeNode);
        System.out.println(strings);
    }



    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> paths = new ArrayList<>();
        dfs(root, "", paths);
        return paths;
    }

    /**
     * dfs递归实现
     * 若当前节点 不是 叶子节点，则在当前路径末尾添加该节点，并继续递归遍历该节点的每一个子节点
     * 若当前节点 是 叶子节点，则在当前路径末尾添加该节点，得到本条路径
     */
    private void dfs(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder pathBuilder = new StringBuilder(path);
            pathBuilder.append(root.val());
            if (root.left() == null && root.right() == null) {
                //叶子节点，加入路径
                paths.add(pathBuilder.toString());
            } else {
                pathBuilder.append("->");
                dfs(root.left(), pathBuilder.toString(), paths);
                dfs(root.right(), pathBuilder.toString(), paths);
            }
        }
    }

    //dfs 迭代实现
    private void dfsByLoop(TreeNode root, String path, List<String> paths) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<String> pathStack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
            pathStack.push(path + root.val());
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            String currentPath = pathStack.pop();

            if (node.left() == null && node.right() == null) {
                //叶子节点，加入路径
                paths.add(currentPath);
            } else {
                StringBuilder pathBuilder = new StringBuilder(currentPath);
                pathBuilder.append("->");

                if (node.right() != null) {
                    stack.push(node.right());
                    pathStack.push(pathBuilder.toString() + node.right().val());
                }
                if (node.left() != null) {
                    stack.push(node.left());
                    pathStack.push(pathBuilder.toString() + node.left().val());
                }
            }
        }
    }


    /**
     * 广搜循环实现
     * @param root
     * @return
     */
    List<String> bfs(TreeNode root){
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val()));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left() == null && node.right() == null) {
                paths.add(path);
            } else {
                if (node.left() != null) {
                    nodeQueue.offer(node.left());
                    pathQueue.offer(path + "->" + node.left().val());
                }

                if (node.right() != null) {
                    nodeQueue.offer(node.right());
                    pathQueue.offer(path + "->" + node.right().val());
                }
            }
        }
        return paths;
    }
}
