/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Redacter {
	
	private DynamicArray<String> words, text;
	private DynamicArray<Integer> fullStops;
	
	public Redacter(DynamicArray<String> text, DynamicArray <String> words)
	{
		this.text = text;
		this.words = words;
		fullStops = new DynamicArray<Integer>(100);
	}
	
	/**
	 * Method to check if two strings are the same
	 * 
	 * @param str1
	 * 			First string to examine
	 * @param str2
	 * 			Second string to examine
	 * @return	
	 * 			True if the strings are identical
	 */
	private boolean equals(String str1, String str2)
	{
		if (str1.length() != str2.length()) // Easiest to check is if they are the same to potentially avoid running remaining code
			return false;
		for(int i = 0; i < str1.length(); i++)
		{
			if (str1.charAt(i) != str2.charAt(i)) // If at any point one char is not equal to the same char at a given index then they are disimilar
				return false;
		}
		return true; // If no discrepancies are found then the strings must be equal
	}
	
	/**
	 * Checks if a given string is in the list of words to be redacted
	 * 
	 * @param str 
	 * 			The string to be checked
	 * @return
	 * 			Returns true if the words should not be redacted
	 */
	private boolean checkWord(String str)
	{
		for (int i = 0; i < words.length(); i++)
		{
			if (equals(words.get(i), str))
			{
				return false;
			}
				
		}
		return true;
	}
	
	/**
	 * Method to check if the word at a given index is a noun
	 * 
	 * @param i
	 * 			Index of word, index is needed so a preceding full stop can be found
	 * @return
	 * 			Whether the word is definitely a noun or not.
	 */
	private boolean isNoun(int i)
	{
		if (text.get(i).length() > 1) // Ensures word is more than one character
		{
			String prior = text.get(i-1);
			if (prior.charAt(0) == '\"' || prior.charAt(0) == '-' || prior.charAt(0) == '“') // Checks if " or - is the char next to the word
			{
				if (text.get(i-2).charAt(0) == 10) // Checks if the char 2 spaces before the word is a newline character
				{
					fullStops.add(i); // Saves location for second pass
					return false;
				}
				if (text.get(i-2).length() == 1 && text.get(i-3).length() == 1) // Checks if there is a one letter word before the space before the word
				{
					fullStops.add(i); // Saves location for second pass
					return false;
				}
				if (text.get(i-3).charAt(0) == 13)
				{
					fullStops.add(i); // Saves location for second pass
					return false;
				}
		}
		if (prior.charAt(0) == ' ' && text.get(i-2).length() == 1 && text.get(i-2).charAt(0) != 'I') // Checks that word prior to word is not a type of full stop
		{
			fullStops.add(i); // Saves location for second pass
			return false;
		}
		if (prior.charAt(0) == 10) //If char before word is a newline character then it is saved for another iteration
		{
			fullStops.add(i);
			return false;
		}

		if (text.get(i).charAt(0) > 64 && text.get(i).charAt(0) < 91) // If starting character is uppercase then it is assumed to be a noun
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to produce a string of '*' chars equal to the length of a given string
	 * @param str
	 * 			String to be redacted
	 * @return
	 * 			String of only '*', of same lenngth as str
	 */
	private String redact(String str)
	{
		StringBuilder r = new StringBuilder();
		for(int i = 0; i < str.length(); i++)
			r.append("*");
		String redacted = r.toString();
		return redacted;
	}

	/**
	 * Method to redact the words in a  given array of strings based on them being on a list, or being nouns
	 * 
	 * Loops through the text once to redact listed words, and redact nouns, as well as add them to list,
	 * then iterates through points with full stops to check if the following words have been added to list
	 */
	public DynamicArray<String> redactText()
	{
		for (int i = 0; i < text.length(); i++)
		{
			if(!checkWord(text.get(i))) // checks if the word is listed to be redacted
			{
				text.set(i, redact(text.get(i))); // Redacts word
			}
			else if (isNoun(i)) // Given the index this checks if the word is a noun, but ignores it if there is a full stop.
			{
				words.add(text.get(i)); // Since this is a noun, it is added to the list of redacted words
				text.set(i, redact(text.get(i))); // Word is then redacted
			}
		}
		
		int i = 0;
		int check;
		while (i < fullStops.length()) // Loops through full stop points and checks the word that follows it
		{
			check = fullStops.get(i);
			if(!checkWord(text.get(check)))
			{
				text.set(check, redact(text.get(check)));
			}
			i++;
				
		}
		return text;
	}
}
