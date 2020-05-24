package leetcode.depth_first_search_items;

import java.util.Arrays;

/**
 *  题意：
 *      给定一个有序整数数组（元素各不相同且升序），构建一个高度最小的二叉搜索树
 *  思路：
 *      二叉搜索树的特点是根节点比左子树节点都大，比右子树节点都小。由于给定的是升序数组
 *      因此可以使得根节点为该数组中点值，左子树为该数组前半段生成的子树，右子树同理
 *      递归实现，其中 Minimum_height_tree2 效率较高
 */
class Minimum_height_tree2 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        return subToBST(nums, 0, nums.length - 1);
    }

    public TreeNode subToBST(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        if(left == right){
            return new TreeNode(nums[left]);
        }

        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = subToBST(nums, left, mid - 1);
        root.right = subToBST(nums, mid + 1, right);
        return root;
    }
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

class Minimum_height_tree1 {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return null;
        }
        TreeNode root = new TreeNode(nums[len / 2]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, len/2));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, len/2 + 1, len));
        return root;
    }
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

public class Minimum_height_tree {
}
