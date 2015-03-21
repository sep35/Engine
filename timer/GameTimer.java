package timer;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Timer utility class, allows pause and resume. 
 * Used as basic or countdown timer.
 * @author Keng
 */

public abstract class GameTimer {
    public static int FOREVER = -1;
    private boolean running = false;
    private long interval;
    private long elapsed;  
    private long duration;
    private ScheduledExecutorService execService = Executors
            .newSingleThreadScheduledExecutor();
    private Future<?> future = null;
    
    /**
     * Default constructor for Timer 
     * with an interval of 1000ms and an indefinite duration.
     */
    public GameTimer() {
        this(1000, -1);
    }
    
    /**
     * @param interval
     *          The duration between each tick in millis.
     * @param duration
     *          The period for which the timer should run in millis.
     */
    
    public GameTimer(long interval, long duration) {
        this.interval = interval;
        this.duration = duration;
        this.elapsed = 0;
    }
    
    /**
     * Starts the timer.
     * The call is ignored if the timer is already running.
     */
    public void start() {
        if(running) return;
        running = true;
        future = execService.scheduleWithFixedDelay(
                 new Runnable() {
                     public void run() {
                         onInit();
                         elapsed += GameTimer.this.interval;
                         if(duration > 0 &&
                                 elapsed >= duration) {
                             onEnd();
                             future.cancel(false);
                         }
                     }
                 }, 0, this.interval, TimeUnit.MILLISECONDS);
        } 
    
    /**
     * Stop timer.
     * The call is ignored if the timer is not running.
     */
    public void cancel() {
        pause();
        this.elapsed = 0;
    }
    
    /** 
    * Pause the timer.
    * The call is ignored if the timer is not running. 
    */
    public void pause() {
        if(!running) return;
        future.cancel(false);
        running = true;
    }
    
    /** 
    * Resumes the timer if it was paused.
    * If not, start a new timer.
    */
    public void resume() {
        this.start();
    }

    /**
     * @return true if the timer is running,
     * false otherwise 
     */
    public boolean isRunning() {
        return running;
    }
    
    /**
     * @return the elapsed time (in millis)
     */
    public long getElapsed() {
        return this.elapsed;
    }
    
    /**
     * @return remaining time (in millis)
     */
    public long getRemaining() {
        if(this.duration < 0) {
            return GameTimer.FOREVER;
        } else {
            return duration - elapsed;
        }
    }
    
    /** 
    * This method is called with the interval defined 
    * as the delay between calls.
    */
    protected abstract void onInit();

    /**
     * This method is called once the timer has run for the set duration
     * If the duration was set to infinity, then it is never called.
     */
    protected abstract void onEnd();
}