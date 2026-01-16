package wordle;

import wordle.application.WordleGame;
import wordle.domain.WordBook;
import wordle.ui.InputAndOutput;
import wordle.util.FileReader;

public class Application {

    public static void main(String[] args) {
        // 게임 준비 -> 객체 생성
        FileReader fileReader = new FileReader();
        WordBook wordBook = new WordBook(fileReader);

        // 입력, 출력 -> 객체 생성
        InputAndOutput inputAndOutput = new InputAndOutput();

        // 게임 시작 wordle.start()
        WordleGame wordleGame = new WordleGame(wordBook, inputAndOutput);
        wordleGame.run();
    }
}
