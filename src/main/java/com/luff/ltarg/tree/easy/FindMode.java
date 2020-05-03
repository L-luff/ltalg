package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname FindMode
 * @Description
 * @Date 2020/4/26 20:21
 * @Created by li
 */

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindMode {


    /**
     * 找到二叉树的众数
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if(root==null) return new int[]{};
        recursive(root);
        if(curCount>maxCount){
            return new int[]{prev.val};
        }
        if(curCount==maxCount){
            res.add(prev.val);
        }
        int[] ares=new int[res.size()];
        for(int i=0;i<res.size();i++){
            ares[i]=res.get(i);
        }
        return ares;
    }

    private List<Integer> res=new LinkedList<>();

    private TreeNode prev;

    private int curCount=1;

    private int maxCount=1;

    /**
     * 利用平衡二叉树的性质。通过使用两个指针，使得在遍历平衡二叉树时，以一种类似于迭代一个有序的数组一般。
     * @param node
     * @return
     */
    public void recursive(TreeNode node){
        if(node==null) return;
        recursive(node.left);
        if(prev!=null){
            if(node.val==prev.val){
                curCount++;
            }else{
                if(curCount>maxCount){
                    res.clear();
                    res.add(prev.val);
                    maxCount=curCount;
                }else if(curCount==maxCount){
                    res.add(prev.val);
                }
                curCount=1;
            }

        }
        prev=node;
        recursive(node.right);
    }
}
