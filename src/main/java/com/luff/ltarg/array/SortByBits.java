package com.luff.ltarg.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortByBits {

    public static void main(String[] args) {
        int[] arr=new int[]{};
        int[] res = new SortByBits().sortByBits(arr);
        List<Integer> list = Arrays.stream(res).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
    public int[] sortByBits(int[] arr) {
       return Arrays.stream(arr).boxed().sorted((a1,a2)->{
           int aBit=countBit(a1);
           int bBit=countBit(a2);
           if(aBit==bBit){
               return a1.compareTo(a2);
           }
           return aBit-bBit;
        }).mapToInt(Integer::valueOf).toArray();
    }

    public int countBit(int val){
        int ans=0;
        while(val!=0){
            if ((val & 1)==1){
                ans+=1;
            }
            val=val>>>1;
        }
        return ans;
    }
}
