package wordle.application;

import wordle.domain.Referee;
import wordle.domain.Tile;
import wordle.domain.WordBook;
import wordle.ui.InputAndOutput;
import wordle.util.DateCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WordleGame {
    private static final LocalDate STANDARD_DATE = LocalDate.of(2021, 6, 19);
    private static final int MAX_ATTEMPTS = 6;

    private final WordBook wordBook;
    private final InputAndOutput inputAndOutput;

    private final List<Tile[]> history = new ArrayList<>();
    private Referee referee;

    public WordleGame(final WordBook wordBook, final InputAndOutput inputAndOutput) {
        this.wordBook = wordBook;
        this.inputAndOutput = inputAndOutput;
    }

    public void run() {
        initializeGame();
        inputAndOutput.printWelcome();

        int attempt = 0;

        while (attempt < MAX_ATTEMPTS) {
            String guess = askAnswer();

            Tile[] result = referee.checkWordle(guess);
            history.add(result);
            attempt++;

            if (isAllGreen(result)) {
                inputAndOutput.printAttemptCount(attempt, MAX_ATTEMPTS);
                inputAndOutput.printHistory(history);
                return;
            }
            inputAndOutput.printHistory(history);
        }
    }

    private void initializeGame() {
        long daysDiff = DateCalculator.calculate(STANDARD_DATE, LocalDate.now());

        int index = (int) (daysDiff % wordBook.getSize());

        String todayAnswer = wordBook.pick(index);
        System.out.println("Today's Answer: " + todayAnswer);
        this.referee = new Referee(todayAnswer);
    }

    private String askAnswer() {
        while (true) {
            String input = inputAndOutput.askAnswer().toLowerCase();

            if (isValid(input)) {
                return input;
            }
        }
    }

    private boolean isValid(final String input) {
        if (input.length() != 5) {
            inputAndOutput.printInvalidInputMessage("단어는 5글자여야 합니다.");
            return false;
        }
        if (!input.matches("[a-zA-Z]+")) {
            inputAndOutput.printInvalidInputMessage("영문 알파벳만 입력 가능합니다.");
            return false;
        }
        return true;
    }

    private boolean isAllGreen(final Tile[] result) {
        for (Tile tile : result) {
            if (tile != Tile.GREEN) {
                return false;
            }
        }
        return true;
    }
}
