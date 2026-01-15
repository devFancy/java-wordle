package wordle.domain;

import java.util.HashMap;
import java.util.Map;

public class Referee {
    private final String targetWord;
    private final Map<Character, Integer> targetWordMap = new HashMap<>();

    public Referee(final String targetWord) {
        if (targetWord.length() != 5) {
            throw new IllegalArgumentException();
        }

        this.targetWord = targetWord;

        for (char targetWordChar : targetWord.toCharArray()) {
            targetWordMap.put(targetWordChar, targetWordMap.getOrDefault(targetWordChar, 0) + 1);
        }
    }

    public Tile[] checkWordle(final String guessWord) {
        Tile[] result = new Tile[targetWord.length()];
        HashMap<Character, Integer> remainedTargetWordMap = new HashMap<>(targetWordMap);

        // NOTE: GREEN(문자, 위치가 같은지) 확인
        for (int i = 0; i < guessWord.length(); i++) {
            char targetWordChar = targetWord.charAt(i);

            if (targetWordChar == guessWord.charAt(i)) {
                result[i] = Tile.GREEN;
                remainedTargetWordMap.put(targetWordChar, targetWordMap.get(targetWordChar) - 1);
            }
        }

        // NOTE: YELLOW OR GRAY 인지 확인
        for (int i = 0; i < guessWord.length(); i++) {
            if (result[i] == Tile.GREEN) {
                continue;
            }

            char guessChar = guessWord.charAt(i);

            if (remainedTargetWordMap.getOrDefault(guessChar, 0) > 0) {
                result[i] = Tile.YELLOW;
                remainedTargetWordMap.put(guessChar, remainedTargetWordMap.get(guessChar) - 1);
            } else {
                result[i] = Tile.GRAY;
            }
        }

        return result;
    }
}
