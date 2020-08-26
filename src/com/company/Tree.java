package com.company;

public class Tree {
    class Node{
        int value;
        Node leftChild;
        Node rightChild;
        public Node(int value){
            this.value=value;
            this.leftChild=null;
            this.rightChild=null;
        }
    }
    public Node root;
    public Tree(){
        this.root=null;
    }
    public void insert(int value){
        Node node=new Node(value);
        Node currentNode=root;
        if(root==null){
            root=node;
            return;
        }
        while (currentNode!=null){
            if(value< currentNode.value){
                if(currentNode.leftChild==null){
                    currentNode.leftChild=node;
                    break;
                }
                currentNode=currentNode.leftChild;

            }else {

                if(currentNode.rightChild==null){
                    currentNode.rightChild=node;
                    break;
                }
                currentNode=currentNode.rightChild;
            }
        }
    }

    public boolean find(int value){
        Node currentNode=root;
        while(currentNode!=null){
            if(currentNode.value==value){
                return true;
            }else if(currentNode.value>value){
                currentNode=currentNode.leftChild;
            }else{
                currentNode=currentNode.rightChild;
            }
        }
        return false;
    }
    public void swapChild(){
        Node temp=root.leftChild;
        root.leftChild=root.rightChild;
        root.rightChild=temp;
    }
    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    public boolean endOfTree(Node node){
        return node.leftChild == null && node.rightChild == null;
    }
    private void preOrderTraversal(Node node){
        if(node==null){
            return;
        }else{
            System.out.println(node.value);
            if(node.leftChild!=null){
                preOrderTraversal(node.leftChild);
            }
            if(node.rightChild!=null){
                preOrderTraversal(node.rightChild);
            }
        }
    }
    public void inorderTraversal(){
        inorderTraversal(root);
    }
    private void inorderTraversal(Node node){
        if(node==null){
            return;
        }else{
            if(node.leftChild!=null){
                inorderTraversal(node.leftChild);
            }
            System.out.println(node.value);
            if(node.rightChild!=null){
                inorderTraversal(node.rightChild);
            }
        }
    }
    public void postorderTraversal(){
        postorderTraversal(root);
    }
    private void postorderTraversal(Node node){
        if(node==null){
            return;
        }else{
            if(node.leftChild!=null){
                postorderTraversal(node.leftChild);
            }
            if(node.rightChild!=null){
                postorderTraversal(node.rightChild);
            }
            System.out.println(node.value);
        }
    }

    public int height(){
        return height(root);
    }
    public int height(Node node){
        if(node==null){
            return 0;
        }
        if(endOfTree(node)){
            return 0;
        }
        if(root==null){
            return -1;
        }
        return 1+Math.max(height(node.leftChild),height(node.rightChild));
    }

    public int minValue(){
        return minValue(root);
    }
    private int minValue(Node node){
        if(endOfTree(node)){
            return node.value;
        }
        if(node.rightChild==null){
            return Math.min(minValue(node.leftChild), node.value);
        }else if(node.leftChild==null){
            return Math.min(minValue(node.rightChild), node.value);
        }else
            return Math.min(Math.min(minValue(node.leftChild),minValue(node.rightChild)), node.value);
    }
    public boolean isEqual(Tree tree){
        return isEqual(root,tree.root);
    }
    private boolean isEqual(Node first,Node second){
        if(first==null && second==null){
            return true;
        }
        if(first.value==second.value){
            if(first.rightChild!=null && second.rightChild!=null && second.leftChild!=null && second.leftChild!=null){
                return isEqual(first.leftChild, second.leftChild) && isEqual(first.rightChild, second.rightChild);
            }
        }
        return false;
    }
    public boolean isBinary(){
        return isBinary(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean isBinary(Node node,int min,int max){
        if(endOfTree(node)){
            return true;
        }
        if(node.value<min || node.value>max){
            return false;
        }
        if(node.leftChild==null) {
            return isBinary(node.rightChild, node.value, max);
        }else if(node.rightChild==null){
            return isBinary(node.leftChild, min, node.value);
        }
        else{
            return isBinary(node.leftChild,min,node.value) && isBinary(node.rightChild, node.value, max);
        }
    }
    public void nthNode(int n){
        nthNode(root,n);
    }
    private void nthNode(Node node,int n){
        if(n==0){
            System.out.println(node.value);
        }
        if(node.leftChild!=null){
            nthNode(node.leftChild,n-1);
        }
        if(node.rightChild!=null){
            nthNode(node.rightChild,n-1);
        }
    }
    public void bfs(){
        for(int i=0;i<=height();i++){
            nthNode(i);
        }
    }
}
