package com.luff.ltarg.string;

import java.util.*;

/**
 * @author lsq
 * @date 2020/10/14
 */
public class CommonChars {

    public static void main(String[] args) {
        String[] s=new String[]{"bella"};
        System.out.println(new CommonChars().commonChars(s));
    }

    public List<String> commonChars(String[] A) {
        int len=A.length;
        Map<Character,List<Integer>> contains=new HashMap<>(26);
        Set<Character> first=new HashSet<>();
        for(int i=0;i<len;i++){
            String s=A[i];
            for(int j=0;j<s.length();j++){
                char c=s.charAt(j);
                if(i==0){
                    first.add(c);
                }
                List<Integer> list=contains.get(c);
                if(list==null){
                    list=new ArrayList<>(len+1);
                    for(int k=0;k<len;k++){
                        list.add(0);
                    }
                    contains.put(c,list);
                }
                list.set(i,list.get(i)+1);
            }
        }
        List<String> res=new ArrayList<>(100);
        for(char c:first){
            List<Integer> list=contains.get(c);
            if(list.size()==len){
                int min=Integer.MAX_VALUE;
                for(int i=0;i<list.size();i++){
                    min=Math.min(min,list.get(i));
                }
                for(int i=0;i<min;i++){
                    res.add(String.valueOf(c));
                }
            }
        }
        return res;
    }
}
