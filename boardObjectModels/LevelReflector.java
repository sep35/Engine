package boardObjectModels;

import java.util.Arrays;
import java.util.List;

import util.Reflection;

public class LevelReflector {

    protected int update(String s, List<Board> list, Board b){
       return (int) Reflection.callMethod(this, s, list, b);
    }
    
    private int add(List<Board> list, Board b){
        Board newBoard = new Board(Arrays.asList(b.getPatchList().size(), b.getPatchList().get(0).size()));
        list.add(newBoard);
        return list.indexOf(newBoard)-list.indexOf(b);
    }
    
    private int remove(List<Board> list, Board b){
        list.remove(b);
        return -1;
    }
    private int forward(List<Board> list, Board b){
        return 1;
    }
    
    private int back(List<Board> list, Board b){
        return -1;
    }
}
