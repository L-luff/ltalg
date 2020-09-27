package com.luff.ltarg.dp;

import com.luff.ltarg.common.TreeNode;

import java.util.*;

/**
 * @author lsq
 * @date 2020/8/5
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 */
public class RobIII {

    public int rob(TreeNode root) {
        return solution(root);
    }

    public  int solution(TreeNode root){
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }

    // 节点i被选中时所能获得的最大金额, 子节点l,r将无法被选中，因此 f(i)=node.val+g(l)+g(r)
    Map<TreeNode,Integer> f=new HashMap<>();

    // 节点i不被选中时获得的最大金额 那么节点l,r可以被选中也可以不被选中
    Map<TreeNode,Integer> g=new HashMap<>();
    public  void dfs(TreeNode node){
        if (node==null) return ;

        dfs(node.left);
        dfs(node.right);
        f.put(node,node.val+g.getOrDefault(node.left,0)+g.getOrDefault(node.right,0));
        g.put(node,Math.max(f.getOrDefault(node.left,0),g.getOrDefault(node.left,0))+
                    Math.max(f.getOrDefault(node.right,0),g.getOrDefault(node.right,0)));
    }
}
