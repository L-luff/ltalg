package com.luff.ltarg.array;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: lsq
 */

public class HalveArray {
    public static void main(String[] args) {
        System.out.println(new HalveArray().halveArray(new int[]{5,19,8,1}));
    }
    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> queue = new PriorityQueue<Double>(Comparator.reverseOrder());
        for (int num:nums){
            sum+=num;
            queue.add(Integer.valueOf(num).doubleValue());
        }
        double target = sum / 2;
        double val = sum;
        double pollVal;
        int count = 0;
        while(val > target){
            pollVal = queue.poll();
            pollVal /= 2;
            val -= pollVal;
            queue.add(pollVal);
            count++;
        }
        return count;
    }


}
