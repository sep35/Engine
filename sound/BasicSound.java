package sound;


/**
 * 
 * @author keng
 *
 */

public abstract class BasicSound implements ISound {

   private ISoundManager<? extends ISound> mySoundManager; 
   
   protected float myLeftVolume = 1.0f; 
   protected float myRightVolume = 1.0f; 
   
   private boolean myReleased;
   
   /**
    * Constructor for basic sound instance
    * @param soundManager
    */
   public BasicSound(ISoundManager<? extends BasicSound> soundManager) {
       this.mySoundManager = soundManager;
   }
   
   /**
    * Getters and setters to enquire about the state of the basic sound instance 
    */
   public boolean isReleased() { 
       return this.myReleased;
   }
   
   protected ISoundManager<? extends ISound> getSoundManager() throws SoundException {
       this.setNotRelease();
       return this.mySoundManager;
   }
   
   public float getRealLeftVolume() throws SoundException {
       this.setNotRelease();
       return this.myLeftVolume * this.getVolume();
   }
   
   public float getRealRightVolume() throws SoundException {
       this.setNotRelease();
       return this.myRightVolume * this.getVolume();
   }
   
   public float getMasterVolume() throws SoundException {
       this.setNotRelease();
       return this.mySoundManager.getVolume();
   }

   /**
    * Methods
    */
   protected abstract void throwOnRelease() throws SoundException;

   public float getVolume() throws SoundException {
       this.setNotRelease();
       return (this.myLeftVolume + this.myRightVolume) * 0.5f;
   }
   
   public float getLeftVolume() throws SoundException {
       this.setNotRelease();
       return this.myLeftVolume;
   }
   
   public float getRightVolume() throws SoundException {
       this.setNotRelease();
       return this.myRightVolume;
   }
   
   public void setVolume(float volume) throws SoundException {
       this.setNotRelease();
       this.setVolume(volume);
   }
   
   public void setVolume(float leftVolume, float rightVolume) throws SoundException {
       this.setNotRelease();
       this.myLeftVolume = leftVolume;
       this.myRightVolume = rightVolume;
   }
   
   public void onMasterVolumeChange(float masterVolume) throws SoundException {
       this.setNotRelease();
   }
   
   public void play() throws SoundException {
       this.setNotRelease();
   }
   
   public void pause() throws SoundException {
       this.setNotRelease();
   }

   public void resume() throws SoundException {
       this.setNotRelease();
   }

   public void stop() throws SoundException {
       this.setNotRelease();
   }
   
   public void setLooping() throws SoundException {
       this.setNotRelease();
   }
   
   public void release() throws SoundException {
       this.setNotRelease();
       this.myReleased = true;
   }
   
   public void setNotRelease() throws SoundException {
       if(this.myReleased) this.throwOnRelease();
   }
}
