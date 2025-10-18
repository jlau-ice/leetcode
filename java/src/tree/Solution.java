package tree;

import model.TreeNode;

import java.sql.Connection;
import java.util.*;

public class Solution {


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }


    public void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(100);
        root.left.left.right = new TreeNode(1000);
        List<Integer> integers = inorderTraversal(root);
        System.out.println(integers);
    }


}
