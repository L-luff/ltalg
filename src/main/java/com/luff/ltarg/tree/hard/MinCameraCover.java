package com.luff.ltarg.tree.hard;

import com.luff.ltarg.common.TreeNode;

import java.util.Set;

/**
 * @author lsq
 * @date 2020/9/22
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * 示例 2：
 *
 *
 *
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 *
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-cameras
 */
public class MinCameraCover {

    public static void main(String[] args) {
        TreeNode root=new TreeNode(0);
        root.right=new TreeNode(1);
        root.right.right=new TreeNode(2);
        root.right.right.right=new TreeNode(3);
       // root.right.right.right.right=new TreeNode(3);
        int res = new MinCameraCover().minCameraCover(root);
        System.out.println(res);
    }

    public int minCameraCover(TreeNode root) {
        // root节点没有被监控的情况下
        if (recursive(root)==0) return res++;
        return res;
    }

    int res=0;
    //0 未被监控 1 被监控 2 摄像头
   public int recursive(TreeNode node){
        if (node==null){
            return 1;
        }
        int l=recursive(node.left);
        int r=recursive(node.right);
        if (l==1 && r==1){
            return 0;
        }else if (l+r>=3){
            return 1;
        }
        res++;
        return 2;
   }
}
