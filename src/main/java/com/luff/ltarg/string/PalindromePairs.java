package com.luff.ltarg.string;


import java.util.*;

/**
 * @author lsq
 * @date 2020/8/6
 * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2：
 *
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 */
public class PalindromePairs {


    public static void main(String[] args) {
        String[] words=new String[]{"a",""};
        List<List<Integer>> res = new PalindromePairs().palindromePairs(words);
        System.out.println(res);
    }


    public  List<List<Integer>> palindromePairs(String[] words) {

        return solution2(words);
    }

    /**
     * 暴力解决
     * @param words
     * @return
     */
    public static List<List<Integer>> solution1(String[] words){
        int len=words.length;
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<len;i++){
            for (int j=i+1;j<len;j++){
                String s1=words[i],s2=words[j];
                if (isPalindrome(s1,s2)){
                    res.add(Arrays.asList(i,j));
                }
                if (isPalindrome(s2,s1)){
                    res.add(Arrays.asList(j,i));
                }
            }
        }
        return res;
    }

    public  List<List<Integer>> solution2(String[] words){
        Map<String,Integer> reverse=new HashMap<>(words.length);
        for (int i=0;i<words.length;i++){
            reverse.put(new StringBuilder(words[i]).reverse().toString(),i);
        }
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<words.length;i++){
            String word=words[i];
            if (word.equals("")) continue;
            int midx=word.length()-1;

            // 1:全身都是回文串 2：word单词不是回文串，但是可以找到他的反串3:有空串
            // word作为左边拼接的长单词 j~midx 回文串
            for (int j=0;j<=midx+1;j++){
                if (isPalindrome(word,j,midx)){
                    int eidx=reverse.getOrDefault(word.substring(0,j),-1);
                    if (eidx!=-1 && eidx!=i){
                        res.add(Arrays.asList(i,eidx));
                        if (words[eidx].equals("")){
                            res.add(Arrays.asList(eidx,i));
                        }
                    }
                }
            }

            // word作为右边拼接的长单词 0~j-1回文串
            for (int j=1;j<=midx;j++){
                if (isPalindrome(word,0,j-1)){
                    int eidx=reverse.getOrDefault(word.substring(j,midx+1),-1);
                    if (eidx != -1 && eidx!=i) {
                        res.add(Arrays.asList(eidx,i));
                    }
                }
            }

        }
        return res;
    }

    private boolean isPalindrome(String s,int left,int right){
        if (left>=right) return true;
        while (left<right){
            if (s.charAt(left++)!=s.charAt(right--)) return false;
        }
        return true;
    }
    private static boolean isPalindrome(String s1,String s2){
        String s=s1+s2;
        int len=s.length();
        int left=0,right=len-1;
        while (left<right){
            if (s.charAt(left++)!=s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}
