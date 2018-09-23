/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Encrypter {

	private DynamicArray<String> text; // DynamicArray holding all the Strings read in
	private String keyword; // Keyword to encode with
	private int count; // count of position in keyword

	/**
	 * Constructor for Encrypter
	 * @param text
	 * 			The text to encrypt
	 * @param keyword
	 * 			Keyword to encrypt
	 */
	public Encrypter(DynamicArray<String> text, String keyword)
	{
		this.text = text;
		this.keyword = keyword;
	}

	/**
	 * Method to encrypt a given string using the keyword
	 * @param str
	 * 			String to encrypt
	 * @return
	 * 			Encrypted String
	 */
	public String encrypt(String str)
	{
		StringBuilder temp = new StringBuilder();
		int index, subtract, charValue; // index: index to add to ascii value of char, subtract: value to subtract to get index in alphabet, charValue: index of encrypted character
		char e, c; //
		for (int i = 0; i < str.length(); i++)
		{
			if (count == keyword.length()) // Ensures count stays inside length of keyword
				count = 0;
			c = str.charAt(i); // Current char to encrypt
			if ((c < 91 && c > 64))
			{
				subtract = 65;
			}
			else
			{
				subtract = 97;
			}
			index = (int)(keyword.charAt(count++)) - 65; // Calculates index to displace char in string by
			charValue = (((int)str.charAt(i) - subtract) + index) % 26; // Calculates index of new char
			
			e = (char)(charValue + subtract); // Adds value to get ascii value of new char
			temp.append(e); // Adds char to StringBuilder
		}
		String encrypted = temp.toString();
		return encrypted; // Returns encrypted string
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
	 * Method to encrypt text
	 * @return
	 * 			Encrypted text
	 */
	public DynamicArray<String> encryptText()
	{
		int count = 0;
		for(int i = 0; i < text.length(); i++)
		{
			if (text.get(i).length() > 0 && isAlpha(text.get(i).charAt(0))) // Ensures String is not empty and that it is a word in order to encrypt it
				text.set(i, encrypt(text.get(i))); // Sets String at index i to be
		}
		return text;
	}
	
	
}
