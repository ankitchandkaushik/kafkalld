import java.util.List;

public class Consumer {
  Storage storage;
  String consumerGroupId;

  public Consumer(String consumerGroupId) {
    this.consumerGroupId = consumerGroupId;
    storage = Storage.getInstance();
  }

//  public void poll() {
//    for(String topicId: topics) {
//      System.out.println(storage.poll(id, topicId, 1));
//    }
//
//  }

  public void subscribe(String topicName) throws InterruptedException {
    while(true) {
      List<Message> polledMessage = storage.poll(consumerGroupId, topicName);
      polledMessage.stream().forEach(m-> System.out.println("Processed : " + m.toString()));
      Thread.sleep(1000);
    }
  }
}
