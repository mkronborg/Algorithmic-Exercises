/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class DynamicArray<T> {
	
	private Holder<T>[] data; // Container for an unspecified data type that is only known at runtime
	int pointer; // Pointer to next element

	/**
	 * Constructor for type Dynamic Array that takes only size argument
	 * @param size
	 * 			Size of DynamicArray desired
	 */
	public DynamicArray(int size)
	{
		data = new Holder[size]; // Creates array to hold data
		pointer = 0; // First element will be placed at index 0
	}

	/**
	 * Constructor for type dynamic array that takes an arrya of the given datatype and creates a DynamicArray to contain that data
	 * @param arr
	 * 			The array to convert to DynamicArray
	 */
	public DynamicArray(T[] arr)
	{
		data = new Holder[arr.length]; // Creates array of same size to hold Holder objects
		for (int i = 0; i < arr.length; i++) // Adds each element in supplied array to array of Holder objects
		{
			add(arr[i]);
		}
	}

	/**
	 * Method to remove element at a given index from DynamicArray
	 * @param remove
	 * 			index of element to remove
	 */
	public void remove(int remove)
	{
		for(int i = remove; i < data.length; i++)
		{
			data[i] = data[i + 1]; // Shifts all elements of array after index to the left by one index
		}
		pointer--; // Decrements pointer so next element will be placed where previous last element was
	}

	/**
	 * Method to return number of elements in DynamicArray
	 * @return
	 * 		The number of elements in DynamicArray
	 */
	public int length()
	{
		return pointer-1;
	}

	/**
	 * Method to return value at a given position in array
	 * @param i
	 * 			Index of element to return
	 * @return
	 * 			Element at requested index
	 */
	public T get(int i)
	{
		return data[i].getElement(); // Calls Holder.getElement method to get value
	}

	/**
	 * method to set element at a given index to a specified value
	 * @param i
	 * 			index to place element at
	 * @param e
	 * 			Element to add
	 */
	public void set(int i, T e)
	{
		Holder<T> element = new Holder<T>(e); // Creates new Holder object
		data[i] = element; // Sets value at index specified
	}

	/**
	 *Method to add an element to the array
	 * @param e
	 * 			Element to add
	 */
	public void add(T e)
	{
		Holder<T> element = new Holder<T>(e);
		if (pointer == data.length-1) // Checks if array needs to be expanded
			data = expand(data.length * 2);
		//System.out.println("Poinnter: " + pointer + " Length: " + data.length);
		data[pointer++] = element;
	}

	/**
	 * Method to expand the array
	 * @param size
	 * 			Size of expanded array
	 * @return
	 * 			Expanded array
	 */
	public Holder<T>[] expand(int size)
	{
		Holder<T>[] newer = new Holder[size];
		for (int i = 0; i < data.length; i++)
		{
			newer[i] = data[i]; // Transfers all elements
		}
		return newer;
	}
}
