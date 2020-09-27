package com.luff.ltarg.string;

/**
 * @author lsq
 * @date 2020/8/19
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *  
 *
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSubstrings {

    public static void main(String[] args) {
        System.out.println(new CountSubstrings().countSubstrings("aaa"));
    }

    public int countSubstrings(String s) {
        return solution2(s);
    }

    /**
     * 暴力算法  660ms
     * @param s
     * @return
     */
    public int solution1(String s){
        int len=s.length();
        int res=0;
        for (int i=0;i<len;i++){
            int count=1;
            for (int j=i+1;j<len;j++){
                if (isPalindrome(s,i,j)){
                    count++;
                }
            }
            res+=count;
        }
        return res;
    }
    int[][] f=null;
    //dp
    public int solution2(String s){
        int len=s.length();
        int res=0;
        f=new int[len][len]; // 所以i-j是否是回文字符串
        for (int k=0;k<len;k++){
            f[k][k]=2;
        }
        for (int i=2;i<=len;i++){
            int count=0;
            for (int j=0;j<len;j++){
                int endIndex=i+j-1;
                if (endIndex>=len) break;
                if (isPalindrome2(s,j,endIndex)){
                    count++;
                }
            }
            res+=count;
        }
        return res+len;

    }

    // 3
    public int solution3(String s){
        return 0;
    }

    /**
     * Manacher 算法
     * @param s
     * @return
     */
    public int solution4(String s){
        return 0;
    }

    private boolean isPalindrome2(String s,int start,int end){
        if (start>end) return false;
        if (f[start][end]==2) return true;
        if (f[start+1][end-1]==1) return false;
        if (f[start+1][end-1]==2 ) {
            return s.charAt(start)==s.charAt(end);
        }
        boolean pr=true;
        int tstart=start,tend=end;
        while(tend>tstart){
            if (s.charAt(tstart++)!=s.charAt(tend--)){
                pr=false;
            }
        }
        f[start][end]=pr ? 2 : 1;
        return pr;
    }

    private boolean isPalindrome(String s,int start,int end){
        if (start>end) return false;
        while (end>start){
            if (s.charAt(start++)!=s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
}
