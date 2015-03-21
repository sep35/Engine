package sound;

import java.util.ArrayList;

/**
 * 
 * @author keng
 *
 */

public abstract class BasicSoundManager<T extends ISound> implements ISoundManager<T> {
    
    protected ArrayList<T> mySoundInstances = new ArrayList<T>();
    
    protected float myMasterVolume = 1.0f;
    
    /*
     * Methods
     */
    
    public void add(T soundInstance) {
        this.mySoundInstances.add(soundInstance);
    }

    public boolean remove(T soundInstance) {
        return this.mySoundInstances.remove(soundInstance);
    }
    
    public float getMasterVolume() {
        return this.myMasterVolume;
    }
    
    public void setMasterVolume(float masterVolume) {
        this.myMasterVolume = masterVolume;
        ArrayList<T> soundInstances = this.mySoundInstances;
        for (T soundInstance : soundInstances) {
           soundInstance.onVolumeChange(masterVolume); 
        }
    }
    
    public void releaseAll() {
        ArrayList<T> soundInstances = this.mySoundInstances;
        for (T soundInstance : soundInstances) {
            soundInstance.stop();
            soundInstance.release();
        }
    }
    
}
