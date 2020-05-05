package com.luff.ltarg.tree.easy;

/**
 * @Classname PathSum
 * @Description
 * @Date 2020/5/3 19:05
 * @Created by li
 */

import com.luff.ltarg.common.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11

 */
public class PathSum {
    /**
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        recursive(root,sum);
        return res;
    }

    int res=0;

    public List<Integer> recursive(TreeNode node,int sum){
        if(node==null) return new ArrayList<>();
        if(node.left==null && node.right==null){
            if(sum==node.val){
                res++;
            }
            List<Integer> tmpRes=new ArrayList<>();
            tmpRes.add(node.val);
            return tmpRes;
        }
        List<Integer> leftSum = recursive(node.left, sum);
        List<Integer> rightSum = recursive(node.right, sum);
        if(node.val==sum){ // 自身节点判断
            res++;
        }
        int maxSize=Math.max(leftSum.size(),rightSum.size());
        for (int i=0;i<maxSize;i++){
            int val;
            if(leftSum.size()>i){
                val=leftSum.get(i);
                if(sum==(val+node.val)){
                    res++;
                }

                leftSum.set(i,leftSum.get(i)+node.val);
            }
            if(rightSum.size()>i){
                val=rightSum.get(i);
                if(sum==(val+node.val)){
                    res++;
                }
                rightSum.set(i,rightSum.get(i)+node.val);
            }
        }
        leftSum.addAll(rightSum);
        leftSum.add(node.val);
        return leftSum;
    }

    //前缀和

    /**
     * 对于每个当前节点： 每次计算当前到root节点个值。例如有节点a,b,c,d,root,则计算节点a到root节点值的和。每个节点都是如果
     * 根据以上，可以得知。如果节点a到root节点的路径是 a->b->c->root 则可以清除的计算出节点a->b a->c a->root的值。根据这些值
     * 和target判断，就可以得知节点a到上面的节点是否符合target. 然后递归。求出所有的可能性
     *
     *
     * @param node
     * @param prefixSum
     * @param target
     * @param curSum
     * @return
     */
    public int recursive_prefix(TreeNode node, Map<Integer,Integer> prefixSum, int target,int curSum){
        if(node==null) return 0;

        curSum+=node.val;
        int res=0;
        res+=prefixSum.getOrDefault(curSum-target,0);
        prefixSum.put(curSum,prefixSum.getOrDefault(curSum,0)+1);
        res+=recursive_prefix(node.left,prefixSum,target,curSum);
        res+=recursive_prefix(node.right,prefixSum,target,curSum);
        prefixSum.put(curSum,prefixSum.get(curSum)-1);
        return res;
    }

}
