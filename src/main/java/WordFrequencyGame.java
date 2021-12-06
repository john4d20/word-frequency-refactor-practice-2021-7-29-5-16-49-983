import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";

    public String getResult(String inputStr){
        try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);
                wordInfoList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
                return getString(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
    }

    private String getString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String s = wordInfo.getValue() + " " +wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }


    private List<WordInfo> calculateWordFrequency(String sentences){
        List<String> words = Arrays.asList(sentences.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());


        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord ->{
            int frequency = (int) words.stream().filter(word -> word.equals((distinctWord))).count();
            WordInfo wordInfo = new WordInfo(distinctWord, frequency);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }


}
