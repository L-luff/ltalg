package com.luff.ltarg.string;

/**
 * @author lsq
 * @date 2020/7/18
 */
public class IsInterleave {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc","dbbca","aadbbbaccc"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        return solution2(s1,s2,s3);
    }

    /**
     * 递归解决
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean solution1(String s1,String s2,String s3){
        if (s1.length()+s2.length()!=s3.length()) return false;
        return recursive(s1,s2,s3,0,0,0);
    }

    /**
     * 使用dp解决
     * dp[i][j] 代表 s1的前i个字符和s2的前j个字符能否交错组成s3的i+j个元素
    * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean solution2(String s1,String s2,String s3){
        int len1=s1.length(),len2=s2.length(),len3=s3.length();
        if (len1+len2!=len3) return  false;

        //可以使用滚动数组优化
        boolean[][] f=new boolean[len1+1][len2+1];
        f[0][0]=true;
        for (int i=0;i<=len1;i++){
            for (int j=0;j<=len2;j++){
                if (i>0){
                    f[i][j]=f[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                }
                if (j>0){
                    f[i][j]=f[i][j] ||f[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
                }
            }
        }
        return f[len1][len2];
    }


    private static boolean recursive(String s1,String s2,String s3,int i1,int i2,int i3){
        boolean res=false;
        if (i1>=s1.length() && i2>=s2.length()){
            if (i3>=s3.length()) return true;
            return false;
        }
        if (i1>=s1.length()){ // 可以采用循环，减少递归次数
            if (s2.charAt(i2)==s3.charAt(i3)){
                return recursive(s1,s2,s3,i1,i2+1,i3+1);
            }else{
                return false;
            }
        }

        if (i2>=s2.length()){  // 同上
            if (s1.charAt(i1)==s3.charAt(i3)){
                return recursive(s1,s2,s3,i1+1,i2,i3+1);
            }
            return false;
        }

        if (s1.charAt(i1)==s3.charAt(i3)){
            res=recursive(s1,s2,s3,i1+1,i2,i3+1);
        }
        if (!res && s2.charAt(i2)==s3.charAt(i3)){
            res=recursive(s1,s2,s3,i1,i2+1,i3+1);
        }
        return res;
    }
}

