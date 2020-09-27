package com.luff.ltarg.dp;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/9
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 *
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。

 */
public class Respace {

    public static void main(String[] args) {
        String[] test=new String[]{"looked","just","like","her","brother"};
        String sen="jesslookedjustliketimherbrother";
        System.out.println(respace(test,sen));
    }

    public static int respace(String[] dictionary, String sentence) {
        return solution(dictionary,sentence);
    }


    /**
     * 使用trip和dp解决
     * 令dp[i]和前i个字符中最少的未识别的字符个数
     *  考虑转移方程式， 第j个字符到第i个字符【j-i] 组成的字符串能否在字典中找到，并且比较最小的未识别字符
     *  dp[i]=min(dp[i],dp[j-1])
     * @param dictionary
     * @param sentence
     * @return
     */
    public static int solution(String[] dictionary,String sentence){
        Trie root=new Trie();
        for (int i=0;i<dictionary.length;i++){
            root.build(dictionary[i]);
        }

        int len=sentence.length();
        int[] dp=new int[len+1];  // 前i个字符未识别的最少字符数
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for (int i=1;i<dp.length;i++){
            dp[i]=dp[i-1]+1; // 先假设字符sentence[i] 无法识别
            Trie cur=root;
            for (int j=i;j>=1;j--){
                int index=sentence.charAt(j-1)-'a';
                if (cur.next[index]==null){
                    // 未识别
                    break;
                }
                if (cur.next[index].isEnd){ // j-1~i-1的字符可以匹配。则以j-1为分割符的dp[i]就是等于dp[j]
                    dp[i]=Math.min(dp[i],dp[j-1]);
                }
                cur=cur.next[index];
            }
        }
        return dp[len];
    }

    static class  Trie{
        Trie[] next;
        boolean isEnd;

        public Trie() {
            this.next = new Trie[26];
            this.isEnd = false;
        }


        public void build(String s){
            if (s==null || s.length()==0) return ;
            Trie cur=this;
            int len=s.length();
            for (int i=len-1;i>=0;i--){
                int index=s.charAt(i)-'a';
                if (cur.next[index]==null){
                    cur.next[index]=new Trie();
                }
                cur=cur.next[index];
            }
            cur.isEnd=true;
        }
    }
}
