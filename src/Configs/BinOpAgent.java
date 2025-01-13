package Configs;

import Graph.*;
import Graph.TopicManagerSingleton.TopicManager;

import java.util.function.BinaryOperator;

public class BinOpAgent implements Agent {
    private String agent;
    private String topic1;
    private String topic2;
    private String result;
    private BinaryOperator<Double> operation;
    private Double input1;
    private Double input2;

    public BinOpAgent(String agent, String topic1, String topic2, String result, BinaryOperator<Double> operation) {
        this.agent = agent;
        this.topic1 = topic1;
        this.topic2 = topic2;
        this.result = result;
        this.operation = operation;

        TopicManagerSingleton.TopicManager topicManager = TopicManagerSingleton.get();
        Topic t1 = topicManager.getTopic(topic1);
        Topic t2 = topicManager.getTopic(topic2);
        Topic res = topicManager.getTopic(result);

        t1.subscribe(this);
        t2.subscribe(this);

        res.addPublisher(this);

        this.input1 = null;
        this.input2 = null;
    }

    @Override
    public String getName() {
        return agent;
    }

    @Override
    public void reset() {
        input1 = null;
        input2 = null;
    }

    @Override
    public void callback(String topic, Message msg) {
        if (topic.equals(topic1)) {
            input1 = msg.asDouble;
        } else if (topic.equals(topic2)) {
            input2 = msg.asDouble;
        }

        if (input1 != null && input2 != null) {
            double resultValue = operation.apply(input1, input2);
            TopicManagerSingleton.get().getTopic(result).publish(new Message(resultValue));
            reset();
        }
    }

    @Override
    public void close() {
    }
}

