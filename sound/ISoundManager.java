package sound;

/**
 * 
 * @author keng
 *
 */

public interface ISoundManager<T extends ISound> {
    
    public float getVolume();
    public void setVolume();
    
    public void add(T sound);
    public boolean remove(T sound);

    public void releaseAll();
}
