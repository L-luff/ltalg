package com.luff.ltarg.tree.easy;

/**
 * @Classname FindSecondMinimumValue
 * @Description
 * @Date 2020/5/5 17:20
 * @Created by li
 */

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 */
public class FindSecondMinimumValue {

    /**
     * 找到第二小的节点值
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        return iterator(root);
    }


    /**
     * 迭代算法 依据提议得知：
     *                  1：最小值为root节点值
     *                  2：要得到第二小的节点，该节点必须是和root节点值相等的节点的左右子节点。
     *          因此只需要对那些和root节点相等的节点的左右子节点进行迭代即可。
     * @param node
     * @return
     */
    public int iterator(TreeNode node){
        int min=node.val,res=Integer.MAX_VALUE;
        boolean find=false;
        LinkedList<TreeNode> nodes=new LinkedList<>();
        nodes.add(node);
        while(!nodes.isEmpty()){
            TreeNode tmp = nodes.pop();
            if(tmp==null) continue;
            if(tmp.left!=null){
                if(tmp.left.val!=min){
                    find=true;
                    res=Math.min(res,tmp.left.val);
                }else{
                    nodes.addLast(tmp.left);
                }
            }
            if(tmp.right!=null){
                if(tmp.right.val!=min){
                    find=true;
                    res=Math.min(res,tmp.right.val);
                }else{
                    nodes.addLast(tmp.right);
                }
            }
        }
        return find?res:-1;
    }

}
