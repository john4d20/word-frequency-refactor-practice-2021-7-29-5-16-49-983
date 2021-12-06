import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";

    public String getResult(String inputStr){

        if (inputStr.split(SPACE_PATTERN).length==1) {
            return inputStr + " 1";
        } else {

            try {

                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);

                wordInfoList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                return getString(wordInfoList);
            } catch (Exception e) {


                return "Calculate Error";
            }
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


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }

            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
