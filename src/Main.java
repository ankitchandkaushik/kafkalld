import java.util.Random;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello world!");

    TopicManagement management = new TopicManagement();

    Topic topic = new Topic("topic1");
    management.register(topic);

    Producer producer1 = new Producer();
    Producer producer2 = new Producer();
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

    Runnable producerRunnable1 = new Runnable(){
      public void run() {
        Random rand = new Random();
        for(int i = 0; i<100; ++i) {
          producer1.send(topic.getId(), new Message("This is message: " +i + " from producer1") );
//          try {
////            Thread.sleep(rand.nextInt(1000));
//          } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//          }
        }
      }
    };

    Thread consumerThread = new Thread(consumerRunnable);
    Thread producerThread1 = new Thread(producerRunnable1);
    Thread producerThread2 = new Thread(producerRunnable1);
    Thread producerThread3 = new Thread(producerRunnable1);
    Thread producerThread4 = new Thread(producerRunnable1);
    Thread producerThread5 = new Thread(producerRunnable1);
    Thread producerThread6 = new Thread(producerRunnable1);





    producerThread1.start();
    producerThread2.start();
    producerThread3.start();
    consumerThread.start();
    producerThread4.start();
    producerThread5.start();
    producerThread6.start();


    Random rand = new Random();
    for(int i = 0; i<100; ++i) {
      producer2.send(topic.getId(), new Message("This is message: " +i + " from producer2") );
//      Thread.sleep(rand.nextInt(1000));
    }
    consumerThread.join(100000);
  }
}
