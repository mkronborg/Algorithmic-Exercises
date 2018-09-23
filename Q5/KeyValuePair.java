/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class KeyValuePair <T1, T2>
{
	private Pair<T1, T2>[] pairs; // Array holding key value pairs
	private int count; // Pointer value

	/**
	 * Constructor for KeyValuePair object
	 * @param type1
	 * 			The type to be used as key
	 * @param type2
	 * 			The type to be used as value
	 */
	public KeyValuePair(T1 type1, T2 type2)
	{
		count = 0; // Sets pointer variable
		pairs = new Pair[10]; // Creates basic size of array
	}

	/**
	 * Constructor for KeyValuePair object
	 * @param type1
	 * 			The type to be used as key
	 * @param type2
	 * 			The type to be used as value
	 * @param size
	 * 			The desired size to be used
	 */
	public KeyValuePair(T1 type1, T2 type2, int size)
	{
		count = 0; // Sets pointer variabe
		pairs = new Pair[size]; // Creates specified size array
	}

	/**
	 * Search method, finds a index of value based on its specified key
	 * @param key
	 * 			Key to search for
	 * @return
	 * 			Value to return
	 */
	public int search(T1 key)
	{
		for (int i = 0; i < count; i++)
			{
				if (pairs[i].getKey().hashCode() == key.hashCode())
				{
					return i;
				}
			}
		return -1; // If value is not found then -1 is returned
	}

	/**
	 * Method to return a value based on its specified key
	 * @param key
	 * 			Key to search for
	 * @return
	 * 			Value associated with key
	 */
	public T2 find(T1 key)
	{
		int c = search(key);
		if (c == -1) // c will be -1 if element was not found
		{
			System.out.println("Could not be found"); // If key was not found them error message is printed
			return null;
		}
		else
			return (T2) pairs[c].getValue(); // Returns value requested
	}

	/**
	 * removes an element based on its key value
	 * @param key
	 * 			Key to search for
	 */
	public void remove(T1 key)
	{
		int c = search(key); // Finds location of this key-value-pair
		if (c == -1) // If element is not found then -1 is returned by search() method
		{
			System.out.println("Could not be found");
		}
		else
		{ 
			for (int i = c; i < count; i++) // Shifts all elements in front of the one to be removed back by one space
			{
				pairs[i] = pairs[i+1];
			}
			count--; // Next pair will be placed where the last one previously was
		}
	}

	/**
	 * removes the element at the front of the array
	 */
	public void remove()
	{
		if (count == 0) // If element is not found then -1 is returned by search() method
		{
			System.out.println("No elements stored");
		}
		else
		{
			count--; // Next pair will be placed where the last one previously was
		}
	}

	/**
	 * Method to expand size of array if necessary
	 */
	private void expand()
	{
		Pair<T1, T2>[] newer = new Pair[2 * pairs.length]; // Creates new array of twice the size to use
		for(int i = 0; i < pairs.length; i++)
		{
			newer[i] = pairs[i]; // copies all currently held pairs into new array
		}
		pairs = newer; // Sets value of pairs to new array
	}

	/**
	 * Method to add new key-value-pair
	 * @param key
	 * 			Key to use
	 * @param value
	 * 			Value to use
	 */
	public void add(T1 key, T2 value)
	{
		Pair<T1, T2> p = new Pair<T1, T2>(key, value); //Creates new pair object
		if (count == pairs.length-1)
		{
			expand(); // Expands array if necessary
		}
		pairs[count++] = p; // Adds element to array
	}
}
