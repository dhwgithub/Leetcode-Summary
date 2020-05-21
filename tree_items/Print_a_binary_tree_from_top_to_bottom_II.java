package leetcode.tree_items;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：
 *      从上往下按层打印二叉树，同一层的节点从左往右，每一层打印一行（相当于层次遍历）。最后返回包含多层节点列表的列表
 * 思路1：
 *      广搜。初始情况下队列为空，然后存入根节点。并用临时列表存储每一层的节点值
 *      然后遍历当前队列所有元素，并依次添加入临时列表中，同时对于有子节点的节点再次添加入队列
 *      注意对象创建方式：List<List<Integer>> list = new ArrayList<List<Integer>>();
 * 思路2：
 *      深搜。利用level变量记录当前层数（第一层时level=0），然后深搜遍历
 *      当level大于等于列表大小时，表示这是新的一层，需要创建临时列表进行存储该层元素；小于时无需额外处理
 *      接着将当前层的元素存入列表，并搜索下一层
 */
class Print_a_binary_tree_from_top_to_bottom_II2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        getAns(root, list, 0);
        return list;
    }

    public void getAns(TreeNode root, List<List<Integer>> list, int level){
        if(root == null){
            return ;
        }
        if(level >= list.size()){
            list.add(new ArrayList<Integer>());
        }

        list.get(level).add(root.val);
        getAns(root.left, list, level+1);
        getAns(root.right, list, level+1);
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
class Print_a_binary_tree_from_top_to_bottom_II1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while( !queue.isEmpty()){
            int num = queue.size();
            List<Integer> temp = new ArrayList<Integer>();
            for(int i=0; i<num; i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            list.add(temp);
        }

        return list;
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
public class Print_a_binary_tree_from_top_to_bottom_II {
}
