package com.luff.ltarg.struct;

/**
 * @author lsq
 * @date 2020/10/10
 */

/**
 * 树状数组
 *
 *
 *
 */
public class BinaryIndexedTree {

    public static void main(String[] args) {
        int[] arr=new int[]{2,3,5,1};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(arr);
//        for (int i=1;i<=arr.length;i++){
//            System.out.println(binaryIndexedTree.prefixSum(i));
//        }
        binaryIndexedTree.update(1,3);
        for (int i=1;i<=arr.length;i++){
            System.out.println(binaryIndexedTree.prefixSum(i));
        }
    }


    private int indexedArr[];
    public BinaryIndexedTree(int[] arr){
        indexedArr=new int[arr.length+1];
        for (int i=0;i<arr.length;i++){
            indexedArr[i+1]=arr[i];
        }

        for (int i=1;i<indexedArr.length;i++){
            int idx=i+idx(i);
            if(idx<indexedArr.length){
                indexedArr[idx]+=indexedArr[i];
            }
        }

    }

    public void update(int idx,int val){
        idx=idx+1;
        while(idx<indexedArr.length){
            indexedArr[idx]+=val;
            idx+=idx(idx);
        }
    }


    public int prefixSum(int count){
        count=count<=0 ? 1 : count;
        count=count>=indexedArr.length ? indexedArr.length-1 : count;
        int res=0;
        while(count>0){
            res+=indexedArr[count];
            count-=idx(count);
        }
        return res;
    }


    public int rangeSum(int fromIdx,int toIdx){
        return prefixSum(toIdx)-prefixSum(fromIdx);
    }


    private int idx(int index){
        return index & (-index);
    }

}
