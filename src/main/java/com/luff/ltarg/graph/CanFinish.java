package com.luff.ltarg.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/8/4
 */
public class CanFinish {

    public static void main(String[] args) {
        int[][] ar=new int[][]{
                {1,0}
        };
        System.out.println(new CanFinish().canFinish(2,ar));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return solution1(numCourses,prerequisites);
    }


    public static int[] visited;
    public static boolean res=true;

    /**
     * 解法1 ：通过DFS
     * 解法2： 可以通过bfs,具体代码可以参考 FindOrder
     * @see  FindOrder
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean solution1(int numCourses,int[][] prerequisites){
        visited=new int[numCourses];
        List<List<Integer>> list=new ArrayList<>(numCourses);
        for (int i=0;i<numCourses;i++){
            list.add(new ArrayList<>(numCourses));
        }
        for (int[] ar:prerequisites){
            List<Integer> l = list.get(ar[0]);
            l.add(ar[1]);
        }
        for (int i=0;i<numCourses;i++){
            if (visited[i]==0){
                recursive(list,i);
            }
        }
        return res;
    }
    private static void recursive(List<List<Integer>> list,int index){
        visited[index]=1;
        for (int val:list.get(index)){
            if (visited[val]==0){
                recursive(list,val);
                if (!res){
                    return ;
                }
            }else if (visited[val]==1){
                res=false;
                return ;
            }
        }
        visited[index]=2;
    }


}
