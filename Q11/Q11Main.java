
/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q11Main
{
	private char[] find;

	/**
	 * Method to print a given array
	 */
	public void printArray()
	{
		for (int i = 0; i < find.length; i++)
			System.out.print(find[i]);
		System.out.println(""); // Prints a new line after loop completes
	}

	/**
	 * Finds the index of the rightmost letter in an array that is lexicographically smaller than its follower
	 * @return
	 */
	public int findRightmostDecreasing()
	{
		for(int i = find.length-2; i >= 0; i--)
		{
			if(((int)find[i] < (int)find[i+1]))
				return i;
		}
		return -1; // If no rightmost is found, -1 is returned, only occurs if algorithms runs past last permutation
	}

	/**
	 * Finds closest successor to the right of an element at a given index
	 * @param rightMost
	 * 			Index of rightmost index to look from
	 * @return
	 * 			Index of nearest successor to element at index rightMost
	 */
	public int findCeiling(int rightMost)
	{
		int number = rightMost+1; // First element used is element to the right of given index

		for(int i = rightMost + 1; i < find.length; i++)
		{
			if((int)find[i] > (int)find[rightMost] && (int)find[i] < (int)find[number])
			{
				number = i; // Replaces number if current element is closer to element at rightMost
			}
		}
		return number;
	}


	/**
	 * Swaps two elements in an array
	 * @param one
	 * 			Index of first element to swap
	 * @param other
	 * 			Index of second element to swap
	 */
	public void swap(int one, int other)
	{
		char temp = find[one];
		find[one] = find[other];
		find[other] = temp;
	}

	/**
	 * Bubblesorts a given sub-array
	 * @param first
	 * 			Index to start sorting at
	 * @param last
	 * 			Index to stop sorting at, always last element in array in this case however
	 */
	public void sort(int first, int last)
	{
		for (int i = last; i > first; i--) // Reduces finishing index
		{
			for (int a = first; a < i; a++) // Compares each element to its next and swaps if necessary
			{
				if ((int)find[a] > (int)find[a+1])
					swap(a, a+1);
			}
		}

	}

	/**
	 * Populates array with the necessary characters
	 */
	public void populate()
	{
		find = new char[10];
		find[0] = 'A';
		find[1] = 'B';
		find[2] = 'C';
		find[3] = 'D';
		find[4] = 'E';
		find[5] = 'F';
		find[6] = 'G';
		find[7] = 'H';
		find[8] = 'I';
		find[9] = 'J';
	}

	/**
	 * Method to generate permutations
	 */
	public void permutate()
	{
		populate();
		for (int i = 2; i < 1000001; i++)
		{
			int number = findRightmostDecreasing(); // Finds rightmost element that is smaller than its follower
			int ceiling = findCeiling(number); // Finds closest successor to number that is to the left of it
			swap(number, ceiling); // Swaps ceiling with rightmost decreasing
			sort(number+1, find.length-1); // Sorts sub-array from index one after original value of number until the end of the array
			//check(i);
			if (i == 100 || i == 1000 || i == 100000 || i == 1000000)
			{
				System.out.print(i + ": "); // Prints out value
				printArray();
			}
		}
		System.out.println("");
	}


	/**
	 * Main method, runs times algorithm, and runs method to find permutations
	 * @param args
	 * 			Unused
	 */
	public static void main(String[] args)
	{
		Q11Main qm = new Q11Main();

		long time = System.currentTimeMillis();

		qm.permutate();

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - time;
		System.out.println("Time to get permutations: " + elapsedTime);
	}
}