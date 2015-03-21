package gameObjectModel;

import java.util.List;

public class CustomRange extends AbstractRange{
    private List<List<Integer>> myDeltas;
    public CustomRange(List<List<Integer>> deltas, boolean b, String name){
        super(name, b);
        myDeltas = deltas;
    }

    @Override
    public List<List<Integer>> getDeltas () {
        return myDeltas;
    }

}
