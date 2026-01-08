package wordle.domain;

import wordle.util.FileReader;

import java.util.List;

public class WordBook {

    private final List<Word> words;

    public WordBook(final FileReader reader) {
        this.words = reader.readByLine("word.txt")
                .stream()
                .map(Word::new)
                .toList();
    }
}
