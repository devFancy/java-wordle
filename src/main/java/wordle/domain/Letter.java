package wordle.domain;

public class Letter {

    // NOTE: 알파벳, 위치
    private final char alphabet; // a ~ z
    private final int position; // 0 ~ ...

    public Letter(final char alphabet, final int position) {
        this.alphabet = alphabet;
        this.position = position;
    }
}
