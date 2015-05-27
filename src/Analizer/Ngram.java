package Analizer;

import java.util.*;

/**
 * Created by marcin on 5/16/15.
 */
public class Ngram implements Comparable<Ngram> {
    private ArrayList<Integer> prefix;
    private ArrayList<Integer> sufixes;

    public Ngram(int index) {
        this.prefix = new ArrayList<>();
        this.sufixes = new ArrayList<>();
        this.addPrefix(index);
        this.addSufix(index);
    }

    public void addPrefix(int index) {
        for (int i = index; i < index + Ngrams.getMark(); i++) {
            this.prefix.add(i);
        }
    }

    public void addSufix(int index) {
        this.sufixes.add(index + Ngrams.getMark());
    }

    public ArrayList<String> getPrefix(){
        ArrayList<String> prefix = new ArrayList<>();
        for(Integer i: this.prefix){
            prefix.add(getString(i));
        }
        return prefix;
    }

    public ArrayList<String> getSufixes(){
        ArrayList<String> sufixes = new ArrayList<>();
        for(Integer i: this.sufixes){
            sufixes.add(getString(i));
        }
        return sufixes;
    }

    private String getString(Integer index) {
        return Words.getInstance().getText().get(index);
    }

    @Override
    public int compareTo(Ngram o) {
        Ngram n1 = o;
        int comp = 0;
        for (int i: n1.prefix) {
            if ((comp = getString(n1.prefix.get(i)).compareTo(getString(this.prefix.get(i)))) != 0) {
                return comp;
            }
        }
        return comp;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i: this.prefix){
            sb.append(getString(i));
        }
        sb.append("\n");
        for(int i: this.sufixes){
            sb.append(getString(i));
        }
        return sb.toString();
    }

}