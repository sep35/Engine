package sound;

/**
 * 
 * @author keng
 *
 */

public interface ISound {
    
    public void play();
    public void pause();
    public void resume();
    public void stop();
    
    public float getVolume();
    public void setVolume(float Volume);
    
    public float getLeftVolume();
    public float getRightVolume();
    public void setGlobalVolume(float leftVolume, float rightVolume);
    
    public void isLooping(boolean looping);
    
    public void onVolumeChange(float masterVolume);

    public void release();
}
