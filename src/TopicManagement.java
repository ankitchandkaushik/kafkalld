import exception.TopicNotFoundException;

public class TopicManagement {
  Storage storage;

  public TopicManagement() {
    storage = Storage.getInstance();
  }

  public void register(Topic topic) {
    storage.addTopic(topic);
  }

  public void addConsumer(Topic topic, Consumer consumer) {
    if(storage.topics.containsKey(topic.getId())) {
      storage.topics.get(topic.getId()).getConsumerOffset().put(consumer.consumerGroupId, 0);
    } else {
      throw new TopicNotFoundException(topic.getName());
    }
  }
}
