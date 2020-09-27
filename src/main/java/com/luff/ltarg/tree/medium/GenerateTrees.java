package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/7/21
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *  
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *  
 *
 * 提示：
 *
 * 0 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
public class GenerateTrees {

    public List<TreeNode> generateTrees(int n) {
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
