package Analizer;

import java.util.*;

/**
 * Created by marcin on 5/16/15.
 */
public class Ngrams {
    private static int mark = 2; //domyslna wartość stopnia ngramu 2
    private HashMap<String, Ngram> ngrams; //zawiera prefixy jako klucze i Ngramy jako wartości
    private static Ngrams ngramsContainer = null;
    private int numberOfNgrams;
    private Ngrams(){
        ngrams = new HashMap<>();
        numberOfNgrams = 0;
    }
    public static Ngrams getInstance(){
        if( ngramsContainer == null){
            ngramsContainer = new Ngrams();
        }
        return ngramsContainer;
    }
    public HashMap<String, Ngram> getMap(){
        return this.ngrams;
    }

    public static void processText(ArrayList<String> text){
        while(getInstance().numberOfNgrams < text.size() - getMark()) {
            addNgram(getInstance().numberOfNgrams, text);
            getInstance().numberOfNgrams++;
        }
    }
    public static int getMark(){
        return getInstance().mark;
    }
    public static void setMark(int m){
        getInstance().mark = m;
    }
    public static void addNgram(int index, ArrayList<String> text){
        //prefix
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < getMark(); i++){
            prefix.append(text.get(index + i));
            prefix.append(" ");
        }
        //sufix
        StringBuilder sufix = new StringBuilder();
        sufix.append(text.get(index + getMark()));
        //logika
        Ngram tmp;
        if ((tmp = findNgram(prefix.toString())) != null) {
            tmp.addSufix(sufix.toString());
        } else {
            tmp = new Ngram(prefix.toString(), sufix.toString());
            getInstance().getMap().put(prefix.toString(), tmp);
        }

    }
    public static Ngram getRandomNgram(){
        String[] pref = null;
        Object[] prefixes = getInstance().getMap().keySet().toArray();
        Random generator = new Random();
        String prefix = ((String) prefixes[generator.nextInt(prefixes.length)]);
        return getInstance().getMap().get(prefix);
    }
    public static Ngram findNgram(String prefix){
        return getInstance().getMap().get(prefix);
    }

}
