package com.luff.ltarg.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lsq
 * @date 2020/9/5
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 */
public class GetPermutation {

    public static void main(String[] args) {
        int n=9;int k=1;
        GetPermutation permutation = new GetPermutation();
        permutation.getPermutation(n,k);
        GetPermutation permutation1 = new GetPermutation();
        permutation1.solution2(n,k);
        System.out.println(permutation.res);
        System.out.println(permutation1.res);
    }
    public String getPermutation(int n, int k) {
        return solution1(n,k);
    }

    // 超时
    public String solution1(int n,int k){
        this.k=k;
        recursive(n,n,new HashSet<>(),new StringBuilder());
        return res;
    }
    boolean end=false;
    String res;
    int k;
    public void recursive(int n, int remain, Set<Integer> marked, StringBuilder pre){
        if (remain==0){
            k--;
            if (k==0){
                end=true;
                res=pre.toString();
            }
            return;
        }
        for (int i=1;i<=n;i++){
            boolean addRes = marked.add(i);
            if (addRes){
                recursive(n,remain-1,marked,pre.append(i));
                if (end) return ;
                pre.deleteCharAt(pre.length()-1);
                marked.remove(i);
            }
        }
    }

    // 可以继续延伸，这里只是剪枝的第一位，其实后面的所有位数都可以继续剪枝
    public String solution2(int n,int k){
        int count=1;
        for (int i=2;i<=n;i++){
            count=count * i;
        }
        int avg=count / n;
        int pos=k / avg;
        pos=k % avg ==0 ? pos : pos+1;
        this.k=k- (pos-1) * avg;
        r2(n,pos,n,new HashSet<>(),new StringBuilder());
        return res;
    }
    public void r2(int n,int start,int remain,Set<Integer> marked,StringBuilder pre){
        if (remain==0){
            k--;
            if (k==0){
                end=true;
                res=pre.toString();
            }
            return;
        }
        for (int i=start;i<=n;i++){
            boolean addRes = marked.add(i);
            if (addRes){
                r2(n,1,remain-1,marked,pre.append(i));
                if (end) return ;
                pre.deleteCharAt(pre.length()-1);
                marked.remove(i);
            }
        }
    }
}
