package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @Classname IsSubtree
 * @Description
 * @Date 2020/5/7 20:51
 * @Created by li
 */

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。

 */

public class IsSubtree {


    public boolean isSubtree(TreeNode s, TreeNode t) {
       return recursive(s,t);
    }

    //############## recursive 1 ##########################
    public boolean recursive(TreeNode s,TreeNode t){
        if(s==null) return false;
        boolean res=false;
        if(s.val==t.val){
             res= compareTwoTrees(s,t);
        }
        return res || recursive(s.left,t) ||  recursive(s.right,t);
    }

    public boolean compareTwoTrees(TreeNode s,TreeNode t){
        if(s==null && t==null) return true;
        else if(s!=null && t!=null && s.val==t.val){
            return compareTwoTrees(s.left,t.left) && compareTwoTrees(s.right,t.right);
        }
        else return false;
    }

    //################ recursive 1 end ###########################

    int checkPrimeNumber=2000;
    boolean[] checkPrime=new boolean[checkPrimeNumber];
    int[] primes=new int[checkPrimeNumber];
    int mod=Integer.MAX_VALUE;

    public void getPrime(){
        for (int i=0;i<checkPrimeNumber;i++){
            checkPrime[i]=true;
        }
        int index=0;
        checkPrime[0]=checkPrime[1]=false;
        for (int i=2;i<checkPrimeNumber;i++){
            if(checkPrime[i]){
                primes[++index]=i; // 第一位素数 index=0 去除
                //将不是素数的设置为false
                for (int j=i*2;j<checkPrimeNumber;j+=i){
                    checkPrime[j]=false;
                }
            }
        }
    }


    static class NodeHash{
        private int hash;
        private int nodeCounts; //

        public NodeHash(int hash, int nodeCounts) {
            this.hash = hash;
            this.nodeCounts = nodeCounts;
        }
    }
    public boolean checkIsSubTree(TreeNode s,TreeNode t){
        getPrime();
        Map<TreeNode,NodeHash> maps=new HashMap<>(checkPrimeNumber);
        Map<TreeNode,NodeHash> mapt=new HashMap<>(checkPrimeNumber);
        nodeHashRecursive(s,maps);
        nodeHashRecursive(t,mapt);
        int tHash=mapt.get(t).hash;
        for (Map.Entry<TreeNode, NodeHash> entry : maps.entrySet()) {
            if(entry.getValue().hash==tHash)
                return true;
        }
        return false;
    }


    public void nodeHashRecursive(TreeNode node, Map<TreeNode,NodeHash> map){
        map.put(node,new NodeHash(node.val,1));
        if(node.left==null && node.right==null) return;
        NodeHash curNodeHash = map.get(node);
        if(node.left!=null){
            nodeHashRecursive(node.left,map);
            NodeHash leftNodeHash=map.get(node.left);
            curNodeHash.nodeCounts+=leftNodeHash.nodeCounts;
            curNodeHash.hash= (curNodeHash.hash+( (31 * leftNodeHash.hash * primes[leftNodeHash.nodeCounts]) % mod)) % mod;
        }
        if(node.right!=null){
            nodeHashRecursive(node.right,map);
            NodeHash rightNodeHash=map.get(node.right);
            curNodeHash.nodeCounts+=rightNodeHash.nodeCounts;
            curNodeHash.hash=(curNodeHash.hash + (179 * rightNodeHash.hash * primes[rightNodeHash.nodeCounts]) % mod) % mod;
        }
    }





}
