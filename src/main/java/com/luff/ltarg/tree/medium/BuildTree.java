package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;
import com.luff.ltarg.dp.RobI;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lsq
 * @date 2020/9/25
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] inorder=new int[]{3,2,1};
        int[] postorder=new int[]{3,2,1};
        TreeNode root = new BuildTree().buildTree(inorder, postorder);
        System.out.println(root);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> orderMap=new HashMap<>(inorder.length);
        for (int i=0;i<inorder.length;i++){
            orderMap.put(inorder[i],i);
        }
//        for (int i=0;i<postorder.length;i++){
//            int[] idxarr = orderMap.get(postorder[i]);
//            idxarr[1]=i;
//        }
        TreeNode res=recursive(orderMap,inorder,postorder,0,inorder.length-1,0,inorder.length-1);
        return res;
    }

    public TreeNode recursive(Map<Integer,Integer> orderMap,int[] inorder,int[] postorder,int ileft,int iright,int left,int right){
        if (left>right) return null;
        if (left==right) return new TreeNode(postorder[right]);
        int nodeVal=postorder[right];
        TreeNode node=new TreeNode(nodeVal);
        int mIdx = orderMap.get(nodeVal);
        int leftSize=mIdx-ileft;
        int rightSize=iright-mIdx;
        if (leftSize>0){
            node.left=recursive(orderMap,inorder,postorder,ileft,mIdx-1,left,left+leftSize-1);
        }
        if (rightSize>0){
            node.right=recursive(orderMap,inorder,postorder,mIdx+1,iright,right-rightSize,right-1);
        }
        return node;
    }
}
