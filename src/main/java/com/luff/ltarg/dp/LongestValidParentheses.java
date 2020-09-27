package com.luff.ltarg.dp;

import java.util.*;

/**
 * @author lsq
 * @date 2020/7/4
 *
 * ###hard###
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */
public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        return solution1(s);
    }

    /**
     * dp
     * @param s
     * @return
     */
    public static int solution1(String s){
        int len=s.length();
        int[] dp=new int[len];
        int max=0;
        for (int i=1;i<len;i++){
            if (s.charAt(i)==')'){
                if (s.charAt(i-1)=='('){
                    dp[i]=i<2?2:dp[i-2]+2;
                }else if (i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='('){
                    dp[i]=dp[i-1]+((i-dp[i-1]>=2) ? dp[i-dp[i-1]-2] : 0) + 2;
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 使用栈解决。 方法在于：保持栈底始终是最后一个【没有被匹配到的右括号的下标】。
     * @param s
     * @return
     */
    public static int solution2(String s){

        Stack<Integer> stack=new Stack<>();
        stack.push(-1); // 为了计算使用，保证栈底始终是最后一个没有被匹配的右括号下标
        int max=0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.empty()){
                    stack.push(i);  //相当于分割线，此分割线之上的都是正常匹配的
                }else{
                    max=Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
