package Analizer;

import java.util.*;

/**
 * Created by marcin on 5/16/15.
 */
public class Ngrams {
    private static int mark = 2; //domyslna wartość stopnia ngramu 2
    private int processedIndex; //index ostatniego przetworzonego wyrazu
    private HashMap<Integer, Ngram> ngrams; //zawiera referencje do ngramów jako klucze i liczbę wystąpień jako wartości
    private static Ngrams ngramsContainer = null;
    private int numberOfNgrams;
    private Ngrams(){
        ngrams = new HashMap<>();
        processedIndex = 0;
        numberOfNgrams = 0;
    }
    public static Ngrams getInstance(){
        if( ngramsContainer == null){
            ngramsContainer = new Ngrams();
        }
        return ngramsContainer;
    }
    public HashMap<Integer, Ngram> getMap(){
        return this.ngrams;
    }

    public static void processText(ArrayList<String> text){
        Words instance = Words.getInstance();
        for(int i = getInstance().processedIndex; i < text.size() - getMark(); i++){
            getInstance().addNgram(i);
            getInstance().processedIndex++;
        }
    }
    public static int getMark(){
        return mark;
    }
    public static void setMark(int m){
        mark = m;
    }
    public static void addNgram(int index){
        Ngram temp = new Ngram(index);
        if( (getInstance().ngrams.get(index)) == null ){
            getInstance().ngrams.put(index, new Ngram(index));
            getInstance().numberOfNgrams++;
        }

    }
    public static Ngram findNgram(Integer index){
        return getInstance().getMap().get(index);
    }

}
