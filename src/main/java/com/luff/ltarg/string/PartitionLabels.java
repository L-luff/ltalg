package com.luff.ltarg.string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public static void main(String[] args) {
        String s="ababcbacadefegdehijhklij";
        System.out.println(new PartitionLabels().partitionLabels(s));
    }

    public List<Integer> partitionLabels(String S) {
        int[] chars=new int[26];
        for (int i=0;i<S.length();i++){
            chars[S.charAt(i)-'a']=i;
        }
        List<Integer> ans=new ArrayList<>(S.length());
        int maxIndex=0,lastIdx=0;
        for (int i=0;i<S.length();i++){
            maxIndex=Math.max(maxIndex,chars[S.charAt(i)-'a']);
            if(maxIndex==i){
                ans.add(maxIndex-lastIdx+1);
                lastIdx=i+1;
            }
//            if(maxIndex==i){
//                ans.add(i-lastIdx);
//                lastIdx=i;
//                maxIndex=-1;
//                continue;
//            }
//            int idx=chars[S.charAt(i)-'a'];
//            if(maxIndex==-1 && idx==i){
//                ans.add(1);
//                lastIdx=i;
//                maxIndex=-1;
//                continue;
//            }
//            maxIndex=Math.max(idx,maxIndex);

        }
        return ans;
    }
}
