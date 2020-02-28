package eg.edu.alexu.csd.datastructure.hangman.cs22;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class HangmanTest {

	@Test
	void test() throws Exception {
		Hangman hangman = new Hangman();
		hangman.setDictionary(hangman.readFile("dictionary.txt"));
		hangman.secretWord="tree";
		hangman.hiddenword="----".toCharArray();
		hangman.setMaxWrongGuesses(5);
		assertEquals(hangman.guess('e'),"--ee");
		assertEquals(hangman.guess('t'),"t-ee");
		assertEquals(hangman.guess('g'),"t-ee");
		assertEquals(hangman.guess('r'),null);
	}

}
