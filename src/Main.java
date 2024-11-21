import java.util.Random;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello world!");

    TopicManagement management = new TopicManagement();

    Topic topic = new Topic("topic1");
    management.register(topic);

    Producer producer = new Producer();
    Consumer consumer = new Consumer("consumer1");

    management.addConsumer(topic, consumer);


    Runnable consumerRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          consumer.subscribe(topic.getId());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };

    Thread consumerThread = new Thread(consumerRunnable);

    consumerThread.start();
    Random rand = new Random();
    for(int i = 0; i<100; ++i) {
      producer.send(topic.getId(), new Message("This is message: " +i) );
      Thread.sleep(rand.nextInt(1000));
    }
    consumerThread.join(100000);
  }
}
