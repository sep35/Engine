package sound;

/**
 * 
 * @author keng  
 */

import util.GameRuntimeException;

public class SoundException extends GameRuntimeException {

   public SoundException() { 
       super();
   }
   
   public SoundException(String message) {
       super(message);
   }
   
   public SoundException(Throwable throwable) {
       super(throwable);
   }
   
   public SoundException(String message, Throwable throwable) {
       super(message, throwable);
   }
}
