package com.company;

import java.util.*;

public class Graph {
    private Map<String, ArrayList<String>>adjacencyList;
    public Graph(){
        this.adjacencyList=new HashMap<>();
    }
    public void addVertex(String node){
        this.adjacencyList.putIfAbsent(node,new ArrayList<>());
    }
    public void addDirectedEdge(String from,String to){
        if(this.adjacencyList.containsKey(from) && this.adjacencyList.containsKey(to)){
            this.adjacencyList.get(from).add(to);
        }
    }
    public void addEdge(String from,String to){
        if(this.adjacencyList.containsKey(from) && this.adjacencyList.containsKey(to)){
            this.adjacencyList.get(from).add(to);
            this.adjacencyList.get(to).add(from);
        }
    }
    public void removeVertex(String node){
        ArrayList<String>arr=this.adjacencyList.get(node);
        for(String neighbor: arr){
            this.adjacencyList.get(neighbor).remove(node);
        }
        this.adjacencyList.remove(node);
    }
    public void removeEdge(String from,String to){
        if(this.adjacencyList.containsKey(from) && this.adjacencyList.containsKey(to)){
            this.adjacencyList.get(from).remove(to);
            this.adjacencyList.get(to).remove(from);
        }
    }
    public void dfsRecursion(String node){
        System.out.println(node);
        Set<String>visited=new HashSet<>();
        dfsRecursion(node,visited);
    }
    private void dfsRecursion(String node, Set<String>visited){
         visited.add(node);
         for(String neighbor: this.adjacencyList.get(node)){
             if(!visited.contains(neighbor)){
                 System.out.println(neighbor);
                 dfsRecursion(neighbor,visited);
             }
         }
    }
    public void dfsIterate(String node){
        Stack<String>stack=new Stack<>();
        Set<String>visited=new HashSet<>();
        stack.push(node);
        while(!stack.isEmpty()){
            String current=stack.pop();
            visited.add(current);
            System.out.println(current);
            for(String neighbor:this.adjacencyList.get(current)){
                if(!visited.contains(neighbor)){
                    stack.push(neighbor);
                }
            }
        }
    }
    public void bfs(String node){
        Queue<String>queue=new ArrayDeque<>();
        Set<String>visited=new HashSet<>();
        queue.add(node);
        while(!queue.isEmpty()){
            String current=queue.remove();
            visited.add(current);
            System.out.println(current);
            for(String neighbor:this.adjacencyList.get(current)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                }
            }
        }
    }
    public List<String> topologicalSort(){
        Stack<String>stack=new Stack<>();
        Set<String>visited=new HashSet<>();
        for(String node:this.adjacencyList.keySet()){
            topologicalSort(node,visited,stack);
        }
        List<String>arr=new ArrayList<>();
        while(!stack.isEmpty()){
            arr.add(stack.pop());
        }
        return arr;
    }

    private void topologicalSort(String node,Set<String>visited,Stack<String>stack){
        if(visited.contains(node)){
            return;
        }
        visited.add(node);
        for(String neighbor:this.adjacencyList.get(node)){
            topologicalSort(neighbor,visited,stack);
        }
        stack.push(node);
    }

    public boolean hasCycle(){
        Set<String>all=new HashSet<>();
        all.addAll(this.adjacencyList.keySet());
        System.out.println(all);
        Set<String>visiting=new HashSet<>();
        Set<String>visited=new HashSet<>();
        while(!all.isEmpty()){
            String current=all.iterator().next();
            if(hasCycle(current,all,visiting,visited)){
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle(String node,Set<String>all,Set<String>visiting,Set<String>visited){
        all.remove(node);
        visiting.add(node);
        for(String neighbor:this.adjacencyList.get(node)){
            if(visited.contains(neighbor)){
                continue;
            }
            if(visiting.contains(neighbor)){
                return true;
            }
            boolean result=hasCycle(neighbor,all,visiting,visited);
            if(result){
                return true;
            }
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }

    public void printGraph(){
        for(String node:this.adjacencyList.keySet()){
            System.out.println(node+" : "+this.adjacencyList.get(node));
        }
    }
}
