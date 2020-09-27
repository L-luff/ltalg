package com.luff.ltarg.array;

/**
 * @author lsq
 * @date 2020/7/1
 *
 *
给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

示例：

输入：
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出：3
解释：
长度最长的公共子数组是 [3, 2, 1] 。
 */
public class FindLength {

    public static int findLength(int[] A, int[] B) {
        return solution2(A,B);
    }


    /**
     * 以二位数组为基础。 行为数组A，列为数组B===> array[i][j]
     *  当任意行和任意列 arr[i][j] 代表 数组A[0-i]和数组B[0-j]的后缀公共子数组长度
     *  因此计算arr[i][j]的后缀长度=arr[i-1][j-1] 需要考虑边界情况
     * @param A
     * @param B
     * @return
     */
    public static int solution1(int[] A,int[] B){
        int res=0;
        int[][] ar=new int[A.length][B.length];
        for (int i=0;i<A.length;i++){
            for (int j=0;j<B.length;j++){
                if(A[i]==B[j]){
                    if(i!=0 && j!=0){
                        ar[i][j]=ar[i-1][j-1];
                    }
                    ar[i][j]++;
                }
                res=Math.max(ar[i][j],res);
            }
        }
        return res;
    }


    /**
     * 使用一维数组,原理与二维数组类似，不过再处理的时候需要从“最后面”先处理。因为a[j]的值依赖于a[j-1],如果先处理A[j-1]则对A[J]有干扰
     *
     * @param A
     * @param B
     * @return
     */
    public static int solution2(int[] A,int[] B){
        int[] ar=new int[B.length];
        int res=0;
        for (int i=0;i<A.length;i++){
            for (int j=B.length-1;j>=0;j--){
                if(A[i]==B[j]){
                    ar[j]=1;
                    if(i!=0 && j!=0){
                        ar[j]+=ar[j-1];
                    }
                }else{
                    ar[j]=0;
                }
                res=Math.max(ar[j],res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A=new int[]{0,0,0,0,0};
        int[] B=new int[]{0,0,0,0,0};
        System.out.println(findLength(A,B));

    }
}
