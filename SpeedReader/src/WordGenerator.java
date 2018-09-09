import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class WordGenerator {
	private int wordCount;
	private int sentenceCount;
	private Scanner text;

	
	/*
	 * constructs a new WordGenerator 
	 * 
	 * @param filename - string - the name/directory of an existing text file
	 * 
	 */
	public WordGenerator (String filename) throws IOException  {
		this.text = new Scanner (new File (filename));
		this.wordCount = 0;
		this.sentenceCount = 0;

	}

	/*
	 * checks if there is another word in the text file
	 * 
	 * @return true if there is another word in the text file, false otherwise
	 * 
	 */
	public boolean hasNext(){
		if (text.hasNext())
			return true;
		else
			return false;
	}

	/*
	 * @return the next word from the file 
	 * 
	 * Increments the word counter each time
	 * 
	 * If the word ends with sentence-ending-punctuation i.e. '.', '?', or '!', increments the 
	 * sentence counter to indicate one processed sentence
	 * 
	 */
	public String next() {
		String nextWord = this.text.next();
		char[] wordArr = nextWord.toCharArray();

		this.wordCount++;
		if ((wordArr[wordArr.length - 1] == '.') ||
				(wordArr[wordArr.length - 1] == '!')||
				(wordArr[wordArr.length - 1] == '?')) 
			this.sentenceCount++;
		return (nextWord);
	}

	/*
	 * @return the number of words processed
	 */
	public int getWordCount() {
		return this.wordCount;
	}

	/*
	 * @return the number of sentences processed
	 */
	public int getSentenceCount(){
		return this.sentenceCount;
	}

	
}
