package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname GetAllElements
 * @Description
 * @Date 2020/5/6 19:13
 * @Created by li
 */

/**
 * 给你 root1 和 root2 这两棵二叉搜索树。
 *
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]

 */
public class GetAllElements {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        return iterator(root1,root2);
    }

    public List<Integer> iterator(TreeNode node1,TreeNode node2){
        List<Integer> res=new ArrayList<>();
        LinkedList<TreeNode> list1=new LinkedList<>();
        LinkedList<TreeNode> list2=new LinkedList<>();
        boolean c1=true,c2=true;
        TreeNode n1=node1,n2=node2;
        while (!list1.isEmpty()||!list2.isEmpty()||n1!=null||n2!=null){
            if(c1){
                while(n1!=null){
                    list1.push(n1);
                    n1=n1.left;
                }
            }
            if(c2){
                while(n2!=null){
                    list2.push(n2);
                    n2=n2.left;
                }
            }
            TreeNode tmp1 = null,tmp2=null;
            if(!list1.isEmpty()) tmp1= list1.pop();
            if(!list2.isEmpty()) tmp2=list2.pop();
            if(tmp1==null || tmp2==null){
                if(tmp1==null && tmp2==null) break;
                else if(tmp1==null && tmp2!=null){
                    res.add(tmp2.val);
                    n2=tmp2.right;
                    c1=false;c2=true;
                }else{
                    res.add(tmp1.val);
                    n1=tmp1.right;
                    c1=true;c2=false;
                }
            }else {
                if (tmp1.val > tmp2.val) {
                    res.add(tmp2.val);
                    n2 = tmp2.right;
                    list1.push(tmp1);
                    c1 = false;
                    c2 = true;
                } else {
                    res.add(tmp1.val);
                    n1 = tmp1.right;
                    list2.push(tmp2);
                    c1 = true;
                    c2 = false;
                }
            }

        }
        return res;
    }


}
