package Configs;

import Graph.*;
import Graph.TopicManagerSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph extends ArrayList<Node> {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public int size() {
        return nodes.size();
    }

    public void createFromTopics() {
        nodes.clear();
        TopicManagerSingleton.TopicManager topicManager = TopicManagerSingleton.get();
        Map<String, Node> nodeMap = new HashMap<>();

        for (String topicName : topicManager.getTopics().keySet()) {
            nodeMap.putIfAbsent("T" + topicName, new Node("T" + topicName));
        }

        for (Topic topic : topicManager.getTopics().values()) {
            for (Agent agent : topic.getPubs()) {
                String agentName = "A" + agent.getName();
                nodeMap.putIfAbsent(agentName, new Node(agentName));
            }
        }

        for (Topic topic : topicManager.getTopics().values()) {
            Node topicNode = nodeMap.get("T" + topic.name);
            if (topicNode == null) {
                throw new NullPointerException("Topic node for " + topic.name + " is null");
            }

            for (Agent agent : topic.getPubs()) {
                Node agentNode = nodeMap.get("A" + agent.getName());
                if (agentNode != null) {
                    topicNode.addEdge(agentNode);
                }
            }

            for (Agent agent : topic.getSubs()) {
                Node agentNode = nodeMap.get("A" + agent.getName());
                if (agentNode != null) {
                    agentNode.addEdge(topicNode);
                }
            }
        }

        nodes.addAll(nodeMap.values());
    }


    public boolean hasCycles() {
        for (Node node : nodes) {
            if (node.hasCycles()) {
                return true;
            }
        }
        return false;
    }
}
