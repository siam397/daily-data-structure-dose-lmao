package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph=new Graph();
//        graph.addVertex("A");
//        graph.addVertex("B");
//        graph.addVertex("C");
//        graph.addVertex("D");
//        graph.addVertex("E");
//        graph.addEdge("A","B");
//        graph.addEdge("B","C");
//        graph.addEdge("A","D");
//        graph.addEdge("D","E");
////        graph.addEdge("B","C");
        graph.addVertex("X");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("P");
        graph.addDirectedEdge("X","A");
        graph.addDirectedEdge("A","P");
        graph.addDirectedEdge("X","B");
        graph.addDirectedEdge("B","P");
        graph.addDirectedEdge("P","X");
        System.out.println(graph.hasCycle());
    }
}
