package com.luff.ltarg.hash;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/16
 */
public class ComputeSimilarities {


    public static void main(String[] args) {
        int[][] docs=new int[][]{
                {14, 15, 100, 9, 3},
                {32, 1, 9, 3, 5},
                {15, 29, 2, 6, 8, 7},
                {7, 10}
        };
        List<String> res = new ComputeSimilarities().solution2(docs);
        System.out.println(res);
    }
    public List<String> computeSimilarities(int[][] docs) {
        return solution1(docs);
    }
    // 超出时间限制
    // 思路： 定义map 当前key: docs文档中的每个数据值，value：为set,代表当前docs中包含key的所有行索引
    // 相当于倒排索引，对于每一行i,遍历行中的数据，填充map中倒排索引值，然后对于0~i-1的每一行和第i行进行比较
    // 解析交集和并集
    public List<String> solution1(int[][] docs){
        if (docs==null) return Collections.emptyList();
        Map<Integer, Set<Integer>> dict=new HashMap<>(256);
        List<String> res=new ArrayList<>(docs.length * 2);
        for (int i=0;i<docs.length;i++){
            for (int j=0;j<docs[i].length;j++){
                int d=docs[i][j];
                Set<Integer> set = dict.get(d);
                if (set==null){
                    set=new HashSet<>();
                    dict.put(d,set);
                }
                set.add(i);
            }
            if (i!=0){
                for (int k=i-1;k>=0;k--){
                    int sum=0;
                    for (int x=0;x<docs[i].length;x++){
                        int v=docs[i][x];
                        if (dict.get(v).contains(k)){
                            sum++;
                        }
                    }
                    if (sum!=0) {
                        String val = String.format("%.4f",(double)sum/ (docs[i].length + docs[k].length - sum)) ;
                        res.add(new StringBuilder().append(k).append(",").append(i).append(": ").append(val).toString());
                    }
                }
            }
        }
        return res;
    }

    /**
     * 与上一个解法唯一不同的是：不再使用两层for循环来计算当前第i行和0~i-1行之前的交并集，而是定义一个helper的二维数组，helper[i][j]
     * 代表第i行和第j行的并集个数，那么将两层for改为了一层for
     * @param docs
     * @return
     */
    public List<String> solution2(int[][] docs){
        if (docs==null) return Collections.emptyList();
        Map<Integer, List<Integer>> dict=new HashMap<>(256);
        List<String> res=new ArrayList<>(docs.length * 2);
        int[][] helper=new int[docs.length][docs.length];
        for (int i=0;i<docs.length;i++){
            for (int j=0;j<docs[i].length;j++){
                int d=docs[i][j];
                List<Integer> list = dict.get(d);
                if (list==null){
                    list=new ArrayList<>(docs[i].length);
                    dict.put(d,list);
                }else{
                    for (int preRow:list){// 和之前行数有相同的数据
                        helper[i][preRow]++;
                    }
                }
                list.add(i);
            }
            for (int k=0;k<docs.length;k++){
                int sum=helper[i][k];
                if (sum!=0){
                    String val = String.format("%.4f",(double)sum/ (docs[i].length + docs[k].length - sum)) ;
                    res.add(new StringBuilder().append(k).append(",").append(i).append(": ").append(val).toString());
                }
            }
        }
        return res;
    }
}
