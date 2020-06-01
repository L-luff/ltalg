package com.luff.ltarg.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class KidsWithCandies {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max=0;
        for (int i=0;i<candies.length;i++){
            max=Math.max(candies[i],max);
        }
        List<Boolean> res=new ArrayList<>(candies.length);
        for (int i=0;i<candies.length;i++){
            res.add(candies[i]+extraCandies >= max);
        }
        return res;
    }

    public static void main(String[] args) {
        kidsWithCandies(new int[]{},1);
    }
}
