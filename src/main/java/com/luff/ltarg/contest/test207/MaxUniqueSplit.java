package com.luff.ltarg.contest.test207;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lsq
 * @date 2020/9/20
 * 给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
 *
 * 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
 *
 * 注意：子字符串 是字符串中的一个连续字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ababccc"
 * 输出：5
 * 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。像 ['a', 'b', 'a', 'b', 'c', 'cc'] 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。
 * 示例 2：
 *
 * 输入：s = "aba"
 * 输出：2
 * 解释：一种最大拆分方法为 ['a', 'ba'] 。
 * 示例 3：
 *
 * 输入：s = "aa"
 * 输出：1
 * 解释：无法进一步拆分字符串。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 *
 * s 仅包含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings
 */
public class MaxUniqueSplit {

    public static void main(String[] args) {
        String s="aa";
        System.out.println(new MaxUniqueSplit().maxUniqueSplit(s));
    }
    public int maxUniqueSplit(String s) {
        recursive(s,0,new HashSet<>(s.length()),0);
        return res;
    }

    int res=0;
    public void recursive(String s, int idx, Set<String> contains,int m){
        if (idx==s.length()){
            if (m>res) res=m;
            return ;
        }
        StringBuilder sb=new StringBuilder();
        for (int i=idx;i<s.length();i++){
            sb.append(s.charAt(i));
            String tmp=sb.toString();
            if (contains.add(tmp)){
                recursive(s,i+1,contains,m+1);
                contains.remove(tmp);
            }
        }
    }
}
