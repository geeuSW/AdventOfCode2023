package aof.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        try{
            File myObj = new File("src/main/java/aof/Day2/Input2");
            Scanner myReader = new Scanner(myObj);
            Map<Integer, Map<String, Integer>> map = new HashMap<>();

            int count = 1;
            while (myReader.hasNextLine()) {
                map.put(count, buildMap(myReader.nextLine()));
                count++;
            }
            //First question
            checkResultFirst(map, "12 red, 13 green, 14 blue");
            //Second question
            checkResultSecond(map, "red, green, blue");

            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void checkResultSecond(Map<Integer, Map<String, Integer>> map, String string) {
        int tot = 0;
        String [] str = cleanInput(string);
        for(int i = 1; i <= map.size(); i++){
            int ltot = map.get(i).get(str[0]);
            for(int j = 1; j < str.length; j++){
                ltot *= map.get(i).get(str[j]);
            }
            tot+=ltot;
        }
        System.out.println(tot);
    }
    public static void checkResultFirst(Map<Integer, Map<String, Integer>> map, String combination){
        String[] str = cleanInput(combination);
        int count = 0;
        for(int j = 1; j <= map.size(); j++){
            boolean check = true;
            for (int i = 0; i < str.length-1; i+=2) {
                if(map.get(j).get(str[i+1]) > Integer.valueOf(str[i])){
                    check = false;
                    i = str.length;
                }
            }
            if(check) {
                count += j;
            }
        }
        System.out.println(count);
    }

    public static String[] cleanInput(String str){
        return str.replace(":" , "").replace(";" , "").replace("," , "").split(" ");
    }
    public static Map<String, Integer> buildMap(String str){

        String [] split = cleanInput(str);

        Map<String, Integer> insmap = new HashMap<>();
        for(int i = 3; i < split.length; i+=2){
            if(!insmap.containsKey(split[i])) {
                insmap.put(split[i], Integer.valueOf(split[i - 1]));
            }else {
                if (insmap.get(split[i]) < Integer.valueOf(split[i - 1]))
                    insmap.replace(split[i], Integer.valueOf(split[i - 1]));
            }
        }
        return insmap;
    }

}
