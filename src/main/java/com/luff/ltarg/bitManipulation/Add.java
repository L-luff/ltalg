package com.luff.ltarg.bitManipulation;

/**
 * @Classname Add
 * @Description
 * @Date 2020/5/19 19:20
 * @Created by li
 */

/**
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数

 */
public class Add {
    public int add(int a, int b) {
        int flag=0,res=0;
        for (int i=0;i<32;i++){
            int ts=0;
            int ta=(a>>i)&1,tb=(b>>i)&1;
            if((ta ^ tb)==1){
                if(flag==0){
                    ts=1;
                }else{
                    ts=0;
                }
            }else{
                ts=flag;
                flag=ta;
            }
            res|=ts<<i;
        }
        return res;
    }

    /**
     * 异或被称为 半加法，运算法则相当于不带进位的加法，
     *
     * 将相加和进位分为两步，
     *
     *      step1： 完成不进位加法 a ^ b
     *      step2:  完成进位运算  a & b
     *      step3:  将step2左移一位，模拟正常加法的向前前进一位，一直到没有进位为止。
     * @param a
     * @param b
     * @return
     */
    public int add2(int a,int b){
        if(b==0) return a;
        int s1=0,s2=0;
        while(b!=0){
            s1=a ^ b;
            s2=a & b;
            s2=s2<<1;
            a=s1;
            b=s2;
        }
        return s1;
    }

    public static void main(String[] args) {
        System.out.println(new Add().add2(Integer.MIN_VALUE,3));
        System.out.println(Integer.MIN_VALUE+3);
    }
}
