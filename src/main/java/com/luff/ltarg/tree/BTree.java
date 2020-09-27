package com.luff.ltarg.tree;

/**
 * @author lsq
 * @date 2020/7/1
 */
public class BTree<K extends Comparable<K>,V> {
    private static int DEFAULT_ENTRY_SIZE=1024;
    private int entrySize=DEFAULT_ENTRY_SIZE;
    private Node root;
    private int height;
    private int n;

    private  class Node{
        private int m;  // number of children
        private Entry[] entries=new Entry[entrySize];

        public Node(int m) {
            this.m = m;
        }
    }
    private class Entry<K,V>{
        private K k;
        private V v;
        private Node next;

        public Entry(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    public BTree(){
        this(DEFAULT_ENTRY_SIZE);
    }
    public BTree(int size){
        if(size<=0) throw new RuntimeException("size too small");
        this.entrySize=size;
        this.root=new Node(0);
    }


}
