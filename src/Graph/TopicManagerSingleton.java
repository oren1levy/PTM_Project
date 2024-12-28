package Graph;

import java.util.concurrent.ConcurrentHashMap;

public class TopicManagerSingleton {

    public static class TopicManager {
        private final ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();

        public Topic getTopic(String name) {
            return topics.computeIfAbsent(name, Topic::new);
        }

        public ConcurrentHashMap<String, Topic> getTopics() {
            return topics;
        }

        public void clear() {
            topics.clear();
        }
    }

    private static final TopicManager instance = new TopicManager();

    private TopicManagerSingleton() {}

    public static TopicManager get() {
        return instance;
    }
}
