import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Maximilian Kronborg
 * @date 03.05.18
 * @version 1.1
 */
public class ReadFile 
{
	private String[] words;
	private String[] lines;
	
	/**
	 * Returns true if first word is alphabetically after the second
	 * @param first
	 * 			First word to compare
	 * @param second
	 * 			Second word to compare
	 * @return
	 * 			Boolean indicating if the first word is lexicographically after the second
	 */
	public boolean compare(String first, String second)
	{
		int i = 0; // index of char to be inspected
		while (i < first.length() && i < second.length())
		{
			if (Character.getNumericValue(first.charAt(i)) > Character.getNumericValue(second.charAt(i)))
			{
				return true; // If the current char in the first word has a higher ascii value than the same index in the other word, then they should be swapped
			}
			else if (Character.getNumericValue(first.charAt(i)) < Character.getNumericValue(second.charAt(i)))
			{
				return false; // If the current char in the first word has a lower ascii value than the same index in the other word, then they should not be swapped
			}
			i++;
		}
		if (second.length() < first.length()) // If they are identical up to the end of the second word, and the first is longer, then they should
			return true;
		return false; // If process makes it through all the  other conditions, then they are exactly identical, or word one is shorter than word to, but identical to that point
	}

	/**
	 * Accessor for name array
	 * @return
	 */
	public String[] getWords()
	{
		return words;
	}

	/**
	 * Method to print out array of words
	 * @param outputText
	 * 			Array of words to print
	 * @param output
	 * 			name of file to output
	 */
	public void outputWords(String[]outputText, String output)
	{
		try
		{
			File file = new File(output);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < outputText.length; i++)
			{
				writer.append(outputText[i]); // Writes word by word
			}
			writer.close();
		}
		catch (IOException e)
		{
			System.err.print("File " + output + " not found, please attempt again");
			System.exit(1);
		}
	}

	/**
	 * Returns true if a given character is a letter
	 * @param c
	 * @return
	 */
	public boolean isAlpha(int c)
	{
		if  ((c < 91 && c > 64) || (c < 123 && c > 96)) // Could be done with chars instead of their ascii values. Ensures c is in the correct range
			return true;
		return false;
	}

	/**
	 * Resizes a given array to a specified size
	 * @param change
	 * 			array to resize
	 * @param size
	 * 			Size of new array
	 * @return
	 * 			array of requested size
	 */
	public String[] reSize(String[] change, int size)
	{
		String[] newer = new String[size];
		for(int i = 0; i < change.length && i < newer.length; i++) // Stops if length of old or new array is reached
		{
			newer[i] = change[i];
		}
		return newer;
	}

	/**
	 * Reads a specific file word by word and stores them in a String array
	 * @param inputFile
	 * 			name of file to read from
	 */
	public void readWords(String inputFile)
	{
		try {
            File file = new File(inputFile); // Attempts to find file
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String word;

            words = new String[5163]; // default array size, is resized if needed
    		try
    		{
    			int c; // Integer value of character read
    			int count = 0; // index of word array
    			StringBuilder n = new StringBuilder();

    			while ((c = reader.read()) != -1) // Reads until terminating character is reached
    			{
    				if(isAlpha(c)) // If char is a letter then it is added to current word
    				{
    					n.append((char)c);
    				}
    				else
    				{
						if (count == words.length)
							words = reSize(words, words.length * 2);
    					if (n.length() != 0)
    					{
							word = n.toString();
							words[count++] = word;
							n = new StringBuilder();
						}
    					
    					n.append((char)c);
    					
    					word = n.toString();
    					
    					if (count == words.length)
    						words = reSize(words, words.length * 2);
    					
    					words[count++] = word;
    					
    					n = new StringBuilder();
    				}
    			}
    			words = reSize(words, count-1); // Trims the size of words
    		}
    		catch(IOException ioe)
    		{
    			System.err.println("IO Exception");
                System.exit(0);
    		}
	 }
     catch (IOException ioe)
     {
    	        System.err.println("File not found, please try again");
                System.exit(0);
     }
		
	}
	
}
