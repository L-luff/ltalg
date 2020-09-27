package com.luff.ltarg.string;

import java.util.*;

/**
 * @author lsq
 * @date 2020/8/26
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinations {


    public static void main(String[] args) {
        List<String> res = new LetterCombinations().letterCombinations("23");
        System.out.println(res);
    }


    public List<String> letterCombinations(String digits) {
        return solution1(digits);
    }
    String[] map=new String[]{"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> solution1(String digits){
        if (digits.trim().length()==0) return Collections.emptyList();
        StringBuilder sb=new StringBuilder(digits.length());
        for (int i=0;i<digits.length();i++){
            sb.append("a");
        }
        List<String> res=new ArrayList<>();
        recursion(res,0,digits,sb);
        return res;
    }


    public void recursion(List<String> res,int index,String digits,StringBuilder sb){
        String s=map[Integer.parseInt(digits.charAt(index)+"")-1];
        if (index+1==digits.length()){
            for (int i=0;i<s.length();i++){
                sb.setCharAt(index,s.charAt(i));
                res.add(sb.toString());
            }
            return;
        }
        for (int i=0;i<s.length();i++){
            sb.setCharAt(index,s.charAt(i));
            recursion(res,index+1,digits,sb);
        }

    }

}
