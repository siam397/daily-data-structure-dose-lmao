package com.company;

import java.util.ArrayList;

public class Heap {
    public ArrayList<Integer>heaps;
    public Heap(){
        this.heaps=new ArrayList<>();
    }
    public void insert(int value){
        heaps.add(value);
        if(heaps.size()==1){
            return;
        }
        int currentIndex=heaps.size()-1;
        while(currentIndex>=0){
            int parentIndex=(currentIndex-1)/2;
            if(heaps.get(currentIndex)>heaps.get(parentIndex)){
                int temp=heaps.get(currentIndex);
                heaps.set(currentIndex,heaps.get(parentIndex));
                heaps.set(parentIndex,temp);
                currentIndex=parentIndex;
            }else{
                break;
            }
        }
    }
    public int remove() {
        if (heaps.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            int toreturn = heaps.get(0);
            heaps.set(0,heaps.get(heaps.size()-1));
            heaps.remove(heaps.size()-1);
            int currentIndex=0;
            while(currentIndex<heaps.size()-1){
                int leftChild=(currentIndex*2)+1;
                int rightChild=(currentIndex*2)+2;
                int swap=currentIndex;
                if(leftChild<heaps.size()){
                    if(heaps.get(leftChild)>heaps.get(swap)){
                        swap=leftChild;
                    }
                }
                if(rightChild<heaps.size()){
                    if(heaps.get(rightChild)>heaps.get(swap)){
                        swap=rightChild;
                    }
                }
                if(swap==currentIndex || leftChild>heaps.size() || rightChild>heaps.size()){
                    break;
                }
                int temp=heaps.get(swap);
                heaps.set(swap,heaps.get(currentIndex));
                heaps.set(currentIndex,temp);
                currentIndex=swap;
            }
            return toreturn;

        }
    }
    public ArrayList<Integer> heapify(ArrayList<Integer>arr){
        Heap heap=new Heap();
        for(int i=0;i<=arr.size()/2-1;i++){
            heap.insert(arr.get(i));
        }
        return heap.heaps;
    }
    public Heap create(ArrayList<Integer>arr){
        Heap heap=new Heap();
        for(int i=0;i<arr.size();i++){
            heap.insert(arr.get(i));
        }
        return heap;
    }
    public ArrayList<Integer> heapSort(){
        ArrayList<Integer>sorted=new ArrayList<>();
        Heap heap=create(this.heaps);
        int size=this.heaps.size();
        for(int i=0;i<size;i++){
            sorted.add(heap.remove());
        }
        return sorted;
    }
    public ArrayList<Integer> heapSort(ArrayList<Integer>heaps){
        ArrayList<Integer>sorted=new ArrayList<>();
        Heap heap=create(heaps);
        for(int i=0;i<heaps.size();i++){
            sorted.add(heap.remove());
        }
        return sorted;
    }
    public int kthMax(int n){
        if(n<=0 || n>this.heaps.size()){
            throw new IllegalArgumentException();
        }
        Heap heap=create(this.heaps);
        int x=0;
        for(int i=0;i<n;i++){
            x=remove();
        }
        return x;
    }
    public void print(){
        for(int i=0;i<heaps.size();i++){
            System.out.print(heaps.get(i)+" ");
        }
    }
}
