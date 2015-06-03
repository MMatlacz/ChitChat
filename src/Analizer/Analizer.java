package Analizer;

import javafx.scene.chart.XYChart;

import java.util.Map;

/**
 * Created by marcin on 5/23/15.
 */
public class Analizer {
    private static XYChart.Series series1 = new XYChart.Series();

    public static void setMark(int value){
        Ngrams.setMark(value);
    }
    public static String generateAnswer(String question) {
        //Generacja odpowiedzi
        //przetworzenie textu
        //dodanie do słownika
        Integer index = Words.addText(question);
        //dodanie do ngramów
        Ngrams.processText(Words.getText());
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = question.split(" ");
        int i = 0;
        if (Ngrams.getInstance().getMap().size() < 1) {
            return "Nie wiem co odpowiedzieć";
        }
        for (String w : words) {
            if (i == Ngrams.getMark()) {
                break;
            }
            stringBuilder.append(w);
            stringBuilder.append(" ");
            i++;
        }

        if (i < Ngrams.getMark()) {
            Ngram tmp = Ngrams.getRandomNgram();
            for (String str : tmp.getPrefix()) {
                stringBuilder.append(str);
                stringBuilder.append(" ");
            }
            stringBuilder.append(tmp.getRandomSufix());
        } else {
            String[] pr = stringBuilder.toString().split(" ");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < Ngrams.getMark(); j++) {
                sb.append(pr[pr.length - j - 1]);
                sb.append(" ");
            }
            Ngram tmp = Ngrams.findNgram(sb.toString());
            if (tmp != null) {
                for (String str : tmp.getPrefix()) {
                    stringBuilder.append(str);
                    stringBuilder.append(" ");
                }
                stringBuilder.append(tmp.getRandomSufix());
            } else {
                tmp = Ngrams.getRandomNgram();
                for (String str : tmp.getPrefix()) {
                    stringBuilder.append(str);
                    stringBuilder.append(" ");
                }
                stringBuilder.append(tmp.getRandomSufix());
            }
        }

        words = null;


        Words.addText(stringBuilder.toString());
        Ngrams.processText(Words.getText());
        return stringBuilder.toString();
    }

    public static XYChart.Series setStats() {
        //Wykres statystyk
        series1 = new XYChart.Series();
        //System.out.println(stringBuilder.toString());
        int i = 0;
        for (Map.Entry<String, Integer> en : Statistics.mostCommonWordds()) {
            if (i < 10) {
                series1.getData().add(new XYChart.Data(en.getValue(), en.getKey()));
            }
            i++;
        }
        return series1;
    }
}
