package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hangman implements IHangman{
	public static String[] dictionary;
	public static String secretWord;
	public static char[] hiddenword;
	public static int leftguesses=5;
	public static boolean gameOn = true;
	
	public static String[] readFile(String file) throws FileNotFoundException {
		Scanner S = new Scanner(new File(file));
		ArrayList<String> listS = new ArrayList<String>();
		while (S.hasNextLine()) {
			listS.add(S.nextLine());
		}
		String[] words = new String[listS.size()];
		for(int i=0; i < listS.size() ; i++) {
			words[i]=listS.get(i);
		}
		return words;
	}
	
	public static void setDictionary(String[] words) {
			dictionary = new String[words.length];
			for(int i =0;i<words.length ; i++) {
				dictionary[i] = words[i];
			}
	}

	public static String selectRandomSecretWord() {
		Random random = new Random();
		return dictionary[random.nextInt(dictionary.length)];
	}
	
	public static String guess(Character c) throws Exception {
		boolean found = false;
		String update;
		for(int i=0 ; i < (secretWord.length()) ; i++) {
			if(c==secretWord.charAt(i) && hiddenword[i]=='-') {
				found = true;
				hiddenword[i]=c;
			}
		}
		update =  String.valueOf(hiddenword);
		if(!found) {
			leftguesses-=1;
		}
		
		if(leftguesses<=0) {
			setMaxWrongGuesses(leftguesses);
			gameOn=false;
			return null;
		}
		else if(update.contentEquals(secretWord)) {
			System.out.println(hiddenword);
			System.out.println("You won \\./");
			gameOn = false;
			return null;
		}
		else {
			setMaxWrongGuesses(leftguesses);
		return update;
		}
	}

	public static void setMaxWrongGuesses(Integer max) {
		if(max>0) {
			System.out.println("You have "+ max+ " false guesses left");
		}
		else {
			gameOn = false;
			System.out.println("You lost :C");
		}
	}
	public static void main(String[] args) throws Exception {
		setDictionary(readFile("dictionary.txt"));
		secretWord = selectRandomSecretWord();
		hiddenword = new char[secretWord.length()];
		for(int i =0 ; i<secretWord.length() ; i++) {
			hiddenword[i]='-';
		}
		Scanner scan = new Scanner(System.in);
		while(gameOn) {
			System.out.println("Enter your guessed character: ");
			System.out.println(hiddenword);
			guess(scan.next().charAt(0));
			
		}
		

		
		
		
		
		
	}
}
