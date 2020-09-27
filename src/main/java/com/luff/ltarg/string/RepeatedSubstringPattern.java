package com.luff.ltarg.string;

/**
 * @author lsq
 * @date 2020/8/24
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: "aba"
 *
 * 输出: False
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abcaaabcaaabcaaabcaaabcaa"));
    }
    public boolean repeatedSubstringPattern(String s) {
        return solution1(s);
    }

    // 比较暴力的解法
    public boolean solution1(String s){
        int len=s.length();
        int mid=len>>1;
        if (len<=1) return false;
        int left=0,right=1;
        while (right<=mid){
            if (s.charAt(right)==s.charAt(left)){
                int w=right-left;
                if (len % w == 0 && isMatch(s,left,right)){
                    return true;
                }
            }
            right++;
        }
        return false;
    }

    public boolean solution2(String s){
        return (s+s).indexOf(s,1)<s.length();
    }

    private boolean isMatch(String s,int left,int right){
        int tmpRight=right,tmpLeft=left;
        while(right<s.length()) {
            while (left < tmpRight && s.charAt(left) == s.charAt(right)) {
                left++;
                right++;
            }
            if (left!=tmpRight) return false;
            left=tmpLeft;
        }
        return true;
    }
}
