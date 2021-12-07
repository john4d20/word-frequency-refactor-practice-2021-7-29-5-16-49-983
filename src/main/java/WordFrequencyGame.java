import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";

    public String getWordFrequencyResult(String inputStr){
        try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);
                wordInfoList.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());
                return getWordFrequencyString(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
    }

    private String getWordFrequencyString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");

        wordInfoList.forEach(wordInfo -> joiner.add(String.format("%s %s",wordInfo.getValue(),wordInfo.getWordCount())));

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
