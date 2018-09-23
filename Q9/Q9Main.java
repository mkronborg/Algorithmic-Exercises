/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q9Main{
	private ReadFile rf; // Object to read from file with

	/**
	 * Receives input and output file names, reads input, encrypts it, and outputs it
	 * @param input
	 * @param output
	 */
	public void encryptText(String input, String output){
		rf = new ReadFile();
		rf.readWords(input); // Reads words from file

		String [] t = rf.getWords(); // Places words in an array
		DynamicArray<String> text = new DynamicArray<String>(t);  // Converts to a DynamicArray object for easier manipulation

		Encrypter e = new Encrypter(text, "LOVELACE"); // Passes input and keyword to Encrypter object

		DynamicArray<String> outputText = e.encryptText(); // Receives encrypted text

		rf.outputWords(toArray(outputText), output); // Converts encrypted text from DynamicArray to array and outputs
	}

	/**
	 * Converts an element of type DynamicArray to a regular array
	 * @param convert
	 * 			DynamicArray object to convert
	 * @return
	 * 			Array containing same data as convert argument
	 */
	public static String[] toArray(DynamicArray<String> convert)
	{
		String [] arr = new String[convert.length()];

		for (int i = 0; i < convert.length(); i++)
		{
			arr[i] = convert.get(i);
		}

		return arr;
	}

	/**
	 * Encrypts a given text using the keyword "LOVELACE"
	 * @param args
	 */
	public static void main(String[] args){
		// args 0 - name of file to read in the text to encrypt
		String inputFileName = null;
		// args 1 - name of file to write out the encrypted text
		String outputFileName = "EncryptOutput.txt";
		if(args.length == 1){
			inputFileName = args[0];
		}
		else if(args.length == 2){
			inputFileName = args[0];
			outputFileName = args[1];
		}
        else if (args.length == 0)
        {
            System.err.println("Insufficient arguments supplied, please try again");
            System.exit(0);
        }

		// call method to encrypt text
		new Q9Main().encryptText(inputFileName,outputFileName);
	}
}