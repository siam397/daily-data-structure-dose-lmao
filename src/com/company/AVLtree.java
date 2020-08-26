package com.company;

public class AVLtree {
    private class Node{
        public int value;
        public int height;
        public Node leftChild;
        public Node rightChild;
        public Node(int value){
            this.value=value;
            this.leftChild=null;
            this.rightChild=null;
            this.height=0;
        }

        @Override
        public String toString() {
            return ""+this.value;
        }
    }

    public Node root;

    public AVLtree(){
        this.root=null;
    }

    private int height(Node node){
        if(node==null){
            return -1;
        }
        return 1+ Math.max(height(node.leftChild),height(node.rightChild));
    }

    private int balanceFactor(Node node){
        return height(node.leftChild)-height(node.rightChild);
    }

    private boolean isLeftHeavy(Node node){
        if(balanceFactor(node)>1){
            return true;
        }return false;
    }

    private boolean isRightHeavy(Node node){
        if(balanceFactor(node)<-1){
            return true;
        }return false;
    }
    private Node rightRotate(Node node){
        Node newroot=node.leftChild;
        node.leftChild=newroot.rightChild;
        newroot.rightChild=node;
        newroot.height=height(newroot);
        node.height=height(node);
        return newroot;
    }

    private Node leftRotate(Node node){
        Node newroot=node.rightChild;
        node.rightChild=newroot.leftChild;
        newroot.leftChild=node;
        newroot.height=height(newroot);
        node.height=height(node);
        return newroot;
    }


    private Node balance(Node node){
        if(isRightHeavy(node)){
            if(balanceFactor(node.rightChild)>0){
                node.rightChild=rightRotate(node.rightChild);
            }
            return leftRotate(node);
        }else if(isLeftHeavy(node)){
            if(balanceFactor(node.leftChild)<0){
                node.leftChild=leftRotate(node.leftChild);
            }
            return rightRotate(node);
        }else{
            return node;
        }
    }

    public void insert(int value){
        root=insert(root,value);
    }

    private Node insert(Node node,int value){
        if(node==null){
            return new Node(value);
        }
        if(node.value>value){
            node.leftChild=insert(node.leftChild,value);
        }else{
            node.rightChild=insert(node.rightChild,value);
        }
        node.height=height(node);
        node=balance(node);
        return node;
    }

}
