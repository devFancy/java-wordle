package wordle.domain;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private static final int WORD_LENGTH = 5;

    private final List<Letter> letters;

    public Word(final String input) {
        if (input.length() != WORD_LENGTH) {
            throw new IllegalArgumentException();
        }

        this.letters = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            final Letter letter = new Letter(input.charAt(i), i);
            this.letters.add(letter);
        }
    }
}
