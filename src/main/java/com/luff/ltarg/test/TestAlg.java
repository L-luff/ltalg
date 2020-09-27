package com.luff.ltarg.test;

/**
 * @author lsq
 * @date 2020/7/11
 */
public class TestAlg {

    //计算x在遇到最低为1之前0的个数为i 则lowbit的返回值是： 2^i
    public static int lowbit(int x){
        return x & (-x);
    }

    int[] array;
    int[] indexTree;

    public TestAlg(int[] array){
        this.array=array;
        int len=array.length;
        indexTree=new int[len];

    }
    // nums[i]小于右侧的数量
    // 1:对数组离散化，然后从小达到排序 5,5,2,3,6->5,2,3,6->2,3,5,6
    //

    //对于数组array,索引i所管理的区间和为：[i-lowbit(i),i]
    public static void main(String[] args) {
        System.out.println(lowbit(3));
    }
    /**
     *
     * 011
     * 100
     * 1000
     *
     * 101
     * 110
     * 1000
     *
     * 索引+ lowbit(索引)=下一个需要修改的索引值
     *
     *
     */
}
