package exception;

public class ConsumerNotAuthorized extends RuntimeException{
  public ConsumerNotAuthorized(String message, String consumer) {
    super("Consumer " + consumer + " not authorisd for topic " + message);
  }
}
