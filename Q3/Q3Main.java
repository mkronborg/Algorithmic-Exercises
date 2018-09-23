


/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q3Main{
	
	private ReadFile rf; // ReadFile object
	private String [] names; // list of names to sort
	
	public void sortNames(String input, String output)
	{
		rf = new ReadFile();
		rf.readNames(input);
		names = rf.getWords(); // gets names from ReadFile object
		
		long time = System.currentTimeMillis(); // Times sorting
		
		quickSort();
		
		long stopTime = System.currentTimeMillis();
		
		long elapsedTime = stopTime - time;
		System.out.println("Time to run QuickSort: " + elapsedTime);
		
		rf.outputNames(names, output); // outputs results
	}


	/**
	 * Swaps the positions of two elements in a String array
	 * @param first
	 * 			Index of first element to swap
	 * @param second
	 *			Index of second element to swap
	 */
	public void swap(int first, int second)
	{
		String temp = names[first];
		names[first] = names[second];
		names[second] = temp;
	}

	/**
	 * Quicksorts an array of strings
	 */
	public void quickSort()
	{
		Stack stack = new Stack(); // Creates a Stack object (Self-Implemented due to spec limitations)
		
		int start = 0; // Sets starting index
		int end = names.length-1; //Sets finishing index
		
		stack.push(start); // pushes indices to stack
		stack.push(end);
		
		while(stack.getSize() > 0) // Sorting loop until no elements are left in the stack
		{
			end = stack.pop(); // Pops indices to use for current iteration
			start = stack.pop();
			
			if (end - start == 1) // Base case scenario
			{
				if (rf.compare(names[start], names[end])) // Compares strings lexicographically
				{
					swap(start, end); // swaps elements
				}
			}
			else
			{
			
				int check = start + 1; // First index to check
			
				int storePos = check; // Position to store element in if it comes before pivot
				String pivot = names[start]; // Sets pivot value
	
				while(check <= end) // Checks elements until it reaches end
				{
					if (rf.compare(pivot, names[check])) // Compares strings lexicographically
					{
						swap(check, storePos); // Swaps element with element in storePos
						storePos++; // Increments storePos
					}
					check++;
				}
				swap(start, storePos-1);// Finally swaps pivot with storePos, the position it belongs in
			
				if(storePos - 1 - start > 0) // if only one element is left on left of pivot then that part of the list is already sorted
				{
					stack.push(start); // Pushes next indices to stack
					stack.push(storePos-1);
				}
				if(end - (storePos + 1) > 0) // If only one element is left on right of pivot then that part of the list is already sorted
				{
					stack.push(storePos+1); // Pushes next indices to stack
					stack.push(end);
				}
			}
		}
	}

	/**
	 * main method, gets arguments from user and runs sorting method
	 * @param args
	 * 			Names of input and output files
	 */
	public static void main(String[] args){
		// args 0 - name of file to read in a list of names
		String inputFileName = null;
		// args 1 - name of file to write out the sorted list of names
		String outputFileName = "Q3Sorted.txt"; // Default name
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
		
		// call method to quick sort list of names
		new Q3Main().sortNames(inputFileName,outputFileName);
	}
	
}