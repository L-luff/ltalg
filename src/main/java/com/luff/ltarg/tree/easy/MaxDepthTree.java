package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname MaxDepthTree
 * @Description
 * @Date 2020/4/21 20:07
 * @Created by li
 */
public class MaxDepthTree {


    /**
     * 给定一个二叉树，找出最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        findMaxDepthWithRecursive(root,0);
        return maxDepth;
    }


    public int maxDepth=Integer.MIN_VALUE;

    /**
     * 递归找出最大深度
     * @param node
     * @Param depth: 根节点到父节点的深度。因此如果当前节点是叶子节点，则depth=depth+1;
     */
    public void findMaxDepthWithRecursive(TreeNode node,int depth){
        if(node==null) return;
        if(node.left==null && node.right==null){
            depth+=1;
            maxDepth=Math.max(maxDepth,depth);
            return;
        }
        findMaxDepthWithRecursive(node.left,depth+1);
        findMaxDepthWithRecursive(node.right,depth+1);
    }


    /**
     * 使用迭代的方式  一层一层遍历即可
     * @param node
     */
    public int findMaxDepthWithIterator(TreeNode node){
       Queue<TreeNode> queue=new LinkedList<>();
       queue.offer(node);
       int level=0;
       while(!queue.isEmpty()){
           int csize=queue.size();
           for (int i=0;i<csize;i++) {
               TreeNode tn = queue.poll();
               if (tn.left != null) {
                   queue.offer(tn.left);
               }
               if (tn.right != null) {
                   queue.offer(tn.right);
               }
           }
           ++level;
       }
       return level;
    }
}
