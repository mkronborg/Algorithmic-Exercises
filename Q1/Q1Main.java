/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q1Main{
	
	private ReadFile rf; // Object to read and write from and to files.

	/**
	 * Creates ReadFile object, calls methods to read names, access them, sort them, and print them to the specified output file
	 * @param input
	 *			The name of the input file
	 * @param output
	 * 			The name of the output file
	 */
	public void sortNames(String input, String output)
	{
		rf = new ReadFile(); // Object to read files
		rf.readNames(input); // Reads names from file and stores them
		rf.outputNames(bubbleSort(rf.getWords()), output); // Outputs the results from bubblesort performed on the list of names to the specified file
	}

	/**
	 * Swaps the positions of two elements in a String array
	 * @param arr
	 * 			Array to be used
	 * @param one
	 * 			Index of first element to swap
	 * @param two
	 *			Index of second element to swap
	 */
	public void swap(String [] arr, int one, int two)
	{
		String temp;
		temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}

	/**
	 * Bubble sorts a given array of names
	 * @param names
	 * 			The String array to sort
	 * @return
	 * 			The sorted String array
	 */
	public String[] bubbleSort(String[] names)
	{
		long time = System.currentTimeMillis(); // Used for timing algorithm
		for (int a = names.length; a > 0; a--) // Reduces finishing index
		{
			for (int i = 0; i < a-1; i++) // Compares each character to the one following it, and swaps them if necessary
			{
				if (rf.compare(names[i], names[i+1])) // Compares names lexicographically
				{
					swap(names, i, i+1);
				}
			}
		}
		long stopTime = System.currentTimeMillis(); // End of timer
		long elapsedTime = stopTime - time;
		System.out.println("Time to run BubbleSort: " + elapsedTime);
		return names;
	}

	/**
	 * Main method, takes arguments
	 * @param args
	 * 			Arguments specifying input and output filenames
	 */
	public static void main(String[] args){
		// args 0 - name of file to read in a list of names
		String inputFileName = null;
		// args 1 - name of file to write out the sorted list of names
		String outputFileName = "Q1Sorted.txt";
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
		
		// call method to bubble sort list of names
		new Q1Main().sortNames(inputFileName,outputFileName);
	}
	
}