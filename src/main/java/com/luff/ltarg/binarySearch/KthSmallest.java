package com.luff.ltarg.binarySearch;

/**
 * @author lsq
 * @date 2020/7/2
 *
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 */
public class KthSmallest {

    public static int kthSmallest(int[][] matrix, int k) {
        return solution1(matrix,k);
    }

    /**
     *
     * 使用二分查找，理论上对所有有序数组查询都可以使用二分查找。
     * 设参数l,r分别为矩阵的最小值和最大值。即l=matrix[0][0] r=matrix[len-1][len-1]
     *
     * 对于任意数mid 使得 l<=mid<=r 在矩阵中，大于和不大于mid的元素会有一条明显的分割线。
     *
     * 例如 如果 m[i][j]<=mid 那么可以得出 m[0][j]到m[i][j] 都是 <= mid 那么此时只要移动j++ 继续判断。， 否则i--向上移动。得出
     *
     * 在此情况下所有小于等于mid的个数 c 然后将c和k对比即可。
     *
     * @param matrix
     * @param k
     * @return
     */
    public static int solution1(int[][] matrix,int k){
        int n=matrix.length;
        int left=matrix[0][0],right=matrix[n-1][n-1];
        while (left<right){
            int mid=left+((right-left)>>1);
            if (check(matrix,k,mid,n)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left; //left是可以保证在矩阵中的，left,right不断逼近 第k小的数
    }

    // 小于等于mid
    public static boolean check(int[][] matrix,int k,int mid,int n){
        int i=n-1,j=0;
        int num=0;
        while (i>=0 && j<n){
            if(matrix[i][j]<=mid){
                num+=i+1;
                j++;
            }else{
                i--;
            }
        }
        return num>=k;
    }
}
