package Graph;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    private final List<Agent> subs = new ArrayList<>();
    private final List<Agent> pubs = new ArrayList<>();

    public Topic(String name){
        this.name=name;
    }

    public void subscribe(Agent a){
        if(!subs.contains(a)){
            subs.add(a);
        }
    }
    public void unsubscribe(Agent a){
        subs.remove(a);
    }

    public void publish(Message m){
        for (Agent agent : subs) {
            agent.callback(name, m);
        }
    }

    public void addPublisher(Agent a){
        if(!pubs.contains(a)){
            pubs.add(a);
        }
    }

    public void removePublisher(Agent a){
        pubs.remove(a);
    }

    public List<Agent> getPubs() {
        return pubs;
    }

    public List<Agent> getSubs() {
        return subs;
    }
}
