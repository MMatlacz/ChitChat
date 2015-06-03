package Analizer;

import GUI.Interface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcin on 5/15/15.
 */
public class Words {
    private ArrayList<String> words;
    private static Words instance;

    private Words() {
        words = new ArrayList<>();
    }

    public static Words getInstance() {
        if (instance == null) {
            instance = new Words();
        }
        return instance;
    }

    public static void initializeDictionary(File fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if(!line.equals("")){
                    stringBuilder.append(line + " ");
                }
            }
            String[] str = stringBuilder.toString().split("\\s+");
            for (String s : str) {
                s = s.trim();
                getInstance().words.add(s);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd odczytu pliku: " + e);
        }
        Ngrams.processText(getInstance().words);
        getInstance().words.clear();
    }

    public static Integer addText(String string) {
        string = string.trim();
        String[] str = string.split(" ");
        Integer index = getInstance().words.size();
        for(String s : str) {
            if(s.equals(" ") || s.equals("")){
                continue;
            }
                getInstance().words.add(s.trim());
        }
        return index;
    }
    public static ArrayList<String> getText() {
        return getInstance().words;
    }

}
