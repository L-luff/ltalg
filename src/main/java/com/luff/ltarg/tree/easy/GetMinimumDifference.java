package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname GetMinimumDifference
 * @Description
 * @Date 2020/5/3 20:44
 * @Created by li
 */


/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

 */
public class GetMinimumDifference {

    /**
     * 任意两节点的绝对值最小差，这是一颗二叉搜索树，因此可以将他当作一个有序数组
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        recursive(root);
        return minVal;
    }


    int minVal=Integer.MAX_VALUE;
    TreeNode prefix=null;
    public void recursive(TreeNode node){
        if(node==null) return ;
        if(node.left==null && node.right==null){
            if(prefix!=null){
                minVal=Math.min(Math.abs(node.val-prefix.val),minVal);
            }
            prefix=node;
            return;
        }
        recursive(node.left);
        if(prefix!=null){
            minVal=Math.min(Math.abs(node.val-prefix.val),minVal);
        }
        prefix=node;
        recursive(node.right);

    }
}
