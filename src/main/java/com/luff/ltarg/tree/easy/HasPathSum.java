package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname HasPathSum
 * @Description
 * @Date 2020/4/22 21:46
 * @Created by li
 */
public class HasPathSum {

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     * @param root
     * @param sum
     * @return
     */

    public boolean hasPathSum(TreeNode root, int sum) {
        return recursiveResolve(root,0,sum);
    }


    /**
     * 与target sum相减，然后在叶节点判断是否等于0
     * @param node
     * @param remain
     * @return
     */
    public boolean recursiveResolve2(TreeNode node,int remain){
        if(node==null) return false;
        remain-=node.val;
        if(node.left==null && node.right==null){
            return remain==0;
        }
        return recursiveResolve2(node.left,remain)
                ||recursiveResolve2(node.right,remain);
    }

    /**
     * 节点相加，然后判断与target是否相等
     * @param node
     * @param sums
     * @param targetSum
     * @return
     */
    public boolean recursiveResolve(TreeNode node,int sums,int targetSum){
        if(node==null) return false;
        if(node.left==null && node.right==null){
            if(targetSum==(sums+node.val)){
                return true;
            }
        }

        return recursiveResolve(node.left,sums+node.val,targetSum)
                || recursiveResolve(node.right,sums+node.val,targetSum);
    }

    // 广度有限搜索
    public boolean solution2(TreeNode root,int sum){
        if (root==null){
            return false;
        }

        SumNode sumNode=new SumNode(root,root.val);
        LinkedList<SumNode> queue=new LinkedList<>();
        queue.add(sumNode);
        while (!queue.isEmpty()){
            int size=queue.size();

            for (int i=0;i<size;i++){
                SumNode node = queue.pop();
                if (node.node.left==null && node.node.right==null && node.sum==sum){
                    return true;
                }else{
                    if (node.node.left!=null){
                        SumNode tmp=new SumNode(node.node.left,node.sum+node.node.left.val);
                        queue.add(tmp);
                    }
                    if (node.node.right!=null){
                        SumNode tmp=new SumNode(node.node.right,node.sum+node.node.right.val);
                        queue.add(tmp);
                    }
                }
            }
        }
        return false;

    }
    static class SumNode{
        public TreeNode node;
        public  int sum;

        public SumNode(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }

    }
}
