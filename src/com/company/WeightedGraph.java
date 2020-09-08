package com.company;

import java.util.*;

public class WeightedGraph {
    private class NodeEntry{
        public String node;
        public int priority;

        public NodeEntry(String node,int priority) {
            this.node = node;
            this.priority=priority;
        }
    }
    private class Edge{
        public String node;
        public int weight;
        public Edge(String node,int weight){
            this.node=node;
            this.weight=weight;
        }

        @Override
        public String toString() {
            return this.node+" "+this.weight;
        }
    }
    public HashMap<String, ArrayList<Edge>>adjacenyList;
    private int sum=0;
    public WeightedGraph(){
        this.adjacenyList=new HashMap<>();
    }
    public void print(){
        for(String neighbor:this.adjacenyList.keySet()){
            System.out.println(neighbor+": "+this.adjacenyList.get(neighbor));
        }
    }
    public void addVertex(String node){
        this.adjacenyList.putIfAbsent(node,new ArrayList<>());
    }
    public void addEdge(String from,String to,int weight){
        if(this.adjacenyList.containsKey(from) && this.adjacenyList.containsKey(to)){
            this.adjacenyList.get(from).add(new Edge(to,weight));
            this.adjacenyList.get(to).add(new Edge(from,weight));
        }
        this.sum=this.sum+weight;
    }
    public int getShortestDistance(String from,String to){
        HashMap<String,Integer>distances=new HashMap<String, Integer>();
        Set<String>visited=new HashSet<>();
        for(String node:this.adjacenyList.keySet()){
            distances.put(node,Integer.MAX_VALUE);
        }
        distances.replace(from,0);
        PriorityQueue<NodeEntry>queue=new PriorityQueue<>(Comparator.comparingInt(ne->ne.priority));
        queue.add(new NodeEntry(from,0));
        while(!queue.isEmpty()){
            NodeEntry node=queue.remove();
            visited.add(node.node);
            for(Edge neighbor:this.adjacenyList.get(node.node)){
                if(!visited.contains(neighbor.node)){
                    int newDistance=distances.get(node.node)+ neighbor.weight;
                    if(newDistance<distances.get(neighbor.node)){
                        distances.replace(neighbor.node, newDistance);
                        queue.add(new NodeEntry(neighbor.node,newDistance));
                    }
                }
            }
        }
        return distances.get(to);
    }
    public List<String>shortestPath(String from,String to){
        List<String>path=new ArrayList<>();
        HashMap<String,Integer>distances=new HashMap<String, Integer>();
        Set<String>visited=new HashSet<>();
        HashMap<String,String>parent=new HashMap<>();
        parent.put(from,null);
        for(String node:this.adjacenyList.keySet()){
            distances.put(node,Integer.MAX_VALUE);
        }
        distances.replace(from,0);
        PriorityQueue<NodeEntry>queue=new PriorityQueue<>(Comparator.comparingInt(ne->ne.priority));
        queue.add(new NodeEntry(from,0));
        while(!queue.isEmpty()){
            NodeEntry node=queue.remove();
            visited.add(node.node);
            for(Edge neighbor:this.adjacenyList.get(node.node)){
                if(!visited.contains(neighbor.node)){
                    int newDistance=distances.get(node.node)+ neighbor.weight;
                    if(newDistance<distances.get(neighbor.node)){
                        distances.replace(neighbor.node, newDistance);
                        parent.put(neighbor.node, node.node);
                        queue.add(new NodeEntry(neighbor.node,newDistance));
                    }
                }
            }
        }
        Stack<String>stack=new Stack<>();
        stack.push(to);
        String node=to;
        while(parent.get(node)!=null){
            stack.push(parent.get(node));
            node=parent.get(node);
        }
        while (!stack.isEmpty()){
            path.add(stack.pop());
        }
        return path;
    }
    public boolean hasCycle(){
        Set<String>visited=new HashSet<>();
        for(String keys:this.adjacenyList.keySet()){
            if(!visited.contains(keys)){
                boolean result = hasCycle(keys,null, visited);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasCycle(String node,String parent,Set<String>visited){
        visited.add(node);
        for(Edge edge:this.adjacenyList.get(node)){
            if(edge.node.equals(parent))
                continue;
            if(visited.contains(edge.node)){
                return true;
            }
            boolean result=hasCycle(edge.node,node, visited);
            if(result){
                return true;
            }
        }
        return false;
    }
    public WeightedGraph minimumSpanningTree(){
        WeightedGraph tree=new WeightedGraph();
        PriorityQueue<Edge>edges=new PriorityQueue<>(Comparator.comparingInt(e->e.weight));
        String startNode=this.adjacenyList.keySet().iterator().next();
        edges.addAll(this.adjacenyList.get(startNode));
        tree.addVertex(startNode);
        while(tree.adjacenyList.size()<this.adjacenyList.size()){
            Edge node=edges.remove();
            if(!tree.adjacenyList.containsKey(node.node)){
                tree.addVertex(node.node);
                tree.addEdge(startNode,node.node,node.weight);
                startNode=node.node;
                for(Edge edge:this.adjacenyList.get(node.node)){
                    if(!tree.adjacenyList.containsKey(edge.node)){
                        edges.add(edge);
                    }
                }
            }
        }
        return tree;
    }
    public int SumOfEdgeWeights(){
        return this.sum;
    }
}
