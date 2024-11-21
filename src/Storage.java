import exception.ConsumerNotAuthorized;
import exception.TopicNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

  Map<String, Topic> topics = new HashMap<>();

  public static Storage instance;

  private Storage() {

  }

  public static Storage getInstance() {
    if(instance == null) {
      instance = new Storage();
    }
    return instance;
  }

  public void addTopic(Topic topic) {
    topics.put(topic.getId(), topic);
  }

  public boolean publish(Message message, String topicId) {
    if(topics.containsKey(topicId)) {
      topics.get(topicId).getMessages().add(message);
      System.out.println("Published message " + message.toString());
      return true;
    } else {
     throw new TopicNotFoundException(topicId);
    }
  }

  public List<Message> poll(String consumerId, String topicId) {
    if(topics.containsKey(topicId)) {
      if(topics.get(topicId).getConsumerOffset().containsKey(consumerId)) {
        int offset = topics.get(topicId).getConsumerOffset().get(consumerId);
        List<Message> ans = new ArrayList<>();
        List<Message> messages = topics.get(topicId).getMessages();
        int i=0;
        for( i=offset; i<Math.min(offset+1, messages.size()) ; ++i) {
          ans.add(messages.get(i));
        }
        topics.get(topicId).getConsumerOffset().put(consumerId, i);
        return ans;
      } else {
        throw new ConsumerNotAuthorized(topicId, consumerId);
      }

    } else {
      throw new TopicNotFoundException(topicId);
    }
  }

  public List<Message> poll(String consumerId, String topicId, int pollCount) {
    if(topics.containsKey(topicId)) {
      if(topics.get(topicId).getConsumerOffset().containsKey(consumerId)) {
        int offset = topics.get(topicId).getConsumerOffset().get(consumerId);
        List<Message> ans = new ArrayList<>();
        List<Message> messages = topics.get(topicId).getMessages();
        int i=0;
        for( i=offset; i<Math.min(offset+pollCount, messages.size()) ; ++i) {
          ans.add(messages.get(i));
        }
        topics.get(topicId).getConsumerOffset().put(consumerId, i);
        return ans;
      } else {
        System.out.println("Consumer: " + consumerId + " not subscribed on topics: " + topicId);
        return null;
      }

    } else {
      System.out.println("Topic: " + topicId + " does not exist");
      return null;
    }
  }


}
