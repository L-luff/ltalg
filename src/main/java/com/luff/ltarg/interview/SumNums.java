package com.luff.ltarg.interview;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *
 */
public class SumNums {

//    public int sumNums(int n) {
//        return recursive(n,1);
//    }
//
//    public int recursive(int n,int i){
//        if(i>n) return 0;
//        return i+recursive(n,i+1);
//    }

    public int sumNums(int n) {
        boolean flag=n>0 &&  (n+=sumNums(n-1))>0;
        return n;
    }

}
