package aof.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    private static ArrayList<ArrayList<Integer>> characterPositions = new ArrayList<>();
    private static ArrayList<Number> numbers = new ArrayList<>();



    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/java/aof/Day3/Input");
        Scanner myReader = new Scanner(myObj);

        buildPositions(myReader);
        result();

        myReader.close();
    }


    public static void buildPositions(Scanner scanner){
        int counterLine = 0;
        int counterColumn = 0;
        int value = 0;
        Integer[] startPosition = new Integer[2];
        char [] charArray;
        boolean sameNumber = false;

        while (scanner.hasNext()){
            charArray = scanner.nextLine().toCharArray().clone();
            for(char c : charArray){

                if((c == '.' && sameNumber)){
                    numbers.add(new Number(startPosition, new Integer[]{counterLine, counterColumn-1},value));
                    sameNumber = false;
                    value = 0;
                }
                else if(Character.isDigit(c)){
                    if(!sameNumber) {
                        startPosition[0] = counterLine;
                        startPosition[1] = counterColumn;
                    }
                    value = value * 10 + Character.getNumericValue(c);
                    sameNumber = true;
                }
                else if(c != '.') {
                    if (sameNumber){
                        numbers.add(new Number(startPosition, new Integer[]{counterLine, counterColumn-1},value));
                        sameNumber = false;
                        value = 0;
                    }
                    characterPositions.add(new ArrayList<>(List.of(counterLine, counterColumn)));
                }

                if(charArray.length-1 == counterColumn && sameNumber){
                    numbers.add(new Number(startPosition, new Integer[]{counterLine, counterColumn},value));
                    sameNumber = false;
                    value = 0;
                }

                counterColumn++;
            }
            counterLine++;
            counterColumn = 0;
        }
    }

    public static void result(){
        int tot = 0;
        int ris = 0;
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
            for (int j = 0; j < characterPositions.size(); j++) {
                ArrayList<Integer> in = new ArrayList<>();
                for (int i = 0; i < numbers.size(); i++) {
                    if(numbers.get(i).checkNearSymbol(characterPositions.get(j))) {
                        in.add(numbers.get(i).getValue());
                    tot += numbers.get(i).getValue();
                    }
                }
                adj.put(j, in);
            }

            for(int x : adj.keySet()){
                if(adj.get(x).size() == 2)
                    ris+=adj.get(x).get(0) * adj.get(x).get(1);
            }

        System.out.println(tot);
        System.out.println(ris);
    }

}
