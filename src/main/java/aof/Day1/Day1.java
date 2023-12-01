package aof.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/java/aof/Day1/Input2");
            Scanner myReader = new Scanner(myObj);

            int result = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = normalize(data);
                data = data.replaceAll("[^0-9]+", "");
                if(data.length() > 1)
                    result += Integer.valueOf(data.charAt(0) + "" + data.charAt(data.length() - 1));
                else
                    result+=Integer.valueOf(data+""+data);
            }
            System.out.println(result);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static String normalize(String str){
        Map<CharSequence, CharSequence> values = new HashMap<>();
        values.put("zero", "z0o");
        values.put("one", "o1e");
        values.put("two", "t2o");
        values.put("three", "t3e");
        values.put("four", "f4r");
        values.put("five", "f5e");
        values.put("six", "s6x");
        values.put("seven", "s7n");
        values.put("eight", "e8t");
        values.put("nine", "n9e");

        for(CharSequence cs : values.keySet()){
            if(str.contains(cs)){
                str = str.replace(cs, values.get(cs));
            }
        }
        return str;
    }
}