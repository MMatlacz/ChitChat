package Analizer;

import java.util.*;

/**
 * Created by marcin on 5/16/15.
 */
public class Ngram {
    private ArrayList<String> prefix;
    private ArrayList<String> sufixes;

    public Ngram(String prefix, String sufix) {
        this.prefix = new ArrayList<>();
        this.sufixes = new ArrayList<>();
        this.addPrefix(prefix);
        this.addSufix(sufix);
    }

    public void addPrefix(String prefix) {
        for (String str: prefix.split(" ")) {
            this.prefix.add(str);
        }
    }

    public void addSufix(String sufix) {
        this.sufixes.add(sufix);
    }

    public ArrayList<String> getPrefix(){
        return prefix;
    }

    public ArrayList<String> getSufixes(){
        return sufixes;
    }

    public String getRandomSufix(){
        Random generator = new Random();
        return this.sufixes.get(generator.nextInt(sufixes.size()));
    }

}