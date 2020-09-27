package com.luff.ltarg.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/8/12
 */
public class CloneGraph {

    Node[] cloneNodes=new Node[101];

    public Node cloneGraph(Node node) {
        if(node==null) return null;
        Node cloneRoot=new Node(node.val);
        cloneNodes[node.val]=cloneRoot;
        dfs(node,cloneRoot);
        return cloneRoot;
    }

    private  void dfs(Node node,Node cloneNode){
        List<Node> neighbors = node.neighbors;
        if (neighbors==null) return ;
        for (Node n:neighbors){
            if (cloneNodes[n.val]!=null){
                cloneNode.neighbors.add(cloneNodes[n.val]);
            }else{
                Node tmp = new Node(n.val);
                cloneNode.neighbors.add(tmp);
                cloneNodes[tmp.val]=tmp;
                dfs(n,tmp);
            }
        }
    }

    public static void main(String[] args) {

    }
}
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}