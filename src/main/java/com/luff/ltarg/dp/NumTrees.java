package com.luff.ltarg.dp;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/7/15
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
= */
public class NumTrees {


    public static void main(String[] args) {
        int n=3;
        int res = numTrees(n);
        System.out.println("res====> "+res);
        List<TreeNode> treeNodes = generateTrees(n);
        System.out.println(treeNodes);
    }
    public static int numTrees(int n) {

        return solution1(n);
    }




    public static int solution1(int n){
        if(n<=2) return n;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        return recursive(n,dp);
    }
    private static int recursive(int count,int[] dp){
        if (count==0) return 0;
        if (dp[count]!=0) return dp[count];
         for (int i=1;i<=count;i++){
             int left=recursive(i-1,dp);
             int right=recursive(count-i,dp);
             dp[count]+=(left==0 ? 1 : left) * (right==0?1:right);
         }
         return dp[count];
    }


    // 题目变种，95题：输出所有的二叉搜索树
    public static List<TreeNode> generateTrees(int n) {
        return generateRecursive(1,n);
    }

    public static List<TreeNode> generateRecursive(int start,int end){
//        if (end<start) return Collections.emptyList();
        if (end<start){
            List<TreeNode> res=new LinkedList<>();
            res.add(null);// 保证list有数据，即便是null,这样可以保证一定能循环成功
            return res;
        }
        if (start==end){
            List<TreeNode> res=new LinkedList<>();
            res.add(new TreeNode(start));
            return res;
        }
        List<TreeNode> nodesRes=new LinkedList<>();
        for (int i=start;i<=end;i++){
            List<TreeNode> left=generateRecursive(start,i-1);
            List<TreeNode> right=generateRecursive(i+1,end);
            for (TreeNode ln:left){
                for (TreeNode rn:right){
                    TreeNode node=new TreeNode(i);
                    node.left=ln;
                    node.right=rn;
                    nodesRes.add(node);
                }
            }
//            int ls=left.size(),rs=right.size();
//            if (ls==0 && rs==0){
//                TreeNode tmp=new TreeNode(i);
//                nodesRes.add(tmp);
//            }else{
//                List<TreeNode> outSide,innerSide;
//                boolean lsb=true;
//                if (ls>rs){
//                    outSide=left;
//                    innerSide=right;
//                }else{
//                    outSide=right;
//                    innerSide=left;
//                    int tmpVal=rs;
//                    rs=ls;
//                    ls=tmpVal;
//                    lsb=false;
//                }
//                for (int j=0;j<ls;j++){
//                    TreeNode outNode=outSide.get(j);
//                    for (int k=0;k<rs;k++){
//                        TreeNode node=new TreeNode(i);
//                        TreeNode innerNode=innerSide.get(k);
//                        if (lsb){
//                            node.left=outNode;
//                            node.right=innerNode;
//                        }else{
//                            node.left=innerNode;
//                            node.right=outNode;
//                        }
//                        nodesRes.add(node);
//                    }
//                    if (rs==0){
//                        TreeNode node=new TreeNode(i);
//                        if (lsb){
//                            node.left=outNode;
//                        }else{
//                            node.right=outNode;
//                        }
//                        nodesRes.add(node);
//                    }
//                }
//
//            }
        }

        return nodesRes;
    }


}
