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
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addDirectedEdge("A","B");
        graph.addDirectedEdge("A","C");
        graph.addDirectedEdge("B","D");
        graph.addDirectedEdge("D","F");
        graph.addDirectedEdge("F","E");
        graph.addDirectedEdge("E","C");
        graph.addDirectedEdge("C","A");
        System.out.println(graph.hasCycle());
    }
}
