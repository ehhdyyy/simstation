package src.mvc;

import java.util.*;

public class Publisher{

    private List<Subscriber> subscribers = new ArrayList<>();

    public void notifySubscribers(){
        for(Subscriber s : subscribers){
            s.update();
        }
    }

    public void subscribe(Subscriber sub){
        subscribers.add(sub);
    }

    public void unsubscribe(Subscriber sub){
        subscribers.remove(sub);
    }
}