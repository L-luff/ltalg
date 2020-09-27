package com.luff.ltarg.bitManipulation;

/**
 * @author lsq
 * @date 2020/8/23
 */
public class RangeBitwiseAnd {

    public static void main(String[] args) {
        RangeBitwiseAnd rangeBitwiseAnd=new RangeBitwiseAnd();
        for (int i=0;i<1000;i++){
            for (int j=10;j<=Integer.MAX_VALUE;j++){
                int i1 = rangeBitwiseAnd.solution1(i, j);
                int i2 = rangeBitwiseAnd.solution2(i, j);
                if (i1!=i2){
                    System.out.println("i="+i+" and j="+j);
                    return ;
                }
            }
        }
    }
    public int rangeBitwiseAnd(int m, int n) {
        return solution1(m,n);
    }
    // 超时
    public int solution1(int m,int n){
        int res=m;
        for (int i=m+1;i<=n;i++){
            res=res & i;
        }
        return res;
    }

    public  int solution2(int m,int n){
        if (m==0) return 0;
        int mCount=bitCount(m);
        int nCount=bitCount(n);
        if (nCount>mCount){
           return 0;
        }
        int res=m;
        for (int i=res+1;i<=n;i++){
            res=res & i;
        }
        return res;
    }

    /**
     *  由两个数m,n当其最长有效位数不同时，那么从m-n的与运算结果是0的，当两个数由相同的位数时，那么计算出两者的共同的最长前缀，后面的补0
     *
     * 综上所述，只要找到m,n的最长前缀即可。
     *
     * @param m
     * @param n
     * @return
     */
    public int solution3(int m,int n){
        int shift=0;
        while (m<n){
            m=m>>1;
            n=n>>1;
            shift++;
        }
        return m<<shift;
    }

    public int solution4(int m,int n){
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;

    }

    private int bitCount(int m){
        if (m==0) return 1;
        int res=0;
        while(m!=0){
            m=m>>1;
            res++;
        }
        return res;
    }
}
