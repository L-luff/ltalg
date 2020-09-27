package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname PreorderTraversal
 * @Description
 * @Date 2020/4/25 16:29
 * @Created by li
 *
 * @see InorderTraversal
 */
public class PreorderTraversal {


    /**
     * 二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        return iterator(root);
    }

    public void recursive(List<Integer> res,TreeNode node){
        if(node==null) return ;
        res.add(node.val);
        recursive(res,node.left);
        recursive(res,node.right);
    }


    /**
     * 迭代的方式 前序遍历
     * @param node
     * @return
     */
    public List<Integer> iterator(TreeNode node){
        List<Integer> res=new LinkedList<>();
        if(node==null) return res;
        LinkedList<TreeNode> nodes=new LinkedList<>();
        nodes.push(node);
        while(!nodes.isEmpty()){
            TreeNode tmp = nodes.poll();
            res.add(tmp.val);
            if(tmp.right!=null){
                nodes.push(tmp.right);
            }
            if(tmp.left!=null){
                nodes.push(tmp.left);
            }
        }
        return res;
    }


}
