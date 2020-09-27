package com.luff.ltarg.binarySearch;

/**
 * @author lsq
 * @date 2020/7/22
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 */
public class MinArray {

    public static void main(String[] args) {
        System.out.println(minArray(new int[]{1,2}));
    }
    public static int minArray(int[] numbers) {
        return solution2(numbers);
    }

    // 1 3  3  3 1 3

    public static int solution2(int[] numbers){
        int len=numbers.length-1;
        for (int i=1;i<=len;i++){
            if (numbers[i]<numbers[i-1]){
                return numbers[i];
            }
        }
        return numbers[0];
    }

}
