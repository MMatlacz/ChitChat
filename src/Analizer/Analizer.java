package Analizer;

/**
 * Created by marcin on 5/23/15.
 */
public class Analizer {
    public static String generateAnswer(String question){
        Integer index = Words.addText(question);
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = question.split(" ");
        for(int i = 0; i < Ngrams.getMark() && i < words.length - 1; i++) {
            stringBuilder.append(words[i]);
            stringBuilder.append(" ");
        }
        words = null;
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
    private static String getRandomSufix(Integer prefix){
        Ngram tmp = Ngrams.findNgram(prefix);
        return null;
    }
}
