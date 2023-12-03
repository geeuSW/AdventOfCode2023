package aof.Day3;

import java.util.ArrayList;

public class Number{
    private Integer[] start;
    private Integer[] finish;
    private int value;

    public Number(Integer[] start, Integer[] finish, int value){
        this.start = start.clone();
        this.finish = finish.clone();
        this.value = value;
    }
    public int getValue(){
        return value;
    }

    public boolean checkNearSymbol(ArrayList<Integer> symbolPosition){
        if((symbolPosition.get(1) >= start[1]-1 && symbolPosition.get(1) <= finish[1]+1) && (symbolPosition.get(0) >= start[0]-1 && symbolPosition.get(0) <= finish[0]+1)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return ""+this.value;
    }
}
