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
	 * Outputs an array of names to a specified file
	 * @param outputText
	 * 			array of names to output
	 * @param output
	 * 			name of file to output to
	 */
	public void outputNames(String[]outputText, String output)
	{
		try
		{
			File file = new File(output);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("\"" + outputText[0] + "\""); // Necessary for formatting
			for (int i = 1; i < outputText.length; i++)
			{
				writer.append(", \""); // Adds necessary notation
				writer.append(outputText[i]);
				writer.append("\"");
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
	 * Reads names from a file into an array
	 * @param inputFile
	 * 			name of file to read from
	 */
	public void readNames(String inputFile)
	{
		try {
            File file = new File(inputFile); // Attempts to find file
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            words = new String[5163]; // Default array size, resized if needed
    		try
    		{
    			int c; // Integer of char read
    			int count = 0; // index of word currently being read
    			StringBuilder n = new StringBuilder();
    			while ((c = reader.read()) != -1) // Reads until reaching terminating character
    			{
    				if(isAlpha(c))
    				{
    					n.append((char)c);
    				}
    				else if(n.length() != 0)
    				{
    					String name = n.toString();
    					n.delete(0, n.length()); // Clears StringBuilder
    					
    					if (count == words.length) // resizes array if necessary
    						words = reSize(words, words.length * 2);
    					
    					words[count++] = name; // Adds word to array of words
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
