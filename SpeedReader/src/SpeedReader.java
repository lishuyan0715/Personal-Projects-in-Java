import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;


public class SpeedReader {
	
	/*
	 * Renders a display panel of size width and height and displays words from filename at the speed of wpm in fsize font size
	 * 
	 * @param filename - a file name that exists 
	 * @param width - a positive integer 
	 * @param height - a positive integer
	 * @param fsize - a positive integer
	 * @param wpm - a positive integer
	 * 
	 * @return void - called for side effects
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static void SpeedReader1 (String filename, int width, int height, int fsize, int wpm) throws IOException, InterruptedException {

		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics g = panel.getGraphics();
		Font f = new Font("Courier", Font.BOLD, fsize);
		g.setFont(f);
		g.clearRect(0, 0, width, height);
		g.setColor(Color.WHITE);

		WordGenerator test = new WordGenerator(filename);
		FontMetrics fontSpace = g.getFontMetrics(f);
		
		while(test.hasNext()) {	  
			
			String curr = test.next();
			int currHeight = fontSpace.getHeight();
			int currLength = fontSpace.stringWidth(curr);
			int cWidth = (width/2) - (currLength/2);
			int cLen = (height/2) - (currHeight/2);
			int sLen = curr.length();

			char[] charArr = curr.toCharArray();

			if (sLen <= 2) {
				for (int i = 0; i < sLen; i++) {
					if (i == 0) {
						g.setColor(Color.RED);
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						g.setColor(Color.WHITE);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 

					else {
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 
				}
			} 
			
			/*
			 * checks the length of the character array that represents the word and prints the chars with appropriate coloring
			 */

			else if (sLen <= 5) {
				for (int i = 0; i < sLen; i++) {
					if (i == 1) {
						g.setColor(Color.RED);
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						g.setColor(Color.WHITE);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 

					else {
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 
				}
			} 

			else if (sLen <= 9) {
				for (int i = 0; i < sLen; i++) {
					if (i == 2) {
						g.setColor(Color.RED);
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						g.setColor(Color.WHITE);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 

					else {
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 
				}
			} 

			else if (sLen <= 13) {
				for (int i = 0; i < sLen; i++) {
					if (i == 3) {
						g.setColor(Color.RED);
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						g.setColor(Color.WHITE);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 

					else {
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 
				} 
			} 

			else {
				for (int i = 0; i < sLen; i++) {
					if (i == 4) {
						g.setColor(Color.RED);
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						g.setColor(Color.WHITE);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 

					else {
						g.drawString(Character.toString(charArr[i]), cWidth, cLen);
						cWidth += fontSpace.charWidth(charArr[i]);
					} 
				}
			}
      
			Thread.sleep( (long) (((double) 60 / (double) wpm) * 1000.00));          
			g.clearRect(0, 0, width, height);
		}
		
		String totalWords = "Words Processed: " + test.getWordCount();
		String totalSent = "Sentences Processed: " + test.getSentenceCount();
		
		g.drawString(totalWords, (width/2)-(fontSpace.stringWidth(totalWords)/2), (height/2)-fontSpace.getHeight());
		g.drawString(totalSent, (width/2)-(fontSpace.stringWidth(totalSent)/2), (height/2)+fontSpace.getHeight());
	}

	
	/*
	 * checks if a given int is positive 
	 * 
	 * @param x - an int 
	 * 
	 * @return true if x is positive, false otherwise
	 * 
	 */
	public static boolean check (int x) {
		if (x <= 0) { return false; }
		else { return true; }
	}
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		if (args.length != 5) {	
			System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			System.exit(0);
		}

		int width = Integer.parseInt(args[1]);
		int heigth = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);

		File temp = new File (args[0]);
		if (!temp.exists()) {
			System.out.println("The file does not exist");
		}

		if (!(check(width) && 
				check(heigth) && 
				check(fontSize) && 
				check(wpm))) {
			System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			System.exit(0);
		}

		SpeedReader1(args[0],width, heigth, fontSize, wpm);
	}

}
