package wordle.domain;

import wordle.util.FileReader;

import java.util.List;

public class WordBook {

    private final List<Word> words;

    public WordBook(final FileReader reader) {
        this.words = reader.readByLine("words.txt")
                .stream()
                .map(Word::new)
                .toList();
    }

    // NOTE: 정답 단어 가져오기
    public String pick(final int index) {
        if (index < 0 || index >= words.size()) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return words.get(index).getValue();
    }

    public int getSize() {
        return words.size();
    }

    public boolean contains(final String inputWord) {
        return words.stream()
                .anyMatch(word -> word.getValue().equals(inputWord));
    }
}
