package com.luff.ltarg.dp;

/**
 * @author lsq
 * @date 2020/10/16
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *
 * 输入: strs = ["10", "0", "1"], m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 */
public class FindMaxForm {

    public static void main(String[] args) {
        String[] strs=new String[]{"10", "0", "1"};
        int m=1,n=1;
        int res = new FindMaxForm().findMaxForm(strs, m, n);
        System.out.println(res);
    }
    public int findMaxForm(String[] strs, int m, int n) {
        int len=strs.length;
        int[][][] f=new int[len+1][m+1][n+1];

        for(int i=1;i<=len;i++){
            int[] strlens=strlen(strs[i-1]);
            for(int k=0;k<=m;k++){
                for(int j=0;j<=n;j++){
                    if(k==0 && j==0){
                        f[i][0][0]=0;
                        continue;
                    }
                    f[i][k][j]=f[i-1][k][j];
                    if(strlens[0]<=k && strlens[1]<=j){
                        f[i][k][j]=Math.max(f[i][k][j],f[i-1][k-strlens[0]][j-strlens[1]]+1);
                    }
                }
            }
        }
        return f[len][m][n];
    }
    public int[] strlen(String s){
        int[] res=new int[]{0,0};
        for(char c:s.toCharArray()){
            if(c=='0'){
                res[0]++;
            }else{
                res[1]++;
            }
        }
        return res;
    }
}
