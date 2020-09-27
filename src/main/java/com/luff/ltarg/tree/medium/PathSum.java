package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/26
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class PathSum {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res=new ArrayList<>();
        recursive(root,sum,new ArrayList<>(),res);
        return res;
    }


    public void recursive(TreeNode node,int remain,List<Integer> list,List<List<Integer>> res){
        if (node==null) return ;
        if (node.left==null && node.right==null){
            if (remain-node.val==0){
                list.add(node.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size()-1);
            }
            return ;
        }
        list.add(node.val);
        recursive(node.left,remain-node.val,list,res);
        recursive(node.right,remain-node.val,list,res);
        list.remove(list.size()-1);
    }
}
