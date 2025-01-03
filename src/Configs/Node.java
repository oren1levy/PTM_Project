package Configs;

import Graph.Message;

import java.util.ArrayList;
import java.util.List;


public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {return name;}
    public List<Node> getEdges() {return edges;}
    public Message getMsg() {return msg;}

    public void setName(String name) {this.name = name;}
    public void setEdges(List<Node> edges) {this.edges = edges;}
    public void setMsg(Message msg) {this.msg = msg;}

    public void addEdge(Node e) {
        edges.add(e);
    }

    public boolean hasCycles() {
        return hasCyclesHelper(new ArrayList<>());
    }

    private boolean hasCyclesHelper(List<Node> visited) {
        if (visited.contains(this)) {
            return true;
        }
        visited.add(this);
        for (Node child : edges) {
            if (child.hasCyclesHelper(new ArrayList<>(visited))) {
                return true;
            }
        }
        return false;
    }

}