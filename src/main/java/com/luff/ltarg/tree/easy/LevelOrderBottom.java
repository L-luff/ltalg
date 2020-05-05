package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname LevelOrderBottom
 * @Description
 * @Date 2020/5/3 18:44
 * @Created by li
 */

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res=new LinkedList<>();
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()){
            int size=list.size();
            List<Integer> tmp=new ArrayList<>(size);
            for (int i=0;i<size;i++){
                TreeNode node = list.pollFirst();
                if(node!=null){
                    tmp.add(node.val);
                    if(node.left!=null){
                        list.add(node.left);
                    }
                    if(node.right!=null){
                        list.add(node.right);
                    }
                }
            }
            if(tmp.size()>0){
                res.push(tmp);
            }
        }
        return res;
    }
}
