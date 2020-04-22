package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @author lisiqun
 * @version V1.0
 * @Description:
 * @date 2020-04-22 9:58
 */
public class LongestUnivaluePath {


    /**
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     *
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        if(root==null) return 0;
        recursive(root);
        return maxPath-1;
    }

    private int maxPath=0;
    public NodeVal recursive(TreeNode node){
        if(node==null) return null;

        NodeVal l=recursive(node.left);
        NodeVal r=recursive(node.right);

        NodeVal res=new NodeVal(node,1);
        int lv=0,rv=0,maxVal=1;
        if(l!=null &&  node.val==l.node.val ){
            lv=l.val;
            maxVal+=l.val;
        }
        if(r!=null && node.val==r.node.val){
            rv=r.val;
            maxVal+=r.val;
        }
        //当前节点，左子结点，右子节点可以构成一条路径，进而与maxPath相比较
        //但是 向上返回的时候，必需找到一条路径的最大值，即：是与左子结点最大还是与右子节点最大。
        //这样才能保证：当前节点的父节点+当前节点+（左子结点或者右子节点）==>是一条路径
        maxPath=Math.max(maxPath,Math.max((Math.max(lv,rv)),maxVal));
        res.val+=Math.max(lv,rv);
        return res;
    }

    static class NodeVal{
        private TreeNode node;

        private int val;

        public NodeVal(){}
        public NodeVal(TreeNode node, int val) {
            this.node = node;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.right=new TreeNode(1);
        root.right.left=new TreeNode(1);
        root.right.right=new TreeNode(1);
        root.right.left.right=new TreeNode(1);
        root.right.left.left=new TreeNode(1);
        root.right.right.left=new TreeNode(1);
        System.out.println(new LongestUnivaluePath().longestUnivaluePath(root));
    }
}
