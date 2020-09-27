package com.luff.ltarg.string;

/**
 * @author lsq
 * @date 2020/8/3
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *415
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 */
public class AddStrings {


    public static void main(String[] args) {
        System.out.println(9999999+99);
        System.out.println(addStrings("9999999","99"));
    }
    public static String addStrings(String num1, String num2) {
        int add=0;
        int n1=num1.length()-1,n2=num2.length()-1;
        StringBuilder res=new StringBuilder();
        while (n1>=0 || n2>=0){
            int a1=0,a2=0;
            if (n1>=0){
                a1=num1.charAt(n1)-'0';
                n1--;
            }
            if (n2>=0){
                a2=num2.charAt(n2)-'0';
                n2--;
            }
            int tmpSum=a1+a2+add;
            int q=tmpSum % 10;
            int remain=tmpSum / 10;
            res.append(q);
            add=remain;
        }
        if (add!=0){
            res.append(add);
        }
        return res.reverse().toString();
    }
}
