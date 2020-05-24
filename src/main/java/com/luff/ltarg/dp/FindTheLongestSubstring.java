package com.luff.ltarg.dp;

/**
 * @Classname FindTheLongestSubstring
 * @Description
 * @Date 2020/5/20 21:28
 * @Created by li
 */

import java.util.Arrays;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 *
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 *
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。

 */
public class FindTheLongestSubstring {

    public int findTheLongestSubstring(String s) {
        int[] cache=new int[32];
        Arrays.fill(cache,-1);
        int res=0,flag=0;
        cache[0]=0; //需要初始化一个为0
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='a'){
                flag^=1<<0;
            }else if(c=='e'){
                flag^=1<<1;
            }else if(c=='i'){
                flag^=1<<2;
            }else if(c=='o'){
                flag^=1<<3;
            }else if(c=='u'){
                flag^=1<<4;
            }
            if(cache[flag]!=-1){
                res=Math.max(res,i+1-cache[flag]);
            }else{
                cache[flag]=i+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int res = new FindTheLongestSubstring().findTheLongestSubstring("leetcodeisgreat");
        System.out.println(res);
    }

}
