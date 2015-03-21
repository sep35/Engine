package exception;

/**
 * 
 * @author keng
 *
 */

public class GameException extends Exception {

   public GameException() {
       super();
   }
   
   public GameException(String message) {
       super(message);
   }
   
   public GameException(Throwable throwable) {
       super(throwable);
   }
   
   public GameException(String message, Throwable throwable) {
       super(message, throwable);
   }
    
}
