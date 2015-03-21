package inputs;

public abstract class GameInfo {
    public String getType(){
        return this.getClass().getSimpleName();
    }
    @Override
    public abstract boolean equals(Object o);
}
