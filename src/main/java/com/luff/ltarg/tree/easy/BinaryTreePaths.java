package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Classname BinaryTreePaths
 * @Description
 * @Date 2020/4/25 13:54
 * @Created by li
 */
public class BinaryTreePaths {


    /**给定一个二叉树，返回所有从根节点到叶子节点的路径。

     说明: 叶子节点是指没有子节点的节点。

     示例:

     输入:

     1
     /   \
     2     3
     \
     5

     输出: ["1->2->5", "1->3"]
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList<>();
        recursive(res,root,"");
        return res;
    }

    public void recursive(List<String> res,TreeNode node,String prefix){
        if(node==null) return;
        if(node.left==null && node.right==null){
            res.add(prefix+node.val);
            return;
        }
        String next=new StringBuilder(prefix).append(node.val).append("->").toString();
        recursive(res,node.left,next);
        recursive(res,node.right,next);
    }

    public void iterator(List<String> res,TreeNode node){

    }
}
