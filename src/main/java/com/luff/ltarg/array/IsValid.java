package com.luff.ltarg.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lsq
 * @date 2020/8/14
 *
 * 20
 *
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
 */
public class IsValid {

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("{[(([]){()()})]}"));
    }
    public boolean isValid(String s) {
        return solution(s);
    }

    public boolean solution(String s){
        if (s==null || s.isEmpty()) return true;
        if (s.length()==1) return false;
        LinkedList<Character> stack=new LinkedList<>();
        for (char c:s.toCharArray()){
            switch (c){
                case ')': {
                    if (stack.isEmpty() || stack.pop() !='(') return false;
                    else break;
                }
                case '}':{
                    if (stack.isEmpty() || stack.pop()!='{') return false;
                    else break;
                }
                case ']':{
                    if (stack.isEmpty() || stack.pop()!='[') return false;
                    else break;
                }
                default:stack.push(c);
            }
//            if (stack.isEmpty()) {
//                switch (c){
//                    case '(':stack.push(')');break;
//                    case '{':stack.push('}');break;
//                    case '[':stack.push(']');break;
//                    default:return false;
//                }
//            }else{
//                if (c=='('||c=='{'||c=='['){
//                    switch (c){
//                        case '(':stack.push(')');break;
//                        case '{':stack.push('}');break;
//                        case '[':stack.push(']');break;
//                    }
//                }else if (c!=stack.pop()){
//                  return false;
//                }
//            }
        }
        return stack.isEmpty();
    }
}
