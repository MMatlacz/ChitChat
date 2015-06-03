package Analizer;

import java.util.*;

/**
 * Created by marcin on 6/3/15.
 */
public class Statistics {
    public static SortedSet<Map.Entry<String,Integer>> mostCommonWordds(){

        Words.getText().sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String str: Words.getText()){
            //System.out.println(str);
        }
        TreeMap<String, Integer> tenMostCommon = new TreeMap<>();
        int i = 0;
        String str = null;
        String nextString = null;
        int wordCount = 0;
        while( true ){
            str = Words.getText().get(i);
            wordCount++;
            if(i + 1 < Words.getText().size()){
                nextString = Words.getText().get(i + 1);
            } else {
                tenMostCommon.put(str, wordCount);
                break;
            }
            if( str.equals(nextString)){
                i++;
            } else {
                tenMostCommon.put(str, wordCount);
                wordCount = 0;
                i++;
            }

        }
        Comparator<Map.Entry<String,Integer>> cmp = new Comparator<Map.Entry<String,Integer>>() {
            @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                return res != 0 ? res : 1;
            }
        };
        SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(cmp.reversed());
        sortedEntries.addAll(tenMostCommon.entrySet());

        for(Map.Entry<String,Integer> en: sortedEntries){
            System.out.println(en.getKey() + " " + en.getValue());
        }
        System.out.println(" ");

        return sortedEntries;
    }
}
