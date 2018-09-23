/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Pair <T1, T2>
{
	private T1 key;
	private T2 value;

	/**
	 * Constructor for Pair object, specifies and sets the type of elements stored by it
	 * @param key
	 * 			The key element to be used
	 * @param value
	 * 			The Value element to be used
	 */
	public Pair(T1 key, T2 value)
	{
		this.key = key;
		this.value = value;
	}

	/**
	 * Accessor method for key
	 * @return
	 * 			The object stored in key variable
	 */
	public T1 getKey()
	{
		return key;
	}

	/**
	 * Accessor method for value
	 * @return
	 * 			The object stored in value object
	 */
	public T2 getValue()
	{
		return value;
	}
}
