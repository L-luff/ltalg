package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname AddOneRow
 * @Description
 * @Date 2020/5/5 23:51
 * @Created by li
 */
public class AddOneRow {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
       return recursive(root,v,d,0,true);
    }


    public TreeNode recursive(TreeNode node,int v,int d,int curDepth,boolean left){
        if (node==null){
            if(curDepth+1!=d){
                return null;
            }
        }

        if(curDepth+1==d){
            TreeNode tmp=new TreeNode(v);
            if(left){
                tmp.left=node;
            }else{
                tmp.right=node;
            }
            return tmp;
        }

        node.left=recursive(node.left,v,d,curDepth+1,true);
        node.right=recursive(node.right,v,d,curDepth+1,false);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        TreeNode treeNode = new AddOneRow().addOneRow(root, 5, 4);
        System.out.println(treeNode);
    }
}
