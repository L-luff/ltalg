package com.luff.ltarg.array;

import java.util.HashMap;

public class SubarraysDivByK {

    public int subarraysDivByK(int[] A, int K) {
        return solution2(A,K);
    }

    public int solution1(int[] A,int K){
        int lastIndex=A.length-1;
        int res=0;
        for (int i=0;i<lastIndex;i++){
            int s=0;
            for (int j=i;j<=lastIndex;j++){
                s+=A[j];
                if(s % K ==0){
                    res++;
                }
            }

        }
        return res;
    }

    /**
     * 同于定理。给定整数A,B 如果A % K == B % K 假设A>B 则 （A-B）% K ==0
     * @param A
     * @param K
     * @return
     */
    public int solution2(int[] A,int K){
        HashMap<Integer,Integer> map=new HashMap<>(A.length);
        map.put(0,1);
        int sum=0,res=0,remain=0,remainCount=0;
        for (int i=0;i<A.length;i++){
            sum+=A[i];
            remain=(sum % K +K)%K;
            remainCount=map.getOrDefault(remain,0);
            map.put(remain,remainCount+1);
            res+=remainCount;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraysDivByK().subarraysDivByK(new int[]{2,-2,2,-4},6));
    }
}
