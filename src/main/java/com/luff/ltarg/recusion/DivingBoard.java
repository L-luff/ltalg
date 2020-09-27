package com.luff.ltarg.recusion;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author lsq
 * @date 2020/7/8
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000

 */
public class DivingBoard {

    public static void main(String[] args) {
        int[] ints = divingBoard(1,1,10000);
        for (int i:ints){
            System.out.print(i+"    ");
        }
        System.out.println();
        int[] ints2=solution(1,1,10000);
        for (int i:ints2){
            System.out.print(i+"    ");
        }
    }
    public static int[] divingBoard(int shorter, int longer, int k) {
        return solution2(shorter,longer,k);
    }

    /**
     *  可以假设先全部使用短的木板。即 k个段木板。然后逐渐将短的木板换成长度木板，直到全部换成长木板。
     *  每次相对于上次替换都会增加（long-shorter）的长度。 共有k+1种可能
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public static int[] solution2(int shorter,int longer,int k){
        if (k==0) return new int[0];
        int max=longer *k;
        int min=shorter * k;
        int r=longer-shorter;
        int count=  1;
        if (r!=0){
            count+=(max-min) / r;
        }
        int[] res=new int[count];
        for (int i=0;i<count;i++){
            res[i]=min+r*i;
        }
        return res;
    }

    /**
     * 使用递归， 超出时间限制
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public static int[] solution(int shorter,int longer,int k){
        if (k==0){
            return new int[0];
        }
        if (shorter==longer){
            return new int[]{shorter * k};
        }
        TreeSet<Integer> set=new TreeSet<>();
        recursive(shorter,longer,k,0,set);
        int[] res=new int[set.size()];
        int index=0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            res[index++]=iterator.next();
        }
        return res;
    }

    public static void recursive(int shorter,int longer,int k,int sum,TreeSet<Integer> set){
        if (k==0){
            set.add(sum);
            return ;
        }
        recursive(shorter,longer,k-1,sum+shorter,set);
        recursive(shorter,longer,k-1,sum+longer,set);
    }
}
