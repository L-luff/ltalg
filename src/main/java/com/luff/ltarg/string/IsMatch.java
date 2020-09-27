package com.luff.ltarg.string;

/**
 * @author lsq
 * @date 2020/7/5
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 */
public class IsMatch {

    public static boolean isMatch(String s, String p) {
        return solution1(s,p);
    }


    /**
     * ‘*’：可以匹配任意字符串，注意： 是字符串而不是字符
     * 回溯
     * @param s
     * @param p
     * @return
     */
    public static boolean solution1(String s,String p){
        int si=0,pi=0;
        while (pi<p.length()){
            char pc=p.charAt(pi);
            if (pc=='*'){
                return doSolutionMatch(s,p,si,pi);
            }else{
                if (si<s.length()){
                    char sc=s.charAt(si);
                    if (sc==pc || pc=='?'){
                        si++;
                        pi++;
                    }else
                        return false;
                }else return false;
            }
        }
        if (si==s.length() && pi==p.length()){
            return true;
        }
        return false;
    }

    /**
     * dp
     *
     *
     *  设置dp[i][j] 代表s的前i个字符和p的前j个字符是否匹配
     * @param s
     * @param p
     * @return
     */
    public static boolean dpSolution(String s,String p){
        int slen=s.length(),plen=p.length();
        int[][] dp=new int[plen][slen];

        return false;
    }

    /**
     *  '*' 数据匹配
     * @param s
     * @param p
     * @param si
     * @param pi
     * @return
     */
    public static boolean doSolutionMatch(String s,String p,int si,int pi){

        if (pi==p.length()-1){ // * 是正则的最后一个字符。直接返回true
            return true;
        }
        do {
            int fpi=pi+1;
            int fsi=si;  // * 可以匹配空字符串
            while(fpi<p.length()){
                if (fsi<s.length()){        // 字符匹配时，s字符串还未查找完
                    char sc=s.charAt(fsi);
                    char pc=p.charAt(fpi);
                    if (sc==pc || pc=='?'){
                        fsi++;
                        fpi++;
                    }else if (pc=='*'){
                        return doSolutionMatch(s,p,fsi,fpi);
                    }else{
                        break;
                    }
                }else{                     // 字符串s已经查找完，判断pattern后面的字符串是否是都是*
                    if (p.charAt(fpi)=='*'){
                        fpi++;
                        continue;
                    }else{
                        break;
                    }
                }
            }
            if (fpi==p.length() && fsi>=s.length()){
                return true;
            }
        }while (pi<p.length()&& (++si)<s.length());

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("acdcb","a*b**"));
    }

}
